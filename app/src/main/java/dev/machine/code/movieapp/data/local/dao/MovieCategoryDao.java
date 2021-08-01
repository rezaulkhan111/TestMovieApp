package dev.machine.code.movieapp.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import dev.machine.code.movieapp.data.remote.model.MovieCategory;

@Dao
public interface MovieCategoryDao {

    @Query("SELECT * FROM MovieCategory")
    LiveData<List<MovieCategory>> loadAllMovieCategory();

    @Query("SELECT * FROM MovieCategory")
    List<MovieCategory> loadListMovieCategory();

    @Insert
    void InsertMovieCategory(MovieCategory movieCategory);

    @Query("SELECT * FROM MovieCategory where movieId=:movieId")
    MovieCategory getMovieCategoryByMovieId(int movieId);

    @Query("DELETE FROM MovieCategory")
    void deleteAllMovieCategory();
}
