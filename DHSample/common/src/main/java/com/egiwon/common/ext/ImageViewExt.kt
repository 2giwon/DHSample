package com.egiwon.common.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.egiwon.common.wrapper.GlideWrapper

@BindingAdapter("loadUrl")
fun ImageView.loadUrl(url: String) = GlideWrapper.asyncLoadImage(this, url)