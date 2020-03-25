package com.egiwon.githubsample.ui

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.egiwon.common.base.BaseActivity
import com.egiwon.common.ext.setupWithViewPager2
import com.egiwon.common.wrapper.SchedulersExt.mainThreadSchedulers
import com.egiwon.githubsample.R
import com.egiwon.githubsample.databinding.ActivityGithubBinding
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit


class GithubActivity : BaseActivity<ActivityGithubBinding, GithubSharedViewModel>(
    R.layout.activity_github
) {

    override val viewModel: GithubSharedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        bind {
            vm = viewModel
            val adapter = PagerAdapter(this@GithubActivity)
            vpSearch.adapter = adapter
            vpSearch.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    viewModel.setTab(Tab.values()[position])
                }
            })

            tlSearch.setupWithViewPager2(vpSearch, titleProvider = adapter, autoRefresh = true)
            bindRx()
        }
    }

    private fun bindRx() {
        binding.etSearch
            .textChanges()
            .debounce(700, TimeUnit.MILLISECONDS)
            .textFilter()
            .observeOn(mainThreadSchedulers)
            .subscribeBy { viewModel.searchGithubQuery(it) }
            .addTo(compositeDisposable)
    }

    private fun Observable<CharSequence>.textFilter(): Observable<String> =
        map(CharSequence::trim)
            .map(CharSequence::toString)
            .distinctUntilChanged()
}
