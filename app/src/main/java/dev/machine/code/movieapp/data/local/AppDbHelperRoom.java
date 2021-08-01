package dev.machine.code.movieapp.data.local;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.room.Room;

import java.util.List;

import dev.machine.code.movieapp.data.remote.model.Movie;
import dev.machine.code.movieapp.data.remote.model.MovieCategory;

public class AppDbHelperRoom implements DbHelperRoom {

    private AppDataBase dataBase;

    private MediatorLiveData<List<Movie>> listMediatorLiveData;

    public AppDbHelperRoom(Context context) {
        this.dataBase = Room.databaseBuilder(context, AppDataBase.class, "MovieDatebase").build();
        this.listMediatorLiveData = new MediatorLiveData<>();
    }

    @Override
    public LiveData<List<Movie>> getLiveDateMovie() {
        return dataBase.movieDao().loadAllMovie();
    }

    @Override
    public List<Movie> getAllMovie() {
        return null;
    }

    @Override
    public void insertMovie(Movie movie) {

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void insertMovies(List<Movie> movieList) {
        new AsyncTask<List<Movie>, Void, Void>() {

            @Override
            protected Void doInBackground(List<Movie>... lists) {
                dataBase.movieDao().deleteAllMovie();
                dataBase.movieCategoryDao().deleteAllMovieCategory();

                for (Movie movieItem : lists[0]) {
                    dataBase.movieDao().InsertMovie(movieItem);

                    for (String category : movieItem.getGenres()) {
                        MovieCategory movieCategory = new MovieCategory();
                        movieCategory.setMovieId(movieItem.getId());
                        movieCategory.setGenresName(category);
                        dataBase.movieCategoryDao().InsertMovieCategory(movieCategory);
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void unused) {
                super.onPostExecute(unused);
            }
        }.execute(movieList);
    }

    @Override
    public Movie getMovieById(int id) {
        return null;
    }

    @Override
    public LiveData<List<MovieCategory>> getLiveDateMovieCategory() {
        return null;
    }

    @Override
    public List<MovieCategory> getAllMovieCategory() {
        return null;
    }

    @Override
    public void insertMovieCategory(MovieCategory category) {

    }

    @Override
    public MovieCategory getMovieCategoryByMovieId(int movieId) {
        return null;
    }
}
