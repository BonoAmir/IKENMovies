package com.example.android.ikenmovies.Activities;

import android.app.SearchManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.ikenmovies.Adapters.search_results_adapter;
import com.example.android.ikenmovies.Network.Utils;
import com.example.android.ikenmovies.R;
import com.example.android.ikenmovies.searchview_suggestions_provider.MySuggestionProvider;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;

import java.util.ArrayList;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class Search_Result_Activity extends AppCompatActivity {



    RecyclerView recyclerView;
    search_results_adapter adapter;
    TextView moive_exists;
    ImageButton back_button;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__result_);


        progressBar = findViewById(R.id.spin_kit);
        Sprite CubeGrid = new CubeGrid();
        progressBar.setIndeterminateDrawable(CubeGrid);


        //this part is all about the searchView sending the query(what the user wrote in the search) and receiving  it in this activity .
        Intent intent=getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doTask(query);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);



        }




        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));





        // sending user back to main activity after pressing back button
        back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMainActivity=new Intent(Search_Result_Activity.this,MainActivity.class);
                startActivity(backToMainActivity);
                finish();
            }
        });


    }

    public class moviesClass extends AsyncTask<String, Void, ArrayList>
    {

        @Override
        protected ArrayList doInBackground(String... strings) {

            if (strings.length<1 || strings[0]==null)
            {
                return null;
            }


            return Utils.fetchMoviesData(strings[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(VISIBLE);


        }




        @Override
        protected void onPostExecute(ArrayList movies) {





            if(movies.size()==0)
            {
                moive_exists=findViewById(R.id.movie_dosent_exist);
                moive_exists.setVisibility(VISIBLE);
            }
            else

            {

                moive_exists=findViewById(R.id.movie_dosent_exist);
                moive_exists.setVisibility(INVISIBLE);
                adapter = new search_results_adapter(movies, getApplicationContext());

                recyclerView.setAdapter(adapter);


            }


            progressBar.setVisibility(INVISIBLE);
        }
    }


    //building the api link and sending it to the asyncTask to fetch data .
    private void doTask(String query)
    {


        moviesClass task=new moviesClass();
        task.execute("http://api.themoviedb.org/3/search/movie?api_key=b3070a5d3abfb7c241d2688d066914e7&query="+query+"&page=1");
    }


}


