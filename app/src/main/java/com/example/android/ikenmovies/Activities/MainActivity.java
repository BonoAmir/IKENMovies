package com.example.android.ikenmovies.Activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;



import com.example.android.ikenmovies.Adapters.RecycelerViewAdapter;
import com.example.android.ikenmovies.Network.Utils;
import com.example.android.ikenmovies.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView,recyclerView2;
    RecyclerView.Adapter action_adapter ,comedy_adapter ;
    ProgressBar progressBar;
    TextView internet_status;

    public String Action="https://api.themoviedb.org/3/discover/movie?api_key=b3070a5d3abfb7c241d2688d066914e7&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte=1990-01-01&primary_release_date.lte=1999-12-31&vote_average.gte=6&with_genres=28";

    public String comedy="https://api.themoviedb.org/3/discover/movie?api_key=b3070a5d3abfb7c241d2688d066914e7&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte=1990-01-01&primary_release_date.lte=1999-12-31&vote_average.gte=6&with_genres=35";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //init searchView
        SearchView searchView=findViewById(R.id.search);
        SearchManager searchManager=(SearchManager)getSystemService(Context.SEARCH_SERVICE);
        ComponentName componentName=new ComponentName(getApplicationContext(),Search_Result_Activity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));



        //init recyclerViews
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView2=findViewById(R.id.recyclerview2);
        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));



        //init progressBar
        progressBar = findViewById(R.id.spin_kit);
        Sprite CubeGrid = new CubeGrid();
        progressBar.setIndeterminateDrawable(CubeGrid);





        //checks if the device is connected to the internet or not then starts the asyncTask it it is connected
         isOnline();



    }


    //this class is for the first recycler view(comedy) to fetch data ** made the app runs only in portrait so no need for loaders here.
    public class moviesClass extends AsyncTask<String, Void, ArrayList>
    {


        //checks if there is an api link or not  if yes start fetching the data in background
        @Override
        protected ArrayList doInBackground(String... strings) {

            if (strings.length<1 || strings[0]==null)
            {
                return null;
            }



            return Utils.fetchMoviesData(strings[0]);
        }





        @Override
        protected void onPostExecute(ArrayList movies) {


             action_adapter=new RecycelerViewAdapter(movies,getApplicationContext() );

            recyclerView.setAdapter(action_adapter);



        }
    }


    //this class is for the first recycler view(comedy) to fetch data .

    public class moviesClass2 extends AsyncTask<String, Void, ArrayList>
    {

        @Override
        protected ArrayList doInBackground(String... strings) {

            if (strings.length<1 || strings[0]==null)
            {
                return null;
            }


            return Utils.fetchMoviesData(strings[0]);
        }

        //while fetching the data in the background show the progress bar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           progressBar.setVisibility(View.VISIBLE);


        }




        @Override
        protected void onPostExecute(ArrayList movies) {





            comedy_adapter=new RecycelerViewAdapter(movies,getApplicationContext() );


            recyclerView2.setAdapter(comedy_adapter);

            progressBar.setVisibility(View.INVISIBLE);





        }
    }


    //checks if the device is connected to the internet or not then calls the asyncTasks if yes .
    public void isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            internet_status=findViewById(R.id.internet_connection);
            internet_status.setVisibility(View.VISIBLE);
        }
        else
        {

            internet_status=findViewById(R.id.internet_connection);
            internet_status.setVisibility(View.INVISIBLE);
            moviesClass task =new moviesClass();
            task.execute(comedy);

            moviesClass2 task2 =new moviesClass2();
            task2.execute(Action);
        }
    }


}
