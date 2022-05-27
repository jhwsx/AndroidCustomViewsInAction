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
 * 绘制矩形
 *
 * @author wangzhichao
 * @since 2022/5/19
 */
class J_CanvasDrawRectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()
    private val outer = RectF()
    private val inner = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.strokeWidth = 15f

        paint.style = Paint.Style.FILL
        canvas.drawRect(10f, 100f, 300f, 300f, paint)

        paint.style = Paint.Style.STROKE
        rectF.set(10f, 400f, 300f, 600f)
        canvas.drawRect(rectF, paint)

        rectF.set(10f, 700f, 300f, 900f)
        canvas.drawRoundRect(rectF, 10f, 30f, paint)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            outer.set(400f, 100f, 700f, 400f)
            inner.set(450f, 150f, 650f, 350f)
            paint.style = Paint.Style.FILL
            canvas.drawDoubleRoundRect(outer, 5f, 20f,
                inner, 20f, 5f, paint)

            outer.set(400f, 500f, 700f, 800f)
            inner.set(450f, 550f, 650f, 750f)
            paint.style = Paint.Style.STROKE
            canvas.drawDoubleRoundRect(outer, 5f, 20f,
                inner, 20f, 5f, paint)
        }

    }
}