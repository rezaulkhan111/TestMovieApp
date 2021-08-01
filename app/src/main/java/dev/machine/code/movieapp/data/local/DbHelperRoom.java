package dev.machine.code.movieapp.data.local;

import androidx.lifecycle.LiveData;

import java.util.List;

import dev.machine.code.movieapp.data.remote.model.Movie;
import dev.machine.code.movieapp.data.remote.model.MovieCategory;

public interface DbHelperRoom {

    LiveData<List<Movie>> getLiveDateMovie();

    List<Movie> getAllMovie();

    void insertMovie(Movie movie);

    void insertMovies(List<Movie> movieList);

    Movie getMovieById(int id);

    LiveData<List<MovieCategory>> getLiveDateMovieCategory();

    List<MovieCategory> getAllMovieCategory();

    void insertMovieCategory(MovieCategory category);

    MovieCategory getMovieCategoryByMovieId(int movieId);
}
