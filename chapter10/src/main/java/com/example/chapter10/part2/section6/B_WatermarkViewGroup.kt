package com.example.chapter10.part2.section6

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.chapter10.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 *
 * @author wangzhichao
 * @since 2022/7/16
 */
class B_WatermarkViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_watermark_viewgroup, this)
        val iv = findViewById<ImageView>(R.id.iv)
        val bitmap = createWatermarkBitmap(BitmapFactory.decodeResource(resources, R.drawable.scenery),
            BitmapFactory.decodeResource(resources, R.drawable.watermark))
        // TODO 这种方式为什么不可以呢？
//        val bitmap = createWatermarkBitmap(ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.scenery, 300.dp.toInt()),
//            ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.watermark, 50.dp.toInt()))
        iv.setImageBitmap(bitmap)
    }

    private fun createWatermarkBitmap(src: Bitmap?, watermark: Bitmap) : Bitmap? {
        if (src == null) {
            return null
        }
        val srcWidth = src.width
        val srcHeight = src.height
        val watermarkWidth = watermark.width
        val watermarkHeight = watermark.height
        val bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888)
        val c = Canvas(bitmap)
        c.drawBitmap(src, 0f, 0f, null)
        val padding = 8.dp
        c.drawBitmap(watermark, padding, padding, null)
        return bitmap
    }
}