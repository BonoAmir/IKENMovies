package com.example.android.ikenmovies.Activities;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.SearchView;


import com.example.android.ikenmovies.Adapters.RecycelerViewAdapter;
import com.example.android.ikenmovies.Model.Model;
import com.example.android.ikenmovies.Network.Utils;
import com.example.android.ikenmovies.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView,recyclerView2;
    RecyclerView.Adapter action_adapter ,comedy_adapter ;
    ProgressBar progressBar;
    String sss;

    public String Action="https://api.themoviedb.org/3/discover/movie?api_key=b3070a5d3abfb7c241d2688d066914e7&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte=1990-01-01&primary_release_date.lte=1999-12-31&vote_average.gte=6&with_genres=28";

    public String comedy="https://api.themoviedb.org/3/discover/movie?api_key=b3070a5d3abfb7c241d2688d066914e7&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&primary_release_date.gte=1990-01-01&primary_release_date.lte=1999-12-31&vote_average.gte=6&with_genres=35";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SearchView searchView=(SearchView)findViewById(R.id.search);


        SearchManager searchManager=(SearchManager)getSystemService(Context.SEARCH_SERVICE);

        ComponentName componentName=new ComponentName(getApplicationContext(),Search_Result_Activity.class);

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {


                return false;

            }

        });








        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView2=(RecyclerView)findViewById(R.id.recyclerview2);

         progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite CubeGrid = new CubeGrid();
        progressBar.setIndeterminateDrawable(CubeGrid);










        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));







        moviesClass task =new moviesClass();
        task.execute(comedy);

        moviesClass2 task2 =new moviesClass2();
        task2.execute(Action);






    }

    public class moviesClass extends AsyncTask<String,Void,ArrayList<Model>>
    {

        @Override
        protected ArrayList<Model> doInBackground(String... strings) {

            if (strings.length<1 || strings[0]==null)
            {
                return null;
            }

            ArrayList<Model> movies= Utils.fetchBooksData(strings[0]);

            return movies;
        }



        @Override
        protected void onPostExecute(ArrayList<Model> movies) {



               movies.addAll(movies);

                action_adapter=new RecycelerViewAdapter(movies,getApplicationContext() );

            recyclerView.setAdapter(action_adapter);






        }
    }

    public class moviesClass2 extends AsyncTask<String,Void,ArrayList<Model>>
    {

        @Override
        protected ArrayList<Model> doInBackground(String... strings) {

            if (strings.length<1 || strings[0]==null)
            {
                return null;
            }

            ArrayList<Model> movies= Utils.fetchBooksData(strings[0]);

            return movies;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           progressBar.setVisibility(progressBar.VISIBLE);


        }




        @Override
        protected void onPostExecute(ArrayList<Model> movies) {



            movies.addAll(movies);

            comedy_adapter=new RecycelerViewAdapter(movies,getApplicationContext() );


            recyclerView2.setAdapter(comedy_adapter);

            progressBar.setVisibility(progressBar.INVISIBLE);





        }
    }



}
