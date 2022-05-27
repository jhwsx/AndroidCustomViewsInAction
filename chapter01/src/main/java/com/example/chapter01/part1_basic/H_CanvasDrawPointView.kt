package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Canvas 的 画点方法演示
 * Paint.setStyle 的值与绘制出的点效果无关
 * Paint.setStrokeWidth 的值决定了点的大小
 *
 * @author wangzhichao
 * @since 20-3-6
 */
class H_CanvasDrawPointView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.strokeWidth = 30f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        canvas.drawPoint(100f, 100f, paint)
        paint.style = Paint.Style.FILL
        canvas.drawPoint(100f, 200f, paint)
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawPoint(100f, 300f, paint)
        paint.strokeWidth = 60f
        canvas.drawPoint(100f, 400f, paint)
        paint.strokeWidth = 120f
        canvas.drawPoint(100f, 600f, paint)
    }
}