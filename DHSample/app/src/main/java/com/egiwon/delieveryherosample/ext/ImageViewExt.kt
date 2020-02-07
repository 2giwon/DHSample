package com.egiwon.delieveryherosample.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.egiwon.delieveryherosample.R

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String) {
    Glide.with(context)
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .fitCenter()
            .into(this)
}