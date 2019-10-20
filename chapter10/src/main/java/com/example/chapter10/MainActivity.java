package com.example.chapter10;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
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
        pageModels.add(new PageModel(R.string.title_path_shape_viewgroup, R.layout.practice_path_shape_viewgroup));
        pageModels.add(new PageModel(R.string.title_region_shape_viewgroup, R.layout.practice_region_shape_viewgroup));
        pageModels.add(new PageModel(R.string.title_shape_shader_view, R.layout.practice_shape_shader_view));
        pageModels.add(new PageModel(R.string.title_magnifier_view, R.layout.practice_magnifier_view));
        pageModels.add(new PageModel(R.string.title_circled_drawable, R.layout.practice_circled_drawable_viewgroup));
        pageModels.add(new PageModel(R.string.title_bitmap_drawable_convert, R.layout.practice_bitmap_drawable_convert));
        pageModels.add(new PageModel(R.string.title_bitmap_compress_format, R.layout.practice_bitmap_compress_format));
        pageModels.add(new PageModel(R.string.title_bitmapfactory_decoderesource, R.layout.practice_bitmapfactory_decoderesource));
        pageModels.add(new PageModel(R.string.title_bitmapfactory_decodebytearray, R.layout.practice_bitmapfactory_decodebytearray));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.pager);
        viewPager.setNoScroll(true);
        tabLayout = findViewById(R.id.tabLayout);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            setupViewPager();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupViewPager();
            }
        }
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
