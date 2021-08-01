package dev.machine.code.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;

import dev.machine.code.movieapp.adapter.MovieAdapter;
import dev.machine.code.movieapp.data.local.AppDbHelperRoom;
import dev.machine.code.movieapp.data.remote.Status;
import dev.machine.code.movieapp.data.remote.model.Movie;
import dev.machine.code.movieapp.mvvm.CommonViewModel;

public class SplashActivity extends AppCompatActivity {

    public static int S_TIME_OUT = 3000;

    CommonViewModel commonViewModel;
    MovieAdapter movieAdapter;
    AppDbHelperRoom appDbHelperRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        appDbHelperRoom = new AppDbHelperRoom(getApplicationContext());
        commonViewModel = ViewModelProviders.of(this).get(CommonViewModel.class);

        commonViewModel.fetchMovie(appDbHelperRoom).observe(this, listResource -> {
        });
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }, S_TIME_OUT);
    }
}