package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Canvas 的 画直线方法演示
 * Paint 的 setStyle 设置情况与线绘制的效果无关。
 * Paint 的 setStrokeWidth() 决定了绘制出直线的线宽。
 *
 * @author wangzhichao
 * @since 20-3-6
 */
class F_CanvasDrawLineView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var paint = Paint()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.isAntiAlias = true
        paint.strokeWidth = 30f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        canvas.drawLine(100f, 100f, 300f, 100f, paint)
        paint.style = Paint.Style.FILL
        canvas.drawLine(100f, 200f, 300f, 200f, paint)
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawLine(100f, 300f, 300f, 300f, paint)
        paint.strokeWidth = 100f
        canvas.drawLine(100f, 500f, 300f, 500f, paint)
        paint.strokeWidth = 0f
        canvas.drawLine(100f, 600f, 300f, 600f, paint)
    }
}