package com.example.chapter07.part7_sweepgradient

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.SweepGradient
import android.util.AttributeSet
import android.view.View
import com.example.common.dp

/**
 * 扫描渐变
 * @author wangzhichao
 * @since 2022/5/27
 */
class A_PaintSetShaderSweepGradientView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var sweepGradient1: SweepGradient
    private lateinit var sweepGradient2: SweepGradient
    private lateinit var sweepGradient3: SweepGradient
    private lateinit var sweepGradient4: SweepGradient
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        sweepGradient1 = SweepGradient(width / 4f, 120.dp, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"))
        sweepGradient2 = SweepGradient(width * 3 / 4f, 120.dp, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"))
        sweepGradient3 = SweepGradient(width / 4f, 250.dp, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"))
        sweepGradient4 = SweepGradient(width * 3 / 4f, 250.dp, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"))

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style = Paint.Style.FILL
        paint.shader = sweepGradient1
        canvas.drawCircle(width / 4f, 120.dp, 50.dp, paint)
        paint.shader = null

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10.dp
        paint.shader = sweepGradient2
        canvas.drawCircle(width * 3 / 4f, 120.dp, 50.dp, paint)
        paint.shader = null

        paint.style = Paint.Style.FILL
        paint.shader = sweepGradient3
        canvas.drawRect(
            width / 4f - 50.dp,
            250.dp - 50.dp,
            width / 4f + 50.dp,
            250.dp + 50.dp,
            paint)
        paint.shader = null

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10.dp
        paint.shader = sweepGradient4
        canvas.drawRect(
            width * 3 / 4f - 50.dp,
            250.dp - 50.dp,
            width * 3 / 4f + 50.dp,
            250.dp + 50.dp,
            paint)
        paint.shader = null
    }
}