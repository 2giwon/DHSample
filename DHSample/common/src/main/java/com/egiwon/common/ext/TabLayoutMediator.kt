package com.egiwon.common.ext

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

interface TabTitleProvider {

    fun getItemTitle(position: Int): CharSequence
}

fun TabLayout.setupWithViewPager2(
    viewPager2: ViewPager2,
    titleProvider: TabTitleProvider,
    autoRefresh: Boolean
) {
    TabLayoutMediator(this, viewPager2, autoRefresh,
        TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            tab.text = titleProvider.getItemTitle(position)
        }
    ).attach()
}