package com.example.chaper06;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.string.title_draw_text_line, R.layout.practice_text_line));
        pageModels.add(new PageModel(R.string.title_draw_text_rect, R.layout.practice_text_rect));
        pageModels.add(new PageModel(R.string.title_text_top_baseline, R.layout.practice_text_top_baseline));
        pageModels.add(new PageModel(R.string.title_text_center_baseline, R.layout.practice_text_center_baseline));
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
        @StringRes int titleRes;
        @LayoutRes int layoutRes;

        public PageModel(int titleRes, int layoutRes) {
            this.titleRes = titleRes;
            this.layoutRes = layoutRes;
        }
    }

}
