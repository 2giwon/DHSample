package com.egiwon.delieveryherosample.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView {

    abstract class BaseViewHolder<B : ViewDataBinding>(
        parent: ViewGroup,
        @LayoutRes resourceId: Int,
        private val bindingId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(resourceId, parent, false)
    ) {
        protected val binding: B = DataBindingUtil.bind(itemView)!!

        open fun onBindViewHolder(item: Any?) {
            if (bindingId == null) return
            if (item == null) return

            binding.run {
                setVariable(
                    bindingId,
                    item
                )

                executePendingBindings()
            }
        }
    }

    abstract class Adapter<A : Any, B : ViewDataBinding>(
        @LayoutRes private val layoutResId: Int,
        private val bindingId: Int
    ) : RecyclerView.Adapter<BaseViewHolder<B>>() {

        private val list = mutableListOf<A>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<B> =
            object : BaseViewHolder<B>(parent, layoutResId, bindingId) {}

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: BaseViewHolder<B>, position: Int) =
            holder.onBindViewHolder(list[position])

        protected fun getItem(position: Int): A? = list.getOrNull(position)

        protected fun removeItem(item: A) = list.remove(item)

        protected fun replaceItem(item: A, position: Int) {
            list[position] = item
        }

        fun replaceAll(items: List<A>?) {
            if (items != null) {
                list.run {
                    clear()
                    addAll(items)
                }
            }

            notifyDataSetChanged()
        }
    }
}