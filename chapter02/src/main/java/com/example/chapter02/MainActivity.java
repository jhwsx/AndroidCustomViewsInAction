package com.example.chapter02;

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

// 参考文章:
// TODO 学习这篇文章
// Animation 原理分析 https://www.cnblogs.com/kross/p/4087780.html
// View Animation(视图动画)包括 Tween Animation(补间动画)和 Frame Animation(逐帧动画)
public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<PageModel> pageModels = new ArrayList<>();

    {
        // part1 视图动画标签
        pageModels.add(new PageModel(R.string.title_view_animation_tag_scale, R.layout.practice_view_animation_tag_scale));
        pageModels.add(new PageModel(R.string.title_view_animation_tag_alpha, R.layout.practice_view_animation_tag_alpha));
        pageModels.add(new PageModel(R.string.title_view_animation_tag_rotate, R.layout.practice_view_animation_tag_rotate));
        pageModels.add(new PageModel(R.string.title_view_animation_tag_translate, R.layout.practice_view_animation_tag_translate));
        pageModels.add(new PageModel(R.string.title_view_animation_tag_set, R.layout.practice_view_animation_tag_set));
        // part2 视图动画代码
        pageModels.add(new PageModel(R.string.title_view_animation_code_scale, R.layout.practice_view_animation_code_scale));
        pageModels.add(new PageModel(R.string.title_view_animation_code_alpha, R.layout.practice_view_animation_code_alpha));
        pageModels.add(new PageModel(R.string.title_view_animation_code_rotate, R.layout.practice_view_animation_code_rotate));
        pageModels.add(new PageModel(R.string.title_view_animation_code_translate, R.layout.practice_view_animation_code_translate));
        pageModels.add(new PageModel(R.string.title_view_animation_code_set, R.layout.practice_view_animation_code_set));
        pageModels.add(new PageModel(R.string.title_view_animation_code_control_listener, R.layout.practice_view_animation_code_control_listener));
        // part3 插值器
        pageModels.add(new PageModel(R.string.title_interpolator_set, R.layout.practice_interpolator_set));
        pageModels.add(new PageModel(R.string.title_interpolator, R.layout.practice_interpolator_viewgroup));
        // part4 例子
        pageModels.add(new PageModel(R.string.title_camera_stretch, R.layout.practice_camera_stretch_viewgroup));
        pageModels.add(new PageModel(R.string.title_loading, R.layout.practice_loading_viewgroup));
        pageModels.add(new PageModel(R.string.title_scanner, R.layout.practice_scanner_viewgroup));
        // part5 逐帧动画
        pageModels.add(new PageModel(R.string.title_frame_anim_xml, R.layout.practice_frame_anim_xml));
        pageModels.add(new PageModel(R.string.title_frame_anim_code, R.layout.practice_frame_anim_code));
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
