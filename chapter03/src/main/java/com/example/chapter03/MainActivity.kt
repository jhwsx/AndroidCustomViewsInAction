package com.example.chapter03

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
                return PageFragment.Companion.newInstance(pageModels[position].layoutRes)
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
        // part1：ValueAnimator 的基本使用
        pageModels.add(PageModel(R.string.title_tween_animation_click_area_issue,
            R.layout.practice_tween_animation_click_area_issue))
        pageModels.add(PageModel(R.string.title_value_animator_fix_click_area_issue,
            R.layout.practice_value_animator_fix_click_area_issue))
        pageModels.add(PageModel(R.string.title_value_animator_varargs,
            R.layout.practice_value_animator_varargs_viewgroup))
        pageModels.add(PageModel(R.string.title_value_animator_api,
            R.layout.practice_value_animator_api_group))
        pageModels.add(PageModel(R.string.title_value_animator_bounce_loading_my,
            R.layout.practice_bounce_loading_viewgroup_my))
        pageModels.add(PageModel(R.string.title_value_animator_bounce_loading_qijian,
            R.layout.practice_bounce_loading_viewgroup_qijian))
        pageModels.add(PageModel(R.string.title_custom_interpolator_viewgroup,
            R.layout.practice_custom_interpolator_viewgroup))
        pageModels.add(PageModel(R.string.title_custom_evaluator_viewgroup,
            R.layout.practice_custom_evaluator_viewgroup))
        pageModels.add(PageModel(R.string.title_reverse_evaluator_viewgroup,
            R.layout.practice_reverse_evaluator_viewgroup))
        pageModels.add(PageModel(R.string.title_argb_evaluator_viewgroup,
            R.layout.practice_argb_evaluator_viewgroup))
        pageModels.add(PageModel(R.string.title_value_animator_of_object_viewgroup,
            R.layout.practice_value_animator_of_object_viewgroup))
        pageModels.add(PageModel(R.string.title_falling_ball_viewgroup,
            R.layout.practice_falling_ball_viewgroup))

        pageModels.add(PageModel(R.string.title_object_animator_intro_viewgroup,
            R.layout.practice_object_animator_intro_viewgroup))
        pageModels.add(PageModel(R.string.practice_objectanimator_falling_ball_viewgroup,
            R.layout.practice_objectanimator_falling_ball_viewgroup))
        pageModels.add(PageModel(R.string.practice_objectanimater_getter,
            R.layout.practice_objectanimater_getter))
        pageModels.add(PageModel(R.string.practice_objectanimater_property,
            R.layout.practice_objectanimater_property))

        pageModels.add(PageModel(R.string.practice_animatorset_playsequentially,
            R.layout.practice_animatorset_playsequentially))
        pageModels.add(PageModel(R.string.practice_animatorset_playtogether,
            R.layout.practice_animatorset_playtogether))
        pageModels.add(PageModel(R.string.practice_animatorset_builder_with,
            R.layout.practice_animatorset_builder_with))
        pageModels.add(PageModel(R.string.practice_animatorset_builder_before,
            R.layout.practice_animatorset_builder_before))
        pageModels.add(PageModel(R.string.practice_animatorset_builder_after,
            R.layout.practice_animatorset_builder_after))
        pageModels.add(PageModel(R.string.practice_animatorset_settarget,
            R.layout.practice_animatorset_settarget))
        pageModels.add(PageModel(R.string.practice_animatorset_setduration,
            R.layout.practice_animatorset_setduration))
        pageModels.add(PageModel(R.string.practice_animatorset_setstartdelay,
            R.layout.practice_animatorset_setstartdelay))
        pageModels.add(PageModel(R.string.practice_path_menu,
            R.layout.practice_path_menu))
        pageModels.add(PageModel(R.string.practice_flipboard_view,
            R.layout.practice_flipboard_view))

        pageModels.add(PageModel(R.string.practice_animator_tag,
            R.layout.practice_animator_tag))
        pageModels.add(PageModel(R.string.practice_object_animator_tag,
            R.layout.practice_object_animator_tag))
        pageModels.add(PageModel(R.string.practice_set_tag,
            R.layout.practice_set_tag))
    }
}