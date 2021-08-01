package dev.machine.code.movieapp.ApiHelper;

import android.widget.ImageView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class AppConstant {
    public static final String APP_URL = "https://raw.githubusercontent.com/";


    public static void LoadImage(String imageUrl, ImageView imageId) {
        if (!imageUrl.equals("")) {
            try {
                Picasso.get().load(imageUrl).networkPolicy(NetworkPolicy.NO_STORE, NetworkPolicy.NO_CACHE).into(imageId);
            } catch (Exception ex) {
            }
        }
    }
}
