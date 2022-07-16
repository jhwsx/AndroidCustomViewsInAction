package com.example.chapter10.part2.section5

import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.chapter10.R
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/12/03
 */
class E_BitmapSizeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private fun getBitmapSize(bitmap: Bitmap): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 获取 Bitmap 所分配的内存。
             bitmap.allocationByteCount
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            bitmap.byteCount
        } else bitmap.rowBytes * bitmap.height
    }
    private val source = """
        private fun getBitmapSize(bitmap: Bitmap): Int {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // 获取 Bitmap 所分配的内存。
                 bitmap.allocationByteCount
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
                bitmap.byteCount
            } else bitmap.rowBytes * bitmap.height
        }
    """.trimIndent()

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        textSize = 16.dp
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        StaticLayout.Builder.obtain(source, 0, source.length, textPaint, width).apply {
            setAlignment(Layout.Alignment.ALIGN_NORMAL)
            setIndents(intArrayOf(8.dp.toInt()), null)
        }.build().draw(canvas)
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