package dev.machine.code.movieapp.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    private int mCurrentPosition;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(int position) {
        mCurrentPosition = position;
    }
}
