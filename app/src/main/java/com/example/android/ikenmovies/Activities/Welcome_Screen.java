package com.example.android.ikenmovies.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.android.ikenmovies.R;

public class Welcome_Screen extends AppCompatActivity {

    ImageView img;
    Button btn ;
    Animation FromBottom , FromTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);


        img=findViewById(R.id.img);
        btn=findViewById(R.id.btn);

        FromBottom=AnimationUtils.loadAnimation(this,R.anim.from_bottom);
        FromTop=AnimationUtils.loadAnimation(this,R.anim.from_top);

        img.setAnimation(FromTop);
        btn.setAnimation(FromBottom);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMainScreen=new Intent(Welcome_Screen.this,MainActivity.class);
                startActivity(goToMainScreen);
                finish();
            }
        });


    }
}
