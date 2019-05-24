package com.example.android.ikenmovies.Network;


import android.text.TextUtils;

import com.example.android.ikenmovies.Model.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Utils {


    public static ArrayList fetchBooksData(String StirngURL)
    {
        String Jsonresponse="";
        ArrayList movies;
        URL url=createUrl(StirngURL);
        try {
            Jsonresponse=makeHttpRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        movies=extractFeatureFromJson(Jsonresponse);

        return movies;


    }
    private static URL createUrl(String stringURL)


    {

        URL url=null;
        try {
            url = new URL(stringURL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;

    }


    private static String makeHttpRequest(URL url) throws IOException {
        String JsonResponse="";


        HttpURLConnection httpURLConnection = null;

        InputStream inputStream=null;

        try {
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (httpURLConnection != null && httpURLConnection.getResponseCode() == 200) {
            inputStream = httpURLConnection.getInputStream();
            JsonResponse = readFromStream(inputStream);

        }

        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }

        if (inputStream != null) {
            inputStream.close();
        }


        return JsonResponse;

    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output=new StringBuilder();

        InputStreamReader inputStreamReader=new InputStreamReader(inputStream,Charset.forName("UTF-8"));
        BufferedReader reader=new BufferedReader(inputStreamReader);
        String line = reader.readLine();

        while (line !=null)
        {
            output.append(line);
            line=reader.readLine();
        }

        reader.close();

        return output.toString();
    }

    private static ArrayList extractFeatureFromJson(String output)
    {


        if (TextUtils.isEmpty(output))
        {
            return null ;
        }


        ArrayList movies= new ArrayList();

        try {


            JSONObject jsonObject=new JSONObject(output);

            JSONArray results=jsonObject.getJSONArray("results");


            for (int i =0; i<results.length();i++) {
                JSONObject movie = results.getJSONObject(i);

                String movie_poster = movie.getString("poster_path");

                String movie_title = movie.getString("title");

                double rating = movie.getDouble("vote_average");

                String date = movie.getString("release_date");

                String overview = movie.getString("overview");


                movies.add(new Model(movie_poster, movie_title, rating, date, overview));
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movies;
    }

}
