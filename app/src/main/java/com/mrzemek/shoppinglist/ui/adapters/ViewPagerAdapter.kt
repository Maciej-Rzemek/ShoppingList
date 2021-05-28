package com.mrzemek.shoppinglist.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mrzemek.shoppinglist.ui.active_lists.ActiveListsFragment
import com.mrzemek.shoppinglist.ui.archived_lists.ArchivedListsFragment
import com.mrzemek.shoppinglist.ui.archived_lists.ArchivedListsViewModel
import com.mrzemek.shoppinglist.ui.settings.SettingsFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> { ActiveListsFragment() }
            1 -> { ArchivedListsFragment() }
            2 -> { SettingsFragment() }
            else -> { Fragment() }
        }
    }
}
