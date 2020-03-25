package com.egiwon.githubsample.ui.search

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.common.base.BaseRecyclerView
import com.egiwon.githubsample.R
import com.egiwon.githubsample.databinding.ItemGithubUserBinding
import com.egiwon.githubsample.ui.GithubSharedViewModel
import com.egiwon.githubsample.ui.model.User

class SearchUserAdapter(
    private val sharedViewModel: GithubSharedViewModel,
    @LayoutRes private val layoutResId: Int = R.layout.item_github_user,
    private val bindingId: Int
) : BaseRecyclerView.Adapter<User, ItemGithubUserBinding>(layoutResId, bindingId) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding> =
        SearchUserViewHolder(parent, bindingId)

    val onUnLikeUser: (User) -> Unit = {
        for (index in 0 until itemCount) {
            if (getItem(index)?.id == it.id) {
                replaceItem(it.copy(like = false), index)
            }
        }
        notifyDataSetChanged()
    }

    inner class SearchUserViewHolder(
        parent: ViewGroup,
        bindingId: Int
    ) : BaseRecyclerView.BaseViewHolder<ItemGithubUserBinding>(parent, layoutResId, bindingId) {

        init {
            binding.sharedVm = sharedViewModel
        }

    }
}