package com.example.chapter06.part3_paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @author wangzhichao
 * @date 2019/09/17
 */
class A_SetStrokeCapView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    init {
        paint.isAntiAlias = true
        paint.color = Color.GREEN
        paint.strokeWidth = 40f
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.strokeCap = Paint.Cap.BUTT
        canvas.drawLine(50f, 100f, 300f, 100f, paint)
        paint.strokeCap = Paint.Cap.SQUARE
        canvas.drawLine(50f, 200f, 300f, 200f, paint)
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawLine(50f, 300f, 300f, 300f, paint)
        paint.color = Color.RED
        paint.strokeWidth = 1f
        canvas.drawLine(50f, 0f, 50f, 400f, paint)
        canvas.drawLine(300f, 0f, 300f, 400f, paint)
    }
}