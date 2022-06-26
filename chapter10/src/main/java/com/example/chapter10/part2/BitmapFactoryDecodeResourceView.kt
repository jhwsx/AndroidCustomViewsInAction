package com.example.chapter10.part2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter10.R

/**
 * @author wangzhichao
 * @date 2019/10/20
 */
class BitmapFactoryDecodeResourceView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap: Bitmap
    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, null, Rect(0, 0, width, height), paint)
    }
}