package com.example.chapter11

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
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
        viewPager.adapter = object : FragmentPagerAdapter(
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
        }
        tabLayout.setupWithViewPager(viewPager)
    }

    private inner class PageModel(
        @field:StringRes var titleRes: Int,
        @field:LayoutRes var layoutRes: Int
    )

    init {
        pageModels.add(PageModel(R.string.practice_colormatrix_half_alpha_view, R.layout.practice_colormatrix_half_alpha_view))
        pageModels.add(PageModel(R.string.practice_colormatrix_channel_output_view, R.layout.practice_colormatrix_channel_output_view))
        pageModels.add(PageModel(R.string.practice_colormatrix_enhance_saturation_view, R.layout.practice_colormatrix_enhance_saturation_view))
        pageModels.add(PageModel(R.string.practice_colormatrix_invert_view, R.layout.practice_colormatrix_invert_view))
        pageModels.add(PageModel(R.string.practice_colormatrix_increase_brightness_view, R.layout.practice_colormatrix_increase_brightness_view))
        pageModels.add(PageModel(R.string.practice_colormatrix_color_hue, R.layout.practice_colormatrix_color_hue))
        pageModels.add(PageModel(R.string.practice_colormatrix_color_project, R.layout.practice_colormatrix_color_project))
        pageModels.add(PageModel(R.string.practice_colormatrix_set_reset, R.layout.practice_colormatrix_set_reset))
        pageModels.add(PageModel(R.string.practice_colormatrix_setsaturation, R.layout.practice_colormatrix_setsaturation))
        pageModels.add(PageModel(R.string.practice_colormatrix_setscale, R.layout.practice_colormatrix_setscale))
        pageModels.add(PageModel(R.string.practice_colormatrix_setrotate, R.layout.practice_colormatrix_setrotate))
        pageModels.add(PageModel(R.string.practice_lighting_color_filter, R.layout.practice_lighting_color_filter))
        pageModels.add(PageModel(R.string.practice_porterduff_color_filter, R.layout.practice_porterduff_color_filter))
    }
}