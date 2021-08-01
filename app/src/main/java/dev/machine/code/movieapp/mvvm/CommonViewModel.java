package dev.machine.code.movieapp.mvvm;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.machine.code.movieapp.ApiHelper.RetrofitClientInstance;
import dev.machine.code.movieapp.data.local.AppDbHelperRoom;
import dev.machine.code.movieapp.data.remote.IApiService;
import dev.machine.code.movieapp.data.remote.Resource;
import dev.machine.code.movieapp.data.remote.datamanage.NetworkBoundResource;
import dev.machine.code.movieapp.data.remote.model.Movie;
import dev.machine.code.movieapp.data.remote.model.MovieResponse;
import retrofit2.Call;

public class CommonViewModel extends ViewModel {

    private AppDbHelperRoom appDbHelperRoom;

    private static IApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(IApiService.class);

    public LiveData<Resource<List<Movie>>> fetchMovie(AppDbHelperRoom appDbHelperRoom) {
        return new NetworkBoundResource<List<Movie>, MovieResponse>() {

            @Override
            protected void saveCallResult(MovieResponse movieResponse) {
                if (movieResponse != null) {
                    appDbHelperRoom.insertMovies(movieResponse.getMovies());
                }
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadDataFromDb() {
                return appDbHelperRoom.getLiveDateMovie();
            }

            @NonNull
            @Override
            protected Call<MovieResponse> createCall() {
                return apiService.GetAllMovie();
            }
        }.getAsLiveData();
    }


}
