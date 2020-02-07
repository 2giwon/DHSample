package com.egiwon.delieveryherosample.ui

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.egiwon.delieveryherosample.R
import com.egiwon.delieveryherosample.base.BaseActivity
import com.egiwon.delieveryherosample.databinding.ActivityMainBinding
import com.egiwon.delieveryherosample.ext.setupWithViewPager2
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<ActivityMainBinding, GithubSharedViewModel>(
        R.layout.activity_main
) {

    override val viewModel: GithubSharedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        bind {
            vm = viewModel
            val adapter = PagerAdapter(this@MainActivity)
            vpSearch.adapter = adapter
            vpSearch.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    viewModel.setTab(Tab.values()[position])
                }
            })

            tlSearch.setupWithViewPager2(vpSearch, titleProvider = adapter, autoRefresh = true)

        }
    }
}
