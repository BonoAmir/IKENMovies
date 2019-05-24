package com.example.android.ikenmovies.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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



public class RecycelerViewAdapter extends RecyclerView.Adapter<RecycelerViewAdapter.ViewHolder> {


    ArrayList<Model> movies;
    Context context;
    String date, overView;

    public RecycelerViewAdapter(ArrayList<Model> movies, Context context) {
        this.movies = movies;
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Model model = movies.get(position);
        if (model.getMovie_image().length() != 0) {

            String path = "http://image.tmdb.org/t/p/w185/" + model.getMovie_image();

            Picasso.get()
                    .load(path)
                    .fit()
                    .placeholder(R.drawable.icon)
                    .into(holder.movie_image);

        }
        holder.movie_name.setText(model.getMovie_title());


        if (model.getMovie_rating() <= 6.5 ) {

            int color = ContextCompat.getColor(context, R.color.red);

            holder.movie_rating.setTextColor(color);
            holder.movie_rating.setText(String.valueOf(model.getMovie_rating()));
        }

        if (model.getMovie_rating() > 6.5 && model.getMovie_rating() < 8) {
            int color = ContextCompat.getColor(context, R.color.yellow);

            holder.movie_rating.setTextColor(color);
            holder.movie_rating.setText(String.valueOf(model.getMovie_rating()));

        }

        if (model.getMovie_rating() >= 8) {
            int color = ContextCompat.getColor(context, R.color.green);

            holder.movie_rating.setTextColor(color);
            holder.movie_rating.setText(String.valueOf(model.getMovie_rating()));

        }

        date = model.getDate1();
        overView = model.getOverView();



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
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView movie_image ;
        TextView movie_name;
        TextView movie_rating;



        public ViewHolder(final View itemView) {
            super(itemView);
            movie_image=(ImageView)itemView.findViewById(R.id.img);
            movie_name=(TextView)itemView.findViewById(R.id.movie_name) ;
            movie_rating=(TextView)itemView.findViewById(R.id.movie_rating);




        }

    }
}
