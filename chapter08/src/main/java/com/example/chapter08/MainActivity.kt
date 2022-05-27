package com.example.chapter08

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
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
        viewPager.setAdapter(object : FragmentPagerAdapter(
            supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return PageFragment.Companion.newInstance(pageModels[position].layoutRes)
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return getString(pageModels[position].titleRes)
            }

            override fun getCount(): Int {
                return pageModels.size
            }
        })
        tabLayout.setupWithViewPager(viewPager)
    }

    private inner class PageModel(
        @field:StringRes var titleRes: Int,
        @field:LayoutRes var layoutRes: Int
    )

    init {
        // part02
        pageModels.add(PageModel(R.string.title_paint_setxfermode_porterduffxfermode,
            R.layout.practice_paint_setxfermode_porterduffxfermode_viewgroup))
        pageModels.add(PageModel(R.string.title_light_book_view, R.layout.practice_light_book_view))
        pageModels.add(PageModel(R.string.title_twitter_view, R.layout.practice_twitter_viewgroup))
        // part03
        pageModels.add(PageModel(R.string.title_roundimageview_srcin,
            R.layout.practice_roundimageview_srcin))
        pageModels.add(PageModel(R.string.title_invertedimageview_srcin,
            R.layout.practice_invertedimageview_srcin))
        pageModels.add(PageModel(R.string.title_eraserview_srcout,
            R.layout.practice_eraserview_srcout))
        pageModels.add(PageModel(R.string.title_scratchingcardview_srcout,
            R.layout.practice_scratchingcardview_srcout))
        pageModels.add(PageModel(R.string.title_roundimageview_srcatop,
            R.layout.practice_roundimageview_srcatop))
        pageModels.add(PageModel(R.string.title_invertedimageview_srcatop,
            R.layout.practice_invertedimageview_srcatop))
        // part04
        pageModels.add(PageModel(R.string.title_roundimageview_dstin,
            R.layout.practice_roundimageview_dstin))
        pageModels.add(PageModel(R.string.title_invertedimageview_dstin,
            R.layout.practice_invertedimageview_dstin))
        pageModels.add(PageModel(R.string.title_areawaveview_dstin,
            R.layout.practice_areawaveview_dstin))
        pageModels.add(PageModel(R.string.title_heartmapview_dstin,
            R.layout.practice_heartmapview_dstin))
        pageModels.add(PageModel(R.string.title_irregularwaveview_dstin,
            R.layout.practice_irregularwaveview_dstin))
    }
}