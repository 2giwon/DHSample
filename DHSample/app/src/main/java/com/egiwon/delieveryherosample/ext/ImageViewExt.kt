package com.egiwon.delieveryherosample.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}