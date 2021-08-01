package dev.machine.code.movieapp.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dev.machine.code.movieapp.ApiHelper.AppConstant;
import dev.machine.code.movieapp.R;
import dev.machine.code.movieapp.data.remote.model.Movie;
import dev.machine.code.movieapp.filter.MovieFilter;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements Filterable {

    public ArrayList<Movie> listMovie;
    private ArrayList<Movie> mfilter_listMovie;
    private ICallBack callBack;
    private MovieFilter movieFilter;

    public MovieAdapter(ArrayList<Movie> listMovie, ICallBack callBack) {
        this.listMovie = listMovie;
        this.mfilter_listMovie = listMovie;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            Movie movie = listMovie.get(position);
            holder.onBind(position);
            holder.itemView.setOnClickListener(v -> {
                callBack.MovieDetails(movie);
            });
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        if (listMovie != null) {
            return listMovie.size();
        }
        return 0;
    }

    public void notifyDataViewSetChange() {
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        if (listMovie != null) {
            movieFilter = new MovieFilter(mfilter_listMovie, this);
            return movieFilter;
        }
        return movieFilter;
    }

    public class ViewHolder extends MovieViewHolder {
        ImageView ivPosterImage;
        TextView tvTitle;
        TextView tvCategory;
        TextView tvDirector;
        TextView tvActor;
        TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPosterImage = itemView.findViewById(R.id.iv_poster_image);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvDirector = itemView.findViewById(R.id.tv_director);
            tvActor = itemView.findViewById(R.id.tv_actor);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBind(int position) {
            super.onBind(position);
            Movie movie = listMovie.get(position);
            try {
                AppConstant.LoadImage(movie.getPosterUrl(), ivPosterImage);
            } catch (Exception e) {

            }

            try {
                tvTitle.setText("Movie Name: " + movie.getTitle() + "Year: " + movie.getYear());

//                tvCategory.setText("Category: " + catItem);
                tvDirector.setText("Director Name: " + movie.getDirector());
                tvActor.setText("Actors: " + movie.getActors());
                tvDescription.setText("Description: " + movie.getPlot());
            } catch (Exception e) {

            }
        }
    }
}
