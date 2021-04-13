package com.moshiur.simpleblogapp.databinding;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.moshiur.simpleblogapp.R;

public class GlideBindingAdapter {

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String imageUrl){
        Context context = imageView.getContext();
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_broken_image);
        Glide.with(context).setDefaultRequestOptions(options)
                .load(imageUrl)
                .into(imageView);
    }
}
