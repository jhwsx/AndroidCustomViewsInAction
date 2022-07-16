package com.example.chapter10.part2.section2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter10.R

/**
 * BitmapFactory.decodeResource
 * @author wangzhichao
 * @date 2019/10/20
 */
class A_BitmapFactoryDecodeResourceView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
    private val rect = Rect()
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect.set(0, 0, w, h)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, null, rect, paint)
    }
}