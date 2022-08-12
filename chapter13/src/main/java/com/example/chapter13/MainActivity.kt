package com.example.chapter13

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import android.os.Bundle
import androidx.fragment.app.FragmentPagerAdapter
import androidx.annotation.StringRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: MyViewPager
    private lateinit var tabLayout: TabLayout
    private val pageModels: MutableList<PageModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        viewPager.setNoScroll(true)
        tabLayout = findViewById(R.id.tabLayout)
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return PageFragment.Companion.newInstance(pageModels[position].layoutRes)
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return getString(pageModels[position].titleRes)
            }

            override fun getCount(): Int {
                return pageModels.size
            }
        }
        tabLayout.setupWithViewPager(viewPager)
    }

    private inner class PageModel(
        @field:StringRes var titleRes: Int,
        @field:LayoutRes var layoutRes: Int
    )

    init {
        pageModels.add(PageModel(R.string.practice_photo_view, R.layout.practice_photo_view))
        pageModels.add(PageModel(R.string.practice_relay_multitouch_view, R.layout.practice_relay_multitouch_view))
        pageModels.add(PageModel(R.string.practice_cooperate_multitouch_view, R.layout.practice_cooperate_multitouch_view))
        pageModels.add(PageModel(R.string.practice_independent_multitouch_view, R.layout.practice_independent_multitouch_view))
        pageModels.add(PageModel(R.string.practice_myviewpager, R.layout.practice_myviewpager))
        pageModels.add(PageModel(R.string.practice_ondraglistener_gridview, R.layout.practice_ondraglistener_gridview))
        pageModels.add(PageModel(R.string.practice_viewdraghelper_gridview, R.layout.practice_viewdraghelper_gridview))
        pageModels.add(PageModel(R.string.practice_ondraglistener_collect_view, R.layout.practice_ondraglistener_collect_view))
        pageModels.add(PageModel(R.string.practice_viewdraghelper_updown_view, R.layout.practice_viewdraghelper_updown_view))
        pageModels.add(PageModel(R.string.practice_dingding_bottomnavigationview, R.layout.practice_dingding_bottomnavigationviewgroup))
    }
}