package com.example.chapter10.part2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.chapter10.R

/**
 * @author wangzhichao
 * @date 2019/12/03
 */
class BitmapSizeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private fun getBitmapSize(bitmap: Bitmap): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.allocationByteCount
        }
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            bitmap.byteCount
        } else bitmap.rowBytes * bitmap.height
    }

    init {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.cat_dog)
        Log.d("wzc", "getBitmapSize(bitmap) = " + getBitmapSize(bitmap))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Log.d("wzc", "bitmap.getAllocationByteCount() = " + bitmap.allocationByteCount)
        }
        Log.d("wzc", "bitmap.getByteCount() = " + bitmap.byteCount)
        Log.d("wzc",
            "bitmap.getRowBytes() * bitmap.getHeight() = " + bitmap.rowBytes * bitmap.height)
    }
}