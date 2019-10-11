package com.example.chapter10;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private MyViewPager viewPager;
    private TabLayout tabLayout;
    private List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.string.title_gradientdrawable_shape_viewgroup, R.layout.practice_gradientdrawable_shape_viewgroup));
        pageModels.add(new PageModel(R.string.title_gradientdrawable_shape_viewgroup2, R.layout.practice_gradientdrawable_shape_viewgroup2));
        pageModels.add(new PageModel(R.string.title_shape_viewgroup1, R.layout.practice_shape_viewgroup));
        pageModels.add(new PageModel(R.string.title_oval_shape_viewgroup, R.layout.practice_oval_shape_viewgroup));
        pageModels.add(new PageModel(R.string.title_arc_shape_viewgroup, R.layout.practice_arc_shape_viewgroup));
        pageModels.add(new PageModel(R.string.title_roundrect_shape_viewgroup, R.layout.practice_roundrect_shape_viewgroup));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabLayout);
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
