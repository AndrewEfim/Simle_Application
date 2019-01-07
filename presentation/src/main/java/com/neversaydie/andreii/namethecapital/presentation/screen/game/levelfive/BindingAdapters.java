package com.neversaydie.andreii.namethecapital.presentation.screen.game.levelfive;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class BindingAdapters {
    @BindingAdapter({"app:url"})
    public  static void loadImage(ImageView imageView, String imageUrl) {
        Picasso.get().load(imageUrl).into(imageView);
    }
}
