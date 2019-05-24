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
import android.widget.TextView;

import com.example.android.ikenmovies.Adapters.search_results_adapter;
import com.example.android.ikenmovies.Network.Utils;
import com.example.android.ikenmovies.R;
import com.example.android.ikenmovies.searchview_suggestions_provider.MySuggestionProvider;

import java.util.ArrayList;

public class Search_Result_Activity extends AppCompatActivity {






    RecyclerView recyclerView;

    search_results_adapter adapter;
    TextView moive_exists;
    ImageButton back_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__result_);



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



        back_button=findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backToMainActivity=new Intent(Search_Result_Activity.this,MainActivity.class);
                startActivity(backToMainActivity);
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


            return Utils.fetchBooksData(strings[0]);
        }



        @Override
        protected void onPostExecute(ArrayList movies) {





            if(movies.size()==0)
            {
                moive_exists=findViewById(R.id.movie_dosent_exist);
                moive_exists.setVisibility(View.VISIBLE);
            }
            else

            {

                moive_exists=findViewById(R.id.movie_dosent_exist);
                moive_exists.setVisibility(View.INVISIBLE);
                adapter = new search_results_adapter(movies, getApplicationContext());

                recyclerView.setAdapter(adapter);


            }



        }
    }

    private void doTask(String query)
    {

        moviesClass task=new moviesClass();
        task.execute("http://api.themoviedb.org/3/search/movie?api_key=b3070a5d3abfb7c241d2688d066914e7&query="+query+"&page=1");
    }


}


