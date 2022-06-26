package com.example.chapter10.part2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.example.chapter10.R

/**
 * 缩放图片
 * @author wangzhichao
 * @date 2019/10/29
 */
class BitmapCreateScaledBitmapView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val scaledBitmap: Bitmap
    private val source: Bitmap
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(source, 0f, 0f, null)
        canvas.drawBitmap(scaledBitmap, 0f, source.height.toFloat(), null)
    }

    init {
        source = BitmapFactory.decodeResource(resources, R.drawable.scenery)
        // Bitmap createScaledBitmap(@NonNull Bitmap src, int dstWidth, int dstHeight, boolean filter)
        // dstWidth, dstHeight, 缩放到多少尺寸
        // filter 是否给图像添加滤波效果
        scaledBitmap = Bitmap.createScaledBitmap(source, 300, 200, true)
    }
}