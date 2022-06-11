package com.example.chapter01

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
        @field:LayoutRes var layoutRes: Int,
    )

    init {
        // part1
        pageModels.add(PageModel(R.string.title_basic_view, R.layout.practice_basic_view))
        pageModels.add(PageModel(R.string.title_basic_antialias_view,
            R.layout.practice_basic_antialias_view))
        pageModels.add(PageModel(R.string.title_basic_setcolor_view,
            R.layout.practice_basic_setcolor_view))
        pageModels.add(PageModel(R.string.title_basic_setstyle_view,
            R.layout.practice_basic_setstyle_view))
        pageModels.add(PageModel(R.string.title_canvas_draw_background_view,
            R.layout.practice_canvas_draw_background_view))
        pageModels.add(PageModel(R.string.title_canvas_draw_line_view,
            R.layout.practice_canvas_draw_line_view))
        pageModels.add(PageModel(R.string.title_canvas_draw_lines_view,
            R.layout.practice_canvas_draw_lines_view))
        pageModels.add(PageModel(R.string.title_canvas_draw_point_view,
            R.layout.practice_canvas_draw_point_view))
        pageModels.add(PageModel(R.string.title_canvas_draw_points_view,
            R.layout.practice_canvas_draw_points_view))
        pageModels.add(PageModel(R.string.title_canvas_draw_rect_view,
            R.layout.practice_canvas_draw_rect_view))
        pageModels.add(PageModel(R.string.title_canvas_draw_oval_view,
            R.layout.practice_canvas_draw_oval_view))
        pageModels.add(PageModel(R.string.title_canvas_draw_arc_view,
            R.layout.practice_canvas_draw_arc_view))
        pageModels.add(PageModel(R.string.title_rect_point_view, R.layout.practice_rect_point_view))
        pageModels.add(PageModel(R.string.title_rect_contains_rect_view,
            R.layout.practice_rect_contains_rect_view))
        pageModels.add(PageModel(R.string.title_rect_intersects_view,
            R.layout.practice_rect_intersects_view))
        pageModels.add(PageModel(R.string.title_rect_intersect_view,
            R.layout.practice_rect_intersect_view))
        pageModels.add(PageModel(R.string.title_rect_union_view, R.layout.practice_rect_union_view))
        // part2
        pageModels.add(PageModel(R.string.title_canvas_line_path_view,
            R.layout.practice_canvas_line_path_view))
        pageModels.add(PageModel(R.string.title_canvas_arc_path_view,
            R.layout.practice_canvas_arc_path_view))
        pageModels.add(PageModel(R.string.title_canvas_add_rect_path_view,
            R.layout.practice_canvas_addrect_path_view))
        pageModels.add(PageModel(R.string.title_canvas_add_round_rect_path_view,
            R.layout.practice_canvas_addroundrect_path_view))
        pageModels.add(PageModel(R.string.title_canvas_add_circle_path_view,
            R.layout.practice_canvas_addcircle_path_view))
        pageModels.add(PageModel(R.string.title_canvas_add_oval_path_view,
            R.layout.practice_canvas_addoval_path_view))
        pageModels.add(PageModel(R.string.title_canvas_add_arc_path_view,
            R.layout.practice_canvas_addarc_path_view))
        pageModels.add(PageModel(R.string.title_canvas_fill_type_path_view,
            R.layout.practice_canvas_filltype_path_view))
        pageModels.add(PageModel(R.string.title_canvas_path_measure_view,
            R.layout.practice_path_measure_view))
        pageModels.add(PageModel(R.string.title_canvas_reset_rewind_path_view,
            R.layout.practice_canvas_resetrewind_path_view))
        pageModels.add(PageModel(R.string.title_spider_view, R.layout.practice_spider_view))
        pageModels.add(PageModel(R.string.title_youtube_play_view,
            R.layout.practice_youtube_play_view))
        pageModels.add(PageModel(R.string.title_dashboard_view,
            R.layout.practice_dashboard_view))
        pageModels.add(PageModel(R.string.title_pie_view,
            R.layout.practice_pie_view))
        pageModels.add(PageModel(R.string.title_getfillpath_view,
            R.layout.practice_get_fill_path_view))
        pageModels.add(PageModel(R.string.title_gettextpath_view,
            R.layout.practice_get_text_path_view))
        // part3 text
        pageModels.add(PageModel(R.string.title_text_paint_setstyle_view,
            R.layout.practice_text_paint_setstyle_view))
        pageModels.add(PageModel(R.string.title_text_paint_settextalign_view,
            R.layout.practice_text_paint_settextalign_view))
        pageModels.add(PageModel(R.string.title_text_paint_textstyle_view,
            R.layout.practice_text_paint_textstyle_view))
        pageModels.add(PageModel(R.string.title_canvas_drawtext_view,
            R.layout.practice_canvas_draw_text_view))
        pageModels.add(PageModel(R.string.title_canvas_drawpostext_view,
            R.layout.practice_canvas_draw_postext_view))
        pageModels.add(PageModel(R.string.title_canvas_drawtext_onpath_view,
            R.layout.practice_canvas_draw_text_onpath_view))
        pageModels.add(PageModel(R.string.title_text_paint_settypeface_view,
            R.layout.practice_text_paint_settypeface_view))
        pageModels.add(PageModel(R.string.title_text_staticlayout,
            R.layout.practice_text_staticlayout))
        pageModels.add(PageModel(R.string.title_text_getfontspacing,
            R.layout.practice_text_getfontspacing_view))
        pageModels.add(PageModel(R.string.title_text_multiline_text,
            R.layout.practice_text_multilinetext_viewgroup))
        // part4-region
        pageModels.add(PageModel(R.string.title_region_direct_construct_view,
            R.layout.practice_region_direct_construct_view))
        pageModels.add(PageModel(R.string.title_region_indirect_construct_view,
            R.layout.practice_region_indirect_construct_view))
        pageModels.add(PageModel(R.string.title_region_iterator_view,
            R.layout.practice_region_iterator_view))
        pageModels.add(PageModel(R.string.title_region_union_view,
            R.layout.practice_region_union_view))
        pageModels.add(PageModel(R.string.title_region_operation_view,
            R.layout.practice_region_operation_viewgroup))
        pageModels.add(PageModel(R.string.title_region_other_api_view,
            R.layout.practice_region_other_api_view))
        // part5-canvas
        pageModels.add(PageModel(R.string.title_canvas_translate_view,
            R.layout.practice_canvas_translate_view))
        pageModels.add(PageModel(R.string.title_canvas_screen_display_view,
            R.layout.practice_canvas_screen_display_view))
        pageModels.add(PageModel(R.string.title_canvas_rotate_view,
            R.layout.practice_canvas_rotate_view))
        pageModels.add(PageModel(R.string.title_canvas_scale_view,
            R.layout.practice_canvas_scale_view))
        pageModels.add(PageModel(R.string.title_canvas_skew_view,
            R.layout.practice_canvas_skew_view))
        pageModels.add(PageModel(R.string.practice_canvas_2d_transformation,
            R.layout.practice_canvas_2d_transformation))
        pageModels.add(PageModel(R.string.practice_matrix_2d_transformation,
            R.layout.practice_matrix_2d_transformation))
        pageModels.add(PageModel(R.string.practice_camera_3d_transformation_view,
            R.layout.practice_camera_3d_transformation_view))
        pageModels.add(PageModel(R.string.practice_flipboard_view,
            R.layout.practice_flipboard_view))
        pageModels.add(PageModel(R.string.title_canvas_clip_path_view,
            R.layout.practice_canvas_clip_path_view))
        pageModels.add(PageModel(R.string.title_canvas_clip_rect_view,
            R.layout.practice_canvas_clip_rect_view))
        pageModels.add(PageModel(R.string.title_canvas_save_restore_view,
            R.layout.practice_canvas_save_restore_view))
        pageModels.add(PageModel(R.string.title_canvas_multi_save_restore_view,
            R.layout.practice_canvas_multi_save_restore_view))
        pageModels.add(PageModel(R.string.title_canvas_restoretocount_view,
            R.layout.practice_canvas_restoretocount_view))
        pageModels.add(PageModel(R.string.title_circle_avatar_view,
            R.layout.practice_circle_avatar_view))
        pageModels.add(PageModel(R.string.title_ruler_view, R.layout.practice_ruler_view))
        pageModels.add(PageModel(R.string.title_clip_anim_view, R.layout.practice_clip_anim_view))
        pageModels.add(PageModel(R.string.title_clip_region_view,
            R.layout.practice_clip_region_view))
        // part6-控件的使用方法
        pageModels.add(PageModel(R.string.title_xml_custom_view, R.layout.practice_xml_custom_view))
        pageModels.add(PageModel(R.string.title_dynamic_custom_view,
            R.layout.practice_dynamic_custom_view))
    }
}