package com.egiwon.delieveryherosample.ui.like

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.delieveryherosample.ui.GithubSharedViewModel
import com.egiwon.delieveryherosample.R
import com.egiwon.delieveryherosample.base.BaseRecyclerView
import com.egiwon.delieveryherosample.data.User
import com.egiwon.delieveryherosample.databinding.ItemGithubUserBinding

class UserLikeAdapter(
        private val viewModel: GithubSharedViewModel,
        @LayoutRes private val layoutResId: Int = R.layout.item_github_user
) : BaseRecyclerView.Adapter<User, ItemGithubUserBinding>(layoutResId) {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding> = UserLikeViewHolder(parent)

    val onRemoveUnlikeUser: (User) -> Unit = {
        removeItem(it)
        notifyDataSetChanged()
    }

    inner class UserLikeViewHolder(
            parent: ViewGroup
    ) : BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding>(parent, layoutResId) {

        init {
            binding.sharedVm = viewModel
        }

        override fun bindItem(item: Any?) {
            (item as? User)?.let {
                binding.user = it
            }
        }

    }
}