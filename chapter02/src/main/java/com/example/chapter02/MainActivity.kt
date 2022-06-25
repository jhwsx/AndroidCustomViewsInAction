package com.example.chapter02

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.util.ArrayList

// 参考文章:
// TODO 学习这篇文章
// Animation 原理分析 https://www.cnblogs.com/kross/p/4087780.html
// View Animation(视图动画)包括 Tween Animation(补间动画)和 Frame Animation(逐帧动画)
class MainActivity : AppCompatActivity() {
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null
    private val pageModels: MutableList<PageModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tabLayout)
        setupViewPager()
    }

    private fun setupViewPager() {
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
        // part1 视图动画标签
        pageModels.add(PageModel(R.string.title_view_animation_tag_scale,
            R.layout.practice_view_animation_tag_scale))
        pageModels.add(PageModel(R.string.title_view_animation_tag_alpha,
            R.layout.practice_view_animation_tag_alpha))
        pageModels.add(PageModel(R.string.title_view_animation_tag_rotate,
            R.layout.practice_view_animation_tag_rotate))
        pageModels.add(PageModel(R.string.title_view_animation_tag_translate,
            R.layout.practice_view_animation_tag_translate))
        pageModels.add(PageModel(R.string.title_view_animation_tag_set,
            R.layout.practice_view_animation_tag_set))
        // part2 视图动画代码
        pageModels.add(PageModel(R.string.title_view_animation_code_scale,
            R.layout.practice_view_animation_code_scale))
        pageModels.add(PageModel(R.string.title_view_animation_code_alpha,
            R.layout.practice_view_animation_code_alpha))
        pageModels.add(PageModel(R.string.title_view_animation_code_rotate,
            R.layout.practice_view_animation_code_rotate))
        pageModels.add(PageModel(R.string.title_view_animation_code_translate,
            R.layout.practice_view_animation_code_translate))
        pageModels.add(PageModel(R.string.title_view_animation_code_set,
            R.layout.practice_view_animation_code_set))
        pageModels.add(PageModel(R.string.title_view_animation_code_control_listener,
            R.layout.practice_view_animation_code_control_listener))
        // part3 插值器
        pageModels.add(PageModel(R.string.title_interpolator_set,
            R.layout.practice_interpolator_set))
        pageModels.add(PageModel(R.string.title_interpolator,
            R.layout.practice_interpolator_viewgroup))
        // part4 例子
        pageModels.add(PageModel(R.string.title_camera_stretch,
            R.layout.practice_camera_stretch_viewgroup))
        pageModels.add(PageModel(R.string.title_loading, R.layout.practice_loading_viewgroup))
        pageModels.add(PageModel(R.string.title_scanner, R.layout.practice_scanner_viewgroup))
        // part5 逐帧动画
        pageModels.add(PageModel(R.string.title_frame_anim_xml, R.layout.practice_frame_anim_xml))
        pageModels.add(PageModel(R.string.title_frame_anim_code, R.layout.practice_frame_anim_code))
    }
}