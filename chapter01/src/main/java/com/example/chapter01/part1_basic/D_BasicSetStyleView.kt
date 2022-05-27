package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Paint.setStyle 方法的使用
 *
 * @author wangzhichao
 * @since 20-3-6
 */
class D_BasicSetStyleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var paint = Paint()
    var textPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textPaint.color = Color.RED
        textPaint.textSize = 24f
        paint.isAntiAlias = true
        paint.strokeWidth = 30f
        paint.color = Color.RED
        canvas.drawCircle(200f, 200f, 70f, paint)
        canvas.drawText("默认是FILL", 400f, 200f, textPaint)

        // 设置为 FILL，绘制出的半径不包括线宽
        // setStrokeWidth 的值，对于 FILL 的效果没有影响
        paint.style = Paint.Style.FILL
        canvas.drawCircle(200f, 500f, 70f, paint)
        canvas.drawText("FILL", 400f, 500f, textPaint)

        // 设置为 STROKE，绘制出的是圆环，圆环的宽度是线宽，圆环宽度一半的位置距离圆心是半径
        paint.style = Paint.Style.STROKE
        canvas.drawCircle(200f, 800f, 70f, paint)
        canvas.drawText("STROKE", 400f, 800f, textPaint)

        // 设置为 FILL_AND_STROKE，绘制出的比 FILL，要多出半个线宽的区域
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(200f, 1100f, 70f, paint)
        canvas.drawText("FILL_AND_STROKE", 400f, 1100f, textPaint)
        paint.color = Color.BLUE
        paint.strokeWidth = 0f
        canvas.drawLine(130f, 0f, 130f, 1200f, paint)
        canvas.drawLine(270f, 0f, 270f, 1200f, paint)
    }
}