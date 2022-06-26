package com.example.chapter10.part2

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.chapter10.R

/**
 * @author wangzhichao
 * @date 2019/12/03
 */
class BitmapSetDensityViewGroup(context: Context, attrs: AttributeSet?) :
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