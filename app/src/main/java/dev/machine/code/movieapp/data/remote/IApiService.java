package dev.machine.code.movieapp.data.remote;

import java.util.List;

import dev.machine.code.movieapp.data.remote.model.MovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiService {

    @GET("erik-sytnyk/movies-list/master/db.json")
    Call<MovieResponse> GetAllMovie();
}
