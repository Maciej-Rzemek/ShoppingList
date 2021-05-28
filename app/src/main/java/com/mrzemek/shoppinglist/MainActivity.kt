package com.mrzemek.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.mrzemek.shoppinglist.databinding.ActivityMainBinding
import com.mrzemek.shoppinglist.ui.adapters.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.mainViewPager.adapter = adapter

        TabLayoutMediator(binding.mainTabLayout, binding.mainViewPager) { tab, position ->
            when (position) {
                0 -> {tab.text = getText(R.string.active_lists_tab_label)
                    tab.setIcon(R.drawable.ic_shopping_cart_24)}
                1 -> {tab.text = getText(R.string.archived_lists_tab_label)
                    tab.setIcon(R.drawable.ic_archive_24)}
                2 -> {tab.text = getText(R.string.settings_tab_label)
                    tab.setIcon(R.drawable.ic_settings_24)
                }
            }
        }.attach()
    }
}