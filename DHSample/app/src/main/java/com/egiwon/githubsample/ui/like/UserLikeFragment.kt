package com.egiwon.githubsample.ui.like

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.egiwon.common.base.BaseFragment
import com.egiwon.githubsample.BR
import com.egiwon.githubsample.R
import com.egiwon.githubsample.databinding.FgGithubUserLikeBinding
import com.egiwon.githubsample.ui.GithubSharedViewModel
import com.egiwon.githubsample.ui.Tab
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class UserLikeFragment : BaseFragment<FgGithubUserLikeBinding, GithubSharedViewModel>(
    R.layout.fg_github_user_like
) {
    override val title: String = Tab.LOCAL.name

    override val viewModel: GithubSharedViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind { initBind() }
        viewModel.getLikeUser()
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
}