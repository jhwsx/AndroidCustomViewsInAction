package com.example.chapter08;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewPager viewPager;
    private TabLayout tabLayout;
    private List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.string.title_paint_setxfermode_porterduffxfermode, R.layout.practice_paint_setxfermode_porterduffxfermode_viewgroup));
        pageModels.add(new PageModel(R.string.title_light_book_view, R.layout.practice_light_book_view));
        pageModels.add(new PageModel(R.string.title_twitter_view, R.layout.practice_twitter_viewgroup));
        pageModels.add(new PageModel(R.string.title_roundimageview_srcin, R.layout.practice_roundimageview_srcin));
        pageModels.add(new PageModel(R.string.title_invertedimageview_srcin, R.layout.practice_invertedimageview_srcin));
        pageModels.add(new PageModel(R.string.title_eraserview_srcout, R.layout.practice_eraserview_srcout));
        pageModels.add(new PageModel(R.string.title_scratchingcardview_srcout, R.layout.practice_scratchingcardview_srcout));
        pageModels.add(new PageModel(R.string.title_roundimageview_srcatop, R.layout.practice_roundimageview_srcatop));
        pageModels.add(new PageModel(R.string.title_invertedimageview_srcatop, R.layout.practice_invertedimageview_srcatop));
        pageModels.add(new PageModel(R.string.title_roundimageview_dstin, R.layout.practice_roundimageview_dstin));
        pageModels.add(new PageModel(R.string.title_invertedimageview_dstin, R.layout.practice_invertedimageview_dstin));
        pageModels.add(new PageModel(R.string.title_areawaveview_dstin, R.layout.practice_areawaveview_dstin));
        pageModels.add(new PageModel(R.string.title_heartmapview_dstin, R.layout.practice_heartmapview_dstin));
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
