package com.example.chapter06

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
    private lateinit var viewPager: ViewPager
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
        @field:LayoutRes var layoutRes: Int,
    )

    init {
        // part2
        pageModels.add(PageModel(R.string.title_draw_text_line, R.layout.practice_text_line))
        pageModels.add(PageModel(R.string.title_draw_text_rect, R.layout.practice_text_rect))
        pageModels.add(PageModel(R.string.title_text_top_baseline,
            R.layout.practice_text_top_baseline))
        pageModels.add(PageModel(R.string.title_text_center_baseline,
            R.layout.practice_text_center_baseline))
        pageModels.add(PageModel(R.string.title_static_text_center_view,
            R.layout.practice_static_text_center_view))
        pageModels.add(PageModel(R.string.title_dynamic_text_center_view,
            R.layout.practice_dynamic_text_center_view))
        pageModels.add(PageModel(R.string.title_text_gettextwidths,
            R.layout.practice_text_gettextwidths_view))
        pageModels.add(PageModel(R.string.title_breaktext1,
            R.layout.practice_text_breaktext_view1))
        pageModels.add(PageModel(R.string.title_breaktext2,
            R.layout.practice_text_breaktext_view2))
        // part3
        pageModels.add(PageModel(R.string.title_paint_set_stroke_cap,
            R.layout.practice_paint_set_stroke_cap))
        pageModels.add(PageModel(R.string.title_paint_set_stroke_join,
            R.layout.practice_paint_set_stroke_join))
        pageModels.add(PageModel(R.string.title_paint_set_stroke_miter,
            R.layout.practice_paint_set_stroke_miter))
        pageModels.add(PageModel(R.string.title_paint_set_path_effect,
            R.layout.practice_paint_set_path_effect))
        pageModels.add(PageModel(R.string.title_sunflower,
            R.layout.practice_sunflower_view))
        pageModels.add(PageModel(R.string.title_path_effect_anim,
            R.layout.practice_path_effect_anim_view))
        pageModels.add(PageModel(R.string.title_diamond_background_view,
            R.layout.practice_diamond_background_viewgroup))
        pageModels.add(PageModel(R.string.title_pinyin_text_view,
            R.layout.practice_pinyin_text_viewgroup))
    }
}