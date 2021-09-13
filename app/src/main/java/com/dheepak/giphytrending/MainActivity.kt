package com.dheepak.giphytrending

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.dheepak.giphytrending.view.TrendingGifsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
        setupTabLayout()
    }

    private fun setupTabLayout() {
        val tabLayout = findViewById<TabLayout>(R.id.tab)
        val viewPager = findViewById<ViewPager2>(R.id.viewpager)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Trending"
                1 -> tab.text = "Favourites"
            }

        }.attach()
    }

    private fun setupViewPager() {
        val viewpager = findViewById<ViewPager2>(R.id.viewpager)
        viewpager.adapter = FragmentAdapter(this)
    }

    inner class FragmentAdapter internal constructor(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> TrendingGifsFragment()
                1 -> GifFavortiesFragment()
                else -> TrendingGifsFragment()
            }
        }
    }

}