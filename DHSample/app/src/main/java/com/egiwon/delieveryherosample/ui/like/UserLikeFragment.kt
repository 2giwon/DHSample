package com.egiwon.delieveryherosample.ui.like

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.egiwon.delieveryherosample.BR
import com.egiwon.delieveryherosample.R
import com.egiwon.delieveryherosample.base.BaseFragment
import com.egiwon.delieveryherosample.databinding.FgGithubUserLikeBinding
import com.egiwon.delieveryherosample.ui.GithubSharedViewModel
import com.egiwon.delieveryherosample.ui.Tab
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserLikeFragment : BaseFragment<FgGithubUserLikeBinding, GithubSharedViewModel>(
    R.layout.fg_github_user_like
) {
    override val title: String = Tab.LOCAL.name

    override val viewModel: GithubSharedViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind { initBind() }
    }

    private fun FgGithubUserLikeBinding.initBind() {
        sharedvm = viewModel
        rvUserLike.adapter = UserLikeAdapter(viewModel, bindingId = BR.user)
        rvUserLike.setHasFixedSize(true)
    }

    override fun addObserve() {
        super.addObserve()
        viewModel.removedUser.observe(viewLifecycleOwner, Observer {
            (binding.rvUserLike.adapter as? UserLikeAdapter)?.run {
                onRemoveUnlikeUser(it)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getLikeUser()
    }
}