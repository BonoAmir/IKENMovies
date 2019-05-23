package com.example.android.ikenmovies.Model;

public class Model {

    private String movie_image,movie_title,date,overView;
    private double movie_rating;




    public Model(String movie_image, String movie_title, double movie_rating, String date , String overView) {
        this.movie_image = movie_image;
        this.movie_title = movie_title;
        this.movie_rating= movie_rating;
        this.date= date;
        this.overView= overView;

    }

    public String getMovie_image() {
        return movie_image;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public double getMovie_rating() {
        return movie_rating;
    }
    public String getDate1() {
        return date;
    }

    public String getOverView() {
        return overView;
    }

}
