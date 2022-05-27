package com.example.chapter07

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var viewPager: MyViewPager? = null
    private var tabLayout: TabLayout? = null
    private val pageModels: MutableList<PageModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager!!.setNoScroll(true)
        viewPager!!.adapter = object : FragmentPagerAdapter(
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
        tabLayout!!.setupWithViewPager(viewPager)
    }

    private inner class PageModel(
        @field:StringRes var titleRes: Int,
        @field:LayoutRes var layoutRes: Int
    )

    init {
        // part1 贝塞尔
        pageModels.add(PageModel(R.string.title_path_quadto, R.layout.practice_path_quadto_view))
        pageModels.add(PageModel(R.string.title_finger_lineto_path_view,
            R.layout.practice_finger_lineto_path_view))
        pageModels.add(PageModel(R.string.title_finger_quadto_path_view,
            R.layout.practice_finger_quadto_path_view))
        pageModels.add(PageModel(R.string.title_path_computebounds,
            R.layout.practice_path_computebounds))
        pageModels.add(PageModel(R.string.title_path_rquadto, R.layout.practice_path_rquadto_view))
        pageModels.add(PageModel(R.string.title_wave_view, R.layout.practice_wave_view))
        // part2 阴影效果
        pageModels.add(PageModel(R.string.title_paint_setshadowlayer_viewgroup,
            R.layout.practice_paint_setshadowlayer_viewgroup))
        pageModels.add(PageModel(R.string.title_textview_setshadowlayer_viewgroup,
            R.layout.practice_textview_setshadowlayer_viewgroup))
        // part3 发光效果与图片阴影
        pageModels.add(PageModel(R.string.title_paint_setmaskfilter_view,
            R.layout.practice_paint_setmaskfilter_view))
        pageModels.add(PageModel(R.string.title_bitmap_extractalpha_view,
            R.layout.practice_bitmap_extractalpha_view))
        pageModels.add(PageModel(R.string.title_bitmap_shadow_view,
            R.layout.practice_bitmap_shadow_view))
        // part4 BitmapShader
        pageModels.add(PageModel(R.string.title_paint_setshader_bitmapshader,
            R.layout.practice_paint_setshader_bitmapshader_viewgroup))
        pageModels.add(PageModel(R.string.title_telescope_view, R.layout.practice_telescope_view))
        pageModels.add(PageModel(R.string.title_circle_avatar_view,
            R.layout.practice_circle_avatar_view))
        pageModels.add(PageModel(R.string.title_avatar_view, R.layout.practice_avatar_viewgroup))
        pageModels.add(PageModel(R.string.title_paint_setshader_lineargradient,
            R.layout.practice_paint_setshader_lineargradient_viewgroup))
        pageModels.add(PageModel(R.string.title_shimmer_textview,
            R.layout.practice_shimmer_textview))
        pageModels.add(PageModel(R.string.title_paint_setshader_radialgradient,
            R.layout.practice_paint_setshader_radialgradient_viewgroup))
        pageModels.add(PageModel(R.string.title_ripple_view, R.layout.practice_ripple_viewgroup))
        pageModels.add(PageModel(R.string.title_paint_setshader_sweepgradient, R.layout.practice_paint_setshader_sweepgradient_view))
        pageModels.add(PageModel(R.string.title_colorful_stroke_viewgroup, R.layout.practice_colorful_stroke_viewgroup))
        pageModels.add(PageModel(R.string.title_paint_setshader_composeshader, R.layout.practice_paint_setshader_composeshader_viewgroup))
    }
}