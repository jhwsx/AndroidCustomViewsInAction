package com.example.chapter09;

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
        pageModels.add(new PageModel(R.string.title_ondrawdispatchdraworderviewgroup, R.layout.practice_ondrawdispatchdraworderviewgroup));
        pageModels.add(new PageModel(R.string.title_bitmap_canvas_view, R.layout.practice_bitmap_canvas_view));
        pageModels.add(new PageModel(R.string.title_xfermode_view, R.layout.practice_xfermode_view));
        pageModels.add(new PageModel(R.string.title_savelayer_useexample, R.layout.practice_savelayer_useexample));
        pageModels.add(new PageModel(R.string.title_savelayer_useexample2, R.layout.practice_savelayer_useexample2));
        pageModels.add(new PageModel(R.string.title_savelayer_useexample3, R.layout.practice_savelayer_useexample3));
        pageModels.add(new PageModel(R.string.title_savelayeralpha_useexample, R.layout.practice_savelayeralphauseexample));
        pageModels.add(new PageModel(R.string.title_matrix_save_flag_view, R.layout.practice_matrix_save_flag_view));
        pageModels.add(new PageModel(R.string.title_matrix_save_flag_view2, R.layout.practice_matrix_save_flag_view2));
        pageModels.add(new PageModel(R.string.title_matrix_save_flag_view3, R.layout.practice_matrix_save_flag_view3));
        pageModels.add(new PageModel(R.string.title_matrix_save_flag_view4, R.layout.practice_matrix_save_flag_view4));
        pageModels.add(new PageModel(R.string.title_clip_save_flag_view, R.layout.practice_clip_save_flag_view));
        pageModels.add(new PageModel(R.string.title_clip_save_flag_view2, R.layout.practice_clip_save_flag_view2));
        pageModels.add(new PageModel(R.string.title_clip_save_flag_view3, R.layout.practice_clip_save_flag_view3));
        pageModels.add(new PageModel(R.string.title_clip_save_flag_view4, R.layout.practice_clip_save_flag_view4));
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
