package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * 绘制椭圆
 *
 * @author wangzhichao
 * @since 2022/5/19
 */
class K_CanvasDrawOvalView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.strokeWidth = 5f

        paint.style = Paint.Style.STROKE
        paint.color = Color.GREEN
        rectF.set(10f, 100f, 300f, 300f)
        canvas.drawRect(rectF, paint)
        paint.color = Color.RED
        canvas.drawOval(rectF, paint)

        paint.style = Paint.Style.STROKE
        rectF.set(10f, 400f, 300f, 600f)
        paint.color = Color.GREEN
        canvas.drawRect(rectF, paint)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas.drawOval(rectF, paint)

        
    }
}