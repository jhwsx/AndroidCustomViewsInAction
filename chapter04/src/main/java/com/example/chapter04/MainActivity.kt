package com.example.chapter04

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList

/**
 * Property Animation(属性动画) 包括 ValueAnimator 和 ObjectAnimator
 */
class MainActivity : AppCompatActivity() {
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null
    private val pageModels: MutableList<PageModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewPager = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tabLayout)
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPager!!.adapter = object : FragmentPagerAdapter(
            supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                return PageFragment.newInstance(pageModels[position].layoutRes)
            }

            override fun getPageTitle(position: Int): CharSequence {
                return getString(pageModels[position].titleRes)
            }

            override fun getCount(): Int {
                return pageModels.size
            }
        }
        tabLayout!!.setupWithViewPager(viewPager)
    }

    private inner class PageModel(
        @field:StringRes var titleRes: Int,
        @field:LayoutRes var layoutRes: Int
    )

    init {
        // part1
        pageModels.add(PageModel(R.string.practice_propertyvaluesholder_offloat, R.layout.practice_propertyvaluesholder_offloat))
        pageModels.add(PageModel(R.string.practice_propertyvaluesholder_ofobject, R.layout.practice_propertyvaluesholder_ofobject))
        pageModels.add(PageModel(R.string.practice_propertyvaluesholder_ofkeyframe, R.layout.practice_propertyvaluesholder_ofkeyframe))
        pageModels.add(PageModel(R.string.practice_telephone_ring_view, R.layout.practice_telephone_ring_view))
        pageModels.add(PageModel(R.string.practice_keyframe_interpolator, R.layout.practice_keyframe_interpolator))
        pageModels.add(PageModel(R.string.practice_keyframe_ofobject, R.layout.practice_keyframe_ofobject))

        // part2
        pageModels.add(PageModel(R.string.practice_viewpropertyanimator_intro, R.layout.practice_viewpropertyanimator_intro))
        pageModels.add(PageModel(R.string.practice_viewpropertyanimator_xxx_vs_xxxby, R.layout.practice_viewpropertyanimator_xxx_vs_xxxby))
    }
}