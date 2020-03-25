package com.egiwon.githubsample.ui.like

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.common.base.BaseRecyclerView
import com.egiwon.githubsample.R
import com.egiwon.githubsample.databinding.ItemGithubUserBinding
import com.egiwon.githubsample.ui.GithubSharedViewModel
import com.egiwon.githubsample.ui.model.User

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