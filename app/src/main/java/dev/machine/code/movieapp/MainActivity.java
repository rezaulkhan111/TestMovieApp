package dev.machine.code.movieapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

import dev.machine.code.movieapp.adapter.ICallBack;
import dev.machine.code.movieapp.adapter.MovieAdapter;
import dev.machine.code.movieapp.data.local.AppDbHelperRoom;
import dev.machine.code.movieapp.data.remote.Status;
import dev.machine.code.movieapp.data.remote.model.Movie;
import dev.machine.code.movieapp.mvvm.CommonViewModel;

public class MainActivity extends AppCompatActivity implements ICallBack {

    SearchView svSearchMovie;
    RecyclerView rvMovieList;
    CommonViewModel commonViewModel;
    MovieAdapter movieAdapter;
    AppDbHelperRoom appDbHelperRoom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        svSearchMovie = findViewById(R.id.sv_search_movie);
        rvMovieList = findViewById(R.id.rv_movie_list);
        appDbHelperRoom = new AppDbHelperRoom(getApplicationContext());
        commonViewModel = ViewModelProviders.of(this).get(CommonViewModel.class);

        commonViewModel.fetchMovie(appDbHelperRoom).observe(this, listResource -> {
            if (listResource != null && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)) {
                rvMovieList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                movieAdapter = new MovieAdapter((ArrayList<Movie>) listResource.data, this);
                rvMovieList.setAdapter(movieAdapter);
            }
        });

        svSearchMovie.setQueryHint("Search");
        svSearchMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (movieAdapter != null) {
                    if (newText != null) {
                        movieAdapter.getFilter();
                    }
                }
                return false;
            }
        });
    }


    @Override
    public void MovieDetails(Movie movie) {
        Gson gson = new Gson();
        String strinDate = gson.toJson(movie);
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("savedUser", strinDate);
        startActivity(intent);
    }
}