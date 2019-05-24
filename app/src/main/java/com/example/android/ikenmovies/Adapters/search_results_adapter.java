package com.example.android.ikenmovies.Adapters;
import android.content.Context;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.ikenmovies.Activities.Movie_info;
import com.example.android.ikenmovies.Model.Model;
import com.example.android.ikenmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class search_results_adapter extends RecyclerView.Adapter<search_results_adapter.ViewHolder> {

    private ArrayList<Model> movies;
    private Context context;




    public search_results_adapter(ArrayList<Model> movies, Context context) {
        this.movies = movies;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item2, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final search_results_adapter.ViewHolder holder, int position) {

        final Model model=movies.get(position);

        if (model.getMovie_image().length() != 0) {

            String path = "http://image.tmdb.org/t/p/w185/" + model.getMovie_image();

            Picasso.get()
                    .load(path)
                    .fit()
                    .placeholder(R.drawable.icon)
                    .into(holder.movie_image);

        }


        holder.movie_title.setText(model.getMovie_title());
        holder.movie_overView.setText(model.getOverView());
        holder.movie_release_date.setText(model.getDate1());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent toMovieInfo = new Intent(context, Movie_info.class);
                toMovieInfo.putExtra("movie title", model.getMovie_title());
                toMovieInfo.putExtra("movie image", "http://image.tmdb.org/t/p/w185/" + model.getMovie_image());
                toMovieInfo.putExtra("movie rating",String.valueOf(model.getMovie_rating()));
                toMovieInfo.putExtra("movie release date", model.getDate1());
                toMovieInfo.putExtra("movie overView", model.getOverView());
                context.startActivity(toMovieInfo);

            }
        });





    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView movie_image ;
        TextView movie_title,movie_overView,movie_release_date;




        ViewHolder(final View itemView) {
            super(itemView);
            movie_image=itemView.findViewById(R.id.movie_image);
            movie_title=itemView.findViewById(R.id.movie_title) ;
            movie_overView=itemView.findViewById(R.id.overview);
            movie_release_date=itemView.findViewById(R.id.release_date);




        }

    }
}