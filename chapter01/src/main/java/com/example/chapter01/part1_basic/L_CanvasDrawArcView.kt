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
 * 绘制圆弧
 *
 * @author wangzhichao
 * @since 2022/5/19
 */
class L_CanvasDrawArcView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.strokeWidth = 10f

        paint.style = Paint.Style.STROKE
        paint.color = Color.GREEN
        rectF.set(100f, 100f, 400f, 300f)
        canvas.drawRect(rectF, paint)
        paint.color = Color.RED
        canvas.drawArc(rectF, 0f, 120f, true, paint)

        rectF.set(500f, 100f, 800f, 300f)
        paint.color = Color.GREEN
        canvas.drawRect(rectF, paint)
        paint.color = Color.RED
        canvas.drawArc(rectF, 0f, 120f, false, paint)

        paint.style = Paint.Style.STROKE
        rectF.set(100f, 400f, 400f, 600f)
        paint.color = Color.GREEN
        canvas.drawRect(rectF, paint)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas.drawArc(rectF, 0f, 120f, true, paint)
        paint.style = Paint.Style.STROKE
        rectF.set(500f, 400f, 800f, 600f)
        paint.color = Color.GREEN
        canvas.drawRect(rectF, paint)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        canvas.drawArc(rectF, 0f, 120f, false, paint)

        paint.style = Paint.Style.STROKE
        rectF.set(100f, 700f, 400f, 900f)
        paint.color = Color.GREEN
        canvas.drawRect(rectF, paint)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawArc(rectF, 0f, 120f, true, paint)
        paint.style = Paint.Style.STROKE
        rectF.set(500f, 700f, 800f, 900f)
        paint.color = Color.GREEN
        canvas.drawRect(rectF, paint)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawArc(rectF, 0f, 120f, false, paint)
        
    }
}