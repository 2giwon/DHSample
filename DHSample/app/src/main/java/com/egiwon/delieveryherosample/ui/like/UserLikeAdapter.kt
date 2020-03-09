package com.egiwon.delieveryherosample.ui.like

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.delieveryherosample.R
import com.egiwon.delieveryherosample.base.BaseRecyclerView
import com.egiwon.delieveryherosample.databinding.ItemGithubUserBinding
import com.egiwon.delieveryherosample.ui.GithubSharedViewModel
import com.egiwon.delieveryherosample.ui.model.User

class UserLikeAdapter(
    private val viewModel: GithubSharedViewModel,
    @LayoutRes private val layoutResId: Int = R.layout.item_github_user,
    private val bindingId: Int
) : BaseRecyclerView.Adapter<User, ItemGithubUserBinding>(layoutResId, bindingId) {

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
    ) : BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding>(parent, layoutResId, bindingId) {

        init {
            binding.sharedVm = viewModel
        }
    }
}