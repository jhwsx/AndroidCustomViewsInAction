package com.example.chapter09

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: MyViewPager
    private lateinit var tabLayout: TabLayout
    private val pageModels: MutableList<PageModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return PageFragment.newInstance(pageModels[position].layoutRes)
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
        pageModels.add(PageModel(R.string.title_ondrawdispatchdraworderviewgroup,
            R.layout.practice_ondrawdispatchdraworderviewgroup))
        pageModels.add(PageModel(R.string.title_bitmap_canvas_view,
            R.layout.practice_bitmap_canvas_view))
        pageModels.add(PageModel(R.string.title_xfermode_view, R.layout.practice_xfermode_view))
        pageModels.add(PageModel(R.string.title_savelayer_useexample,
            R.layout.practice_savelayer_useexample))
        pageModels.add(PageModel(R.string.title_savelayer_useexample2,
            R.layout.practice_savelayer_useexample2))
        pageModels.add(PageModel(R.string.title_savelayer_useexample3,
            R.layout.practice_savelayer_useexample3))
        pageModels.add(PageModel(R.string.title_savelayeralpha_useexample,
            R.layout.practice_savelayeralphauseexample))
        pageModels.add(PageModel(R.string.title_all_save_flag_view,
            R.layout.practice_all_save_flag_view))
        pageModels.add(PageModel(R.string.title_restoretocount_view,
            R.layout.practice_restoretocount_view))
        pageModels.add(PageModel(R.string.title_restoretocount_view2,
            R.layout.practice_restoretocount_view2))
    }
}