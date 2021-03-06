package com.example.chapter01;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<PageModel> pageModels = new ArrayList<>();

    {
        // part1
        pageModels.add(new PageModel(R.string.title_basis_view, R.layout.practice_basis_view));
        pageModels.add(new PageModel(R.string.title_basic_antialias_view, R.layout.practice_basic_antialias_view));
        pageModels.add(new PageModel(R.string.title_basic_setcolor_view, R.layout.practice_basic_setcolor_view));
        pageModels.add(new PageModel(R.string.title_basic_setstyle_view, R.layout.practice_basic_setstyle_view));
        pageModels.add(new PageModel(R.string.title_canvas_draw_background_view, R.layout.practice_canvas_draw_background_view));
        pageModels.add(new PageModel(R.string.title_canvas_draw_line_view, R.layout.practice_canvas_draw_line_view));
        pageModels.add(new PageModel(R.string.title_canvas_draw_lines_view, R.layout.practice_canvas_draw_lines_view));
        pageModels.add(new PageModel(R.string.title_canvas_draw_point_view, R.layout.practice_canvas_draw_point_view));
        pageModels.add(new PageModel(R.string.title_canvas_draw_points_view, R.layout.practice_canvas_draw_points_view));
        pageModels.add(new PageModel(R.string.title_rect_pointer_view, R.layout.practice_rect_pointer_view));
        pageModels.add(new PageModel(R.string.title_rect_contains_rect_view, R.layout.practice_rect_contains_rect_view));
        pageModels.add(new PageModel(R.string.title_rect_intersects_view, R.layout.practice_rect_intersects_view));
        pageModels.add(new PageModel(R.string.title_rect_intersect_view, R.layout.practice_rect_intersect_view));
        pageModels.add(new PageModel(R.string.title_rect_union_view, R.layout.practice_rect_union_view));
        // part2
        pageModels.add(new PageModel(R.string.title_canvas_line_path_view, R.layout.practice_canvas_line_path_view));
        pageModels.add(new PageModel(R.string.title_canvas_arc_path_view, R.layout.practice_canvas_arc_path_view));
        pageModels.add(new PageModel(R.string.title_canvas_add_rect_path_view, R.layout.practice_canvas_addrect_path_view));
        pageModels.add(new PageModel(R.string.title_canvas_add_round_rect_path_view, R.layout.practice_canvas_addroundrect_path_view));
        pageModels.add(new PageModel(R.string.title_canvas_add_circle_path_view, R.layout.practice_canvas_addcircle_path_view));
        pageModels.add(new PageModel(R.string.title_canvas_add_oval_path_view, R.layout.practice_canvas_addoval_path_view));
        pageModels.add(new PageModel(R.string.title_canvas_add_arc_path_view, R.layout.practice_canvas_addarc_path_view));
        pageModels.add(new PageModel(R.string.title_canvas_fill_type_path_view, R.layout.practice_canvas_filltype_path_view));
        pageModels.add(new PageModel(R.string.title_canvas_reset_rewind_path_view, R.layout.practice_canvas_resetrewind_path_view));
        pageModels.add(new PageModel(R.string.title_spider_view, R.layout.practice_spider_view));
        // part3
        pageModels.add(new PageModel(R.string.title_text_paint_setstyle_view, R.layout.practice_text_paint_setstyle_view));
        pageModels.add(new PageModel(R.string.title_text_paint_settextalign_view, R.layout.practice_text_paint_settextalign_view));
        pageModels.add(new PageModel(R.string.title_text_paint_textstyle_view, R.layout.practice_text_paint_textstyle_view));
        pageModels.add(new PageModel(R.string.title_canvas_drawtext_view, R.layout.practice_canvas_draw_text_view));
        pageModels.add(new PageModel(R.string.title_canvas_drawpostext_view, R.layout.practice_canvas_draw_postext_view));
        pageModels.add(new PageModel(R.string.title_canvas_drawtext_onpath_view, R.layout.practice_canvas_draw_text_onpath_view));
        pageModels.add(new PageModel(R.string.title_text_paint_settypeface_view, R.layout.practice_text_paint_settypeface_view));
        // part4-region
        pageModels.add(new PageModel(R.string.title_region_direct_construct_view, R.layout.practice_region_direct_construct_view));
        pageModels.add(new PageModel(R.string.title_region_indirect_construct_view, R.layout.practice_region_indirect_construct_view));
        pageModels.add(new PageModel(R.string.title_region_iterator_view, R.layout.practice_region_iterator_view));
        pageModels.add(new PageModel(R.string.title_region_union_view, R.layout.practice_region_union_view));
        pageModels.add(new PageModel(R.string.title_region_operation_view, R.layout.practice_region_operation_viewgroup));
        pageModels.add(new PageModel(R.string.title_region_other_api_view, R.layout.practice_region_other_api_view));
        // part5-canvas
        pageModels.add(new PageModel(R.string.title_canvas_translate_view, R.layout.practice_canvas_translate_view));
        pageModels.add(new PageModel(R.string.title_canvas_screen_display_view, R.layout.practice_canvas_screen_display_view));
        pageModels.add(new PageModel(R.string.title_canvas_rotate_view, R.layout.practice_canvas_rotate_view));
        pageModels.add(new PageModel(R.string.title_canvas_scale_view, R.layout.practice_canvas_scale_view));
        pageModels.add(new PageModel(R.string.title_canvas_skew_view, R.layout.practice_canvas_skew_view));
        pageModels.add(new PageModel(R.string.title_canvas_clip_operation_view, R.layout.practice_canvas_clip_operation_view));
        pageModels.add(new PageModel(R.string.title_ruler_view, R.layout.practice_ruler_view));
        pageModels.add(new PageModel(R.string.title_canvas_save_restore_view, R.layout.practice_canvas_save_restore_view));
        pageModels.add(new PageModel(R.string.title_canvas_multi_save_restore_view, R.layout.practice_canvas_multi_save_restore_view));
        pageModels.add(new PageModel(R.string.title_canvas_restoretocount_view, R.layout.practice_canvas_restoretocount_view));
        pageModels.add(new PageModel(R.string.title_circle_avatar_view, R.layout.practice_circle_avatar_view));
        pageModels.add(new PageModel(R.string.title_clip_anim_view, R.layout.practice_clip_anim_view));
        pageModels.add(new PageModel(R.string.title_clip_region_view, R.layout.practice_clip_region_view));
        // part6-控件的使用方法
        pageModels.add(new PageModel(R.string.title_xml_custom_view, R.layout.practice_xml_custom_view));
        pageModels.add(new PageModel(R.string.title_dynamic_custom_view, R.layout.practice_dynamic_custom_view));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabLayout);
        setupViewPager();
    }

    private void setupViewPager() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PageFragment.newInstance(pageModels.get(position).layoutRes);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    private class PageModel {
        @StringRes
        int titleRes;
        @LayoutRes
        int layoutRes;

        public PageModel(int titleRes, int layoutRes) {
            this.titleRes = titleRes;
            this.layoutRes = layoutRes;
        }
    }
}
