package dev.machine.code.movieapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import dev.machine.code.movieapp.data.remote.model.Movie;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> loadAllMovie();

    @Query("SELECT * FROM Movie")
    List<Movie> loadListMovie();

    @Insert
    void InsertMovie(Movie movie);

    @Query("SELECT * FROM Movie where id=:movieId")
    Movie getMovieById(int movieId);

    @Query("DELETE FROM Movie")
    void deleteAllMovie();
}
