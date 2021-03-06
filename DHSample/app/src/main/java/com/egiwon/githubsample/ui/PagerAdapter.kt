package com.egiwon.githubsample.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.egiwon.common.ext.TabTitleProvider
import com.egiwon.githubsample.ui.like.UserLikeFragment
import com.egiwon.githubsample.ui.search.SearchUserFragment

class PagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity), TabTitleProvider {

    private val fragments = listOf(
        SearchUserFragment(),
        UserLikeFragment()
    )

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    override fun getItemTitle(position: Int): CharSequence = fragments[position].title
}