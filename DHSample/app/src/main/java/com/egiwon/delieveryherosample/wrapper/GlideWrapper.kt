package com.egiwon.delieveryherosample.wrapper

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

object GlideWrapper {

    fun asyncLoadImage(target: ImageView, url: String) {
        Glide.with(target)
            .load(url)
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(RequestOptions.circleCropTransform())
            .into(target)
    }
}