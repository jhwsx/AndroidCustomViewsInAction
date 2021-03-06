package com.example.chapter07;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyViewPager viewPager;
    private TabLayout tabLayout;
    private List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.string.title_path_quadto, R.layout.practice_path_quadto_view));
        pageModels.add(new PageModel(R.string.title_finger_lineto_path_view, R.layout.practice_finger_lineto_path_view));
        pageModels.add(new PageModel(R.string.title_finger_quadto_path_view, R.layout.practice_finger_quadto_path_view));
        pageModels.add(new PageModel(R.string.title_path_computebounds, R.layout.practice_path_computebounds));
        pageModels.add(new PageModel(R.string.title_path_rquadto, R.layout.practice_path_rquadto_view));
        pageModels.add(new PageModel(R.string.title_wave_view, R.layout.practice_wave_view));
        pageModels.add(new PageModel(R.string.title_paint_setshadowlayer_viewgroup, R.layout.practice_paint_setshadowlayer_viewgroup));
        pageModels.add(new PageModel(R.string.title_textview_setshadowlayer_viewgroup, R.layout.practice_textview_setshadowlayer_viewgroup));
        pageModels.add(new PageModel(R.string.title_paint_setmaskfilter_view, R.layout.practice_paint_setmaskfilter_view));
        pageModels.add(new PageModel(R.string.title_bitmap_extractalpha_view, R.layout.practice_bitmap_extractalpha_view));
        pageModels.add(new PageModel(R.string.title_bitmap_shadow_view, R.layout.practice_bitmap_shadow_view));
        pageModels.add(new PageModel(R.string.title_paint_setshader_bitmapshader, R.layout.practice_paint_setshader_bitmapshader_viewgroup));
        pageModels.add(new PageModel(R.string.title_telescope_view, R.layout.practice_telescope_view));
        pageModels.add(new PageModel(R.string.title_avatar_view_demo, R.layout.practice_avatar_view_demo));
        pageModels.add(new PageModel(R.string.title_avatar_view, R.layout.practice_avatar_viewgroup));
        pageModels.add(new PageModel(R.string.title_paint_setshader_lineargradient, R.layout.practice_paint_setshader_lineargradient_viewgroup));
        pageModels.add(new PageModel(R.string.title_shimmer_textview, R.layout.practice_shimmer_textview));
        pageModels.add(new PageModel(R.string.title_paint_setshader_radialgradient, R.layout.practice_paint_setshader_radialgradient_viewgroup));
        pageModels.add(new PageModel(R.string.title_ripple_view, R.layout.practice_ripple_viewgroup));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager.setNoScroll(true);
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
