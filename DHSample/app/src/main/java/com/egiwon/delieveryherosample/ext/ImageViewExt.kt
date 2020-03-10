package com.egiwon.delieveryherosample.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.egiwon.delieveryherosample.wrapper.GlideWrapper

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String) = GlideWrapper.asyncLoadImage(this, url)
