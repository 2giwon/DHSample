package com.egiwon.delieveryherosample.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.egiwon.delieveryherosample.ext.TabTitleProvider
import com.egiwon.delieveryherosample.ui.like.UserLikeFragment
import com.egiwon.delieveryherosample.ui.search.SearchUserFragment

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