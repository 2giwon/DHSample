package com.egiwon.delieveryherosample.ui.search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.egiwon.delieveryherosample.R
import com.egiwon.delieveryherosample.base.BaseFragment
import com.egiwon.delieveryherosample.databinding.FgSearchGithubUserBinding
import com.egiwon.delieveryherosample.ui.GithubSharedViewModel
import com.egiwon.delieveryherosample.ui.Tab
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
            rvSearchResultUsers.adapter = SearchUserAdapter(viewModel)
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

        viewModel.searchUserLiveData.observe(viewLifecycleOwner, Observer {})
    }
}