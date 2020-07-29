package com.example.chapter03;

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

/**
 * Property Animation(属性动画) 包括 ValueAnimator 和 ObjectAnimator
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.string.title_tween_animation_click_area_issue, R.layout.practice_tween_animation_click_area_issue));
        pageModels.add(new PageModel(R.string.title_value_animator_fix_click_area_issue, R.layout.practice_value_animator_fix_click_area_issue));
        pageModels.add(new PageModel(R.string.title_value_animator_varargs, R.layout.practice_value_animator_varargs_viewgroup));
        pageModels.add(new PageModel(R.string.title_value_animator_api, R.layout.practice_value_animator_api_group));
        pageModels.add(new PageModel(R.string.title_value_animator_bounce_loading_my, R.layout.practice_bounce_loading_viewgroup_my));
        pageModels.add(new PageModel(R.string.title_value_animator_bounce_loading_qijian, R.layout.practice_bounce_loading_viewgroup_qijian));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
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
