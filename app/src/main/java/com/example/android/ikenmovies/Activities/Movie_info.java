package com.example.android.ikenmovies.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.ikenmovies.R;
import com.squareup.picasso.Picasso;

public class Movie_info extends AppCompatActivity {

    TextView movie_name, movie_release_date, movie_rating, movie_overView;

    ImageView movie_poster, backGround_image;

    ImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        movie_name =  findViewById(R.id.movie_name);
        movie_release_date =  findViewById(R.id.release_date);
        movie_rating =  findViewById(R.id.movie_rating);
        movie_overView =  findViewById(R.id.overview);


        movie_poster =  findViewById(R.id.movie_poster);
        backGround_image =  findViewById(R.id.background_image);


        back_button=findViewById(R.id.back_button);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();



            }
        });




            movie_name.setText(getIntent().getStringExtra("movie title"));
            movie_release_date.setText(getIntent().getStringExtra("movie release date"));
            movie_rating.setText(getIntent().getStringExtra("movie rating"));
            movie_overView.setText(getIntent().getStringExtra("movie overView"));

            Picasso.get()
                    .load(getIntent().getStringExtra("movie image"))
                    .fit()
                    .into(movie_poster);


            Picasso.get()
                    .load(getIntent().getStringExtra("movie image"))
                    .fit()
                    .into(backGround_image);


        }


    }

