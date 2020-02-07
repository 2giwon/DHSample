package com.egiwon.delieveryherosample.ui.search

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.delieveryherosample.R
import com.egiwon.delieveryherosample.base.BaseRecyclerView
import com.egiwon.delieveryherosample.data.User
import com.egiwon.delieveryherosample.databinding.ItemGithubUserBinding
import com.egiwon.delieveryherosample.ui.GithubSharedViewModel

class SearchUserAdapter(
        private val sharedViewModel: GithubSharedViewModel,
        @LayoutRes private val layoutResId: Int = R.layout.item_github_user
) : BaseRecyclerView.Adapter<User, ItemGithubUserBinding>(layoutResId) {

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding> = SearchUserViewHolder(parent)

    val onUnLikeUser: (User) -> Unit = {
        for (index in 0 until itemCount) {
            if (getItem(index)?.id == it.id) {
                getItem(index)?.like = false
            }
        }
        notifyDataSetChanged()
    }

    inner class SearchUserViewHolder(
            parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding>(parent, layoutResId) {

        init {
            binding.sharedVm = sharedViewModel
        }

        override fun bindItem(item: Any?) {
            (item as? User)?.let {
                binding.user = it
            }
        }
    }
}