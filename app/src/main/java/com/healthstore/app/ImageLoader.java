package com.healthstore.app;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ImageLoader {

    @Inject public ImageLoader() { }

    public void load(Context context, String url, ImageView imageView) {
        Picasso.with(context)
                .load(url)
                .placeholder(R.mipmap.icon_placeholder)
                .fit()
                .into(imageView);
    }

}
