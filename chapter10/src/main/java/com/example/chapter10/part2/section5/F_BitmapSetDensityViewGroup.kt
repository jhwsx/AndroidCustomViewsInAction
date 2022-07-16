package com.example.chapter10.part2.section5

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.chapter10.R

/**
 * https://blog.csdn.net/just_keep/article/details/42457059
 * @author wangzhichao
 * @date 2019/12/03
 */
class F_BitmapSetDensityViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    companion object {
        private const val TAG = "BitmapSetDensity"
    }

    init {
        inflate(context, R.layout.layout_bitmap_setdensity_viewgroup, this)
        val iv1 = findViewById<ImageView>(R.id.iv1)
        val iv2 = findViewById<ImageView>(R.id.iv2)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.cat_dog)
        iv1.setImageBitmap(bitmap)
        val density = bitmap.density
        Log.d(TAG, "density:" + density + ", width:" + bitmap.width + ", height:" + bitmap.height)
        val scaledDensity = density * 2
        bitmap.density = scaledDensity
        Log.d(TAG,
            "scaledDensity:" + scaledDensity + ", width:" + bitmap.width + ", height:" + bitmap.height)
        iv2.setImageBitmap(bitmap)
    }
}
// 这种设置 Bitmap Density 的方式只会影响显示缩放，不会改变 Bitmap 本身在内存中的大小。而在设置 BitmapFactory 中的 Density 选项后，图片在被加载到内存中时，就已经被放大/缩小了。这是它们的区别。
// D/BitmapSetDensity: density:440, width:1015, height:813
// D/BitmapSetDensity: scaledDensity:880, width:1015, height:813