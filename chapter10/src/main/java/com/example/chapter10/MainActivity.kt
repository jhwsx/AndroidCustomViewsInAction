package com.example.chapter10

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private var viewPager: MyViewPager? = null
    private var tabLayout: TabLayout? = null
    private val pageModels: MutableList<PageModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)
        viewPager!!.setNoScroll(true)
        tabLayout = findViewById(R.id.tabLayout)
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1)
        } else {
            setupViewPager()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupViewPager()
            }
        }
    }

    private fun setupViewPager() {
        viewPager!!.adapter = object : FragmentPagerAdapter(supportFragmentManager) {
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
        @field:LayoutRes var layoutRes: Int
    )

    init {
        pageModels.add(PageModel(R.string.title_gradientdrawable_shape_viewgroup,
            R.layout.practice_gradientdrawable_shape_viewgroup))
        pageModels.add(PageModel(R.string.title_gradientdrawable_shape_viewgroup2,
            R.layout.practice_gradientdrawable_shape_viewgroup2))
        pageModels.add(PageModel(R.string.title_shape_viewgroup1,
            R.layout.practice_shape_viewgroup))
        pageModels.add(PageModel(R.string.title_oval_shape_viewgroup,
            R.layout.practice_oval_shape_viewgroup))
        pageModels.add(PageModel(R.string.title_arc_shape_viewgroup,
            R.layout.practice_arc_shape_viewgroup))
        pageModels.add(PageModel(R.string.title_roundrect_shape_viewgroup,
            R.layout.practice_roundrect_shape_viewgroup))
        pageModels.add(PageModel(R.string.title_path_shape_viewgroup,
            R.layout.practice_path_shape_viewgroup))
        pageModels.add(PageModel(R.string.title_region_shape_viewgroup,
            R.layout.practice_region_shape_viewgroup))
        pageModels.add(PageModel(R.string.title_shape_shader_view,
            R.layout.practice_shape_shader_view))
        pageModels.add(PageModel(R.string.title_magnifier_view, R.layout.practice_magnifier_view))
        pageModels.add(PageModel(R.string.title_circled_drawable,
            R.layout.practice_circled_drawable_viewgroup))
        pageModels.add(PageModel(R.string.title_bitmap_drawable_convert,
            R.layout.practice_bitmap_drawable_convert))
        pageModels.add(PageModel(R.string.title_bitmap_compress_format,
            R.layout.practice_bitmap_compress_format))
        pageModels.add(PageModel(R.string.title_bitmapfactory_decoderesource,
            R.layout.practice_bitmapfactory_decoderesource))
        pageModels.add(PageModel(R.string.title_bitmapfactory_decodebytearray,
            R.layout.practice_bitmapfactory_decodebytearray))
        pageModels.add(PageModel(R.string.title_bitmapfactory_decodefiledescriptor,
            R.layout.practice_bitmapfactory_decodefiledescriptor))
        pageModels.add(PageModel(R.string.title_bitmapfactory_decodestream,
            R.layout.practice_bitmapfactory_decodestream))
        pageModels.add(PageModel(R.string.title_bitmapfactory_options_injustdecodebounds,
            R.layout.practice_bitmapfactoryoptions_injustdecodebounds))
        pageModels.add(PageModel(R.string.title_bitmapfactory_options_insamplesize,
            R.layout.practice_bitmapfactoryoptions_insamplesize))
        pageModels.add(PageModel(R.string.title_densitydpi_view, R.layout.practice_densitydpi_view))
        pageModels.add(PageModel(R.string.title_sdcardbitmap_drawablebitmap,
            R.layout.practice_sdcardbitmap_drawablebitmap_view))
        pageModels.add(PageModel(R.string.title_bitmapfactory_options_inscaled,
            R.layout.practice_bitmapfactory_options_inscaled))
        pageModels.add(PageModel(R.string.title_bitmapfactoryoptionsindensityintargetdensityview,
            R.layout.practice_bitmapfactoryoptionsindensityintargetdensityview))
        pageModels.add(PageModel(R.string.title_bitmapfactoryoptionsinpreferredconfigview,
            R.layout.practice_bitmapfactoryoptionsinpreferredconfigview))
        pageModels.add(PageModel(R.string.title_bitmap_createbitmap_lineargradientview,
            R.layout.practice_bitmap_createbitmap_lineargradientview))
        pageModels.add(PageModel(R.string.title_bitmap_createbitmap_clipimageview,
            R.layout.practice_bitmapcreatebitmapclipimageview))
        pageModels.add(PageModel(R.string.title_bitmap_createbitmap_colorview,
            R.layout.practice_bitmapcreatebitmapcolorview))
        pageModels.add(PageModel(R.string.title_bitmap_createscaledbitmap,
            R.layout.practice_bitmapcreatescaledbitmapview))
        pageModels.add(PageModel(R.string.title_bitmap_copy, R.layout.practice_bitmap_copy_view))
        pageModels.add(PageModel(R.string.title_bitmap_extractalpha,
            R.layout.practice_bitmap_extractalpha))
        pageModels.add(PageModel(R.string.title_bitmap_extractalpha_withparms,
            R.layout.practice_bitmap_extractalpha_withparams))
        pageModels.add(PageModel(R.string.title_bitmap_extractalpha_stroke_image,
            R.layout.practice_bitmap_extractalpha_stroke_image_viewgroup))
        pageModels.add(PageModel(R.string.title_bitmap_size_view,
            R.layout.practice_bitmap_size_view))
        pageModels.add(PageModel(R.string.title_bitmap_setdensity,
            R.layout.practice_bitmap_setdensity_viewgroup))
        pageModels.add(PageModel(R.string.title_bitmap_setpixel,
            R.layout.practice_bitmap_setpixel_viewgroup))
        pageModels.add(PageModel(R.string.title_bitmap_compress,
            R.layout.practice_bitmap_compress_viewgroup))
        pageModels.add(PageModel(R.string.title_paint_antialiasflag_invalid,
            R.layout.practice_paint_antialiasflag_invalid_view))
    }
}