package com.example.chapter10.part2

import android.content.Context
import com.example.chapter10.R
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.*
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/10/21
 */
class BitmapFactoryOptionsInSampleSizeView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private val bitmap: Bitmap?
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dstWidth = 200.dp.toInt()
    private val dstHeight = 200.dp.toInt()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (bitmap == null) {
            return
        }
        canvas.drawBitmap(bitmap, null, Rect(0, 0, dstWidth, dstHeight), paint)
    }

    private fun calSampleSize(options: BitmapFactory.Options, dstWidth: Int, dstHeight: Int): Int {
        val rawWidth = options.outWidth
        val rawHeight = options.outHeight
        var inSampleSize = 1
        if (rawWidth > dstWidth || rawHeight > dstHeight) {
            val ratioHeight = rawHeight.toFloat() / dstHeight
            val ratioWidth = rawWidth * 1f / dstWidth
            Log.d("wzc", "ratioHeight: $ratioHeight, ratioWidth: $ratioWidth")
            inSampleSize = Math.min(ratioHeight, ratioWidth).toInt()
        }
        return inSampleSize
    }

    companion object {
        fun calculateInSampleSize(
            options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int
        ): Int {
            // Raw height and width of image
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1
            if (height > reqHeight || width > reqWidth) {
                val halfHeight = height / 2
                val halfWidth = width / 2

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while (halfHeight / inSampleSize >= reqHeight
                    && halfWidth / inSampleSize >= reqWidth
                ) {
                    inSampleSize *= 2
                }
            }
            return inSampleSize
        }
    }

    init {
        val options = BitmapFactory.Options()
        // 设置为true，表示只解析图片信息，不获取图片，不分配内存。可以看到 bitmap 是 null，而 outXXXX 都是有值的。
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.book, options)
        options.inJustDecodeBounds = false
        // 这里直接写成 1，会报错：java.lang.RuntimeException: Canvas: trying to draw too large(143327232bytes) bitmap.
//        options.inSampleSize = 1;
//        options.inSampleSize = calSampleSize(options, dstWidth, dstHeight);
        options.inSampleSize = calculateInSampleSize(options, dstWidth, dstHeight)
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.book, options)
    }
}