package dev.machine.code.movieapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dev.machine.code.movieapp.data.local.dao.MovieCategoryDao;
import dev.machine.code.movieapp.data.local.dao.MovieDao;
import dev.machine.code.movieapp.data.remote.model.Movie;
import dev.machine.code.movieapp.data.remote.model.MovieCategory;

@Database(entities = {Movie.class, MovieCategory.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract MovieDao movieDao();

    public abstract MovieCategoryDao movieCategoryDao();
}
