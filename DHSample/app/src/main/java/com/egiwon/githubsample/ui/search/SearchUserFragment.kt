package com.egiwon.githubsample.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.egiwon.common.base.BaseFragment
import com.egiwon.githubsample.BR
import com.egiwon.githubsample.R
import com.egiwon.githubsample.databinding.FgSearchGithubUserBinding
import com.egiwon.githubsample.ui.GithubSharedViewModel
import com.egiwon.githubsample.ui.Tab
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchUserFragment : BaseFragment<FgSearchGithubUserBinding, GithubSharedViewModel>(
    R.layout.fg_search_github_user
) {
    override val title: String = Tab.API.name

    override val viewModel: GithubSharedViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind {
            sharedVm = viewModel
            rvSearchResultUsers.adapter = SearchUserAdapter(viewModel, bindingId = BR.user)
            rvSearchResultUsers.setHasFixedSize(true)
        }
    }

    override fun addObserve() {
        super.addObserve()
        viewModel.unLikeUser.observe(viewLifecycleOwner, Observer { user ->
            (binding.rvSearchResultUsers.adapter as? SearchUserAdapter)?.run {
                onUnLikeUser(user)
            }
        })
    }

}