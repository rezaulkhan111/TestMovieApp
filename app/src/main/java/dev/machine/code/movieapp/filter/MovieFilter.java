package dev.machine.code.movieapp.filter;

import android.widget.Filter;

import java.util.ArrayList;

import dev.machine.code.movieapp.adapter.MovieAdapter;
import dev.machine.code.movieapp.data.remote.model.Movie;

public class MovieFilter extends Filter {

    private final ArrayList<Movie> movieArrayList;
    private final MovieAdapter movieAdapter;

    public MovieFilter(ArrayList<Movie> movieArrayList, MovieAdapter movieAdapter) {
        this.movieArrayList = movieArrayList;
        this.movieAdapter = movieAdapter;
    }

    @Override
    protected Filter.FilterResults performFiltering(CharSequence constraint) {
        Filter.FilterResults results = new Filter.FilterResults();

        if (constraint != null && constraint.length() > 0) {
            //CHANGE TO UPPER
            constraint = constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Movie> filterProducts = new ArrayList<>();

            for (int i = 0; i < movieArrayList.size(); i++) {

                if (movieArrayList.get(i).getTitle().toUpperCase().contains(constraint)) {
                    filterProducts.add(movieArrayList.get(i));
                }
//                if (movieArrayList.get(i).getPrice().toUpperCase().contains(constraint)) {
//                    filterProducts.add(movieArrayList.get(i));
//                }
//                if (movieArrayList.get(i).getRegularPrice().toUpperCase().contains(constraint)) {
//                    filterProducts.add(movieArrayList.get(i));
//                }
            }
            results.count = filterProducts.size();
            results.values = filterProducts;
        } else {
            results.count = movieArrayList.size();
            results.values = movieArrayList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, Filter.FilterResults results) {
        movieAdapter.listMovie = (ArrayList<Movie>) results.values;
        movieAdapter.notifyDataViewSetChange();
    }
}
