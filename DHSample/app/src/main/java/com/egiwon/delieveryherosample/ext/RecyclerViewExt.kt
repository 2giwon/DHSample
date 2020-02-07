package com.egiwon.delieveryherosample.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egiwon.delieveryherosample.base.BaseRecyclerView

@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceItems")
fun RecyclerView.replaceItems(items: List<Any>?) {
    (this.adapter as? BaseRecyclerView.Adapter<Any, *>)?.run {
        replaceAll(items)
        notifyDataSetChanged()
    }
}