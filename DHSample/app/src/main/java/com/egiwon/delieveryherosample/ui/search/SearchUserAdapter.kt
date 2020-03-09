package com.egiwon.delieveryherosample.ui.search

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.egiwon.delieveryherosample.R
import com.egiwon.delieveryherosample.base.BaseRecyclerView
import com.egiwon.delieveryherosample.databinding.ItemGithubUserBinding
import com.egiwon.delieveryherosample.ui.GithubSharedViewModel
import com.egiwon.delieveryherosample.ui.model.User

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
                replaceItem(it, index)
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