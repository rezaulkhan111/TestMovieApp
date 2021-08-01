package dev.machine.code.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import dev.machine.code.movieapp.ApiHelper.AppConstant;
import dev.machine.code.movieapp.data.remote.model.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    ImageView ivIamge;
    TextView textViewTitle;
    TextView year;
    TextView director;
    TextView actors;
    TextView plot;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ivIamge = findViewById(R.id.iv_poster_image);
        textViewTitle = findViewById(R.id.tv_title);
        year = findViewById(R.id.tv_year);
        director = findViewById(R.id.tv_director);
        actors = findViewById(R.id.tv_actor);
        plot = findViewById(R.id.tv_plot);

        Gson gson = new Gson();
        Intent intent = getIntent();
        String stringDate = intent.getStringExtra("savedUser");
        Movie movie = (Movie) gson.fromJson(stringDate, Movie.class);
        if (movie != null) {
            try {
                AppConstant.LoadImage(movie.getPosterUrl(), ivIamge);
            } catch (Exception ex) {

            }
            try {
                textViewTitle.setText("Name: " + movie.getTitle());
                year.setText("Year: " + movie.getYear());
                director.setText("Director: " + movie.getDirector());
                actors.setText("Actors: " + movie.getActors());
                plot.setText("Description: " + movie.getPlot());
            } catch (Exception ex) {

            }
        }

    }
}