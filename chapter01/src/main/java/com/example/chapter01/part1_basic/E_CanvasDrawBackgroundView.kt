package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Canvas 绘制背景的方法演示
 *
 * @author wangzhichao
 * @since 20-3-6
 */
class E_CanvasDrawBackgroundView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), View.OnClickListener {
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2f
        val centerY = height / 2f
        when (remainder) {
            0 -> {
                // 需要传入必须是 8 位的 0xAARRGGBB 的形式。
                // 如果传入的是 0xRRGGBB，那么没有效果。
                // 还可以传入 Color.RED 等预定义的一些值。
                canvas.drawColor(-0x10000)
                canvas.drawText("canvas.drawColor(0xFFFF0000)", centerX, centerY, textPaint)
            }
            1 -> {
                canvas.drawARGB(100, 0, 255, 0)
                canvas.drawText("canvas.drawARGB(100, 0, 255, 0)", centerX, centerY, textPaint)
            }
            2 -> {
                canvas.drawRGB(255, 255, 0)
                canvas.drawText("canvas.drawRGB(255, 255, 0)", centerX, centerY, textPaint)
            }
            else -> {}
        }
    }

    private var count = 0
    private var remainder = 0
    override fun onClick(v: View) {
        remainder = count % 3
        invalidate()
        count++
    }

    init {
        textPaint.color = Color.BLACK
        textPaint.textSize = 36f
        textPaint.textAlign = Paint.Align.CENTER
        setOnClickListener(this)
    }
}