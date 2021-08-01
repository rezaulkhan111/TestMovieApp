package dev.machine.code.movieapp.data.remote.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MovieCategory")
public class MovieCategory {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movieCategoryTableId")
    private transient Long movieCategoryTableId;

    private int movieId;

    private String genresName;

    public Long getMovieCategoryTableId() {
        return movieCategoryTableId;
    }

    public void setMovieCategoryTableId(Long movieCategoryTableId) {
        this.movieCategoryTableId = movieCategoryTableId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getGenresName() {
        return genresName;
    }

    public void setGenresName(String genresName) {
        this.genresName = genresName;
    }
}
