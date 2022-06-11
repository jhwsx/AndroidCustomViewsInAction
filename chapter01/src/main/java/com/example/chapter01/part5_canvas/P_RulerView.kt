package com.example.chapter01.part5_canvas

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.example.common.dp

/**
 * 使用 Canvas.translate 来绘制一把简易的尺子。
 *
 * @author wangzhichao
 * @since 20-3-17
 */
class P_RulerView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 2f
        style = Paint.Style.STROKE
    }
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 40f
        textAlign = Paint.Align.CENTER
    }
    private val RULER_HEIGHT = 50.dp.toInt()
    private val RULER_PADDING = 8.dp
    private val LONG_SCALE_HEIGHT = 15.dp
    private val MIDDLE_SCALE_HEIGHT = 10.dp
    private val SHORT_SCALE_HEIGHT = 5.dp
    private val RULER_INNER_HORIZONTAL_PADDING = 8.dp
    private lateinit var rect: RectF
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect = RectF(RULER_PADDING, RULER_PADDING, width - RULER_PADDING, RULER_PADDING + RULER_HEIGHT)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制尺子的轮廓
        canvas.drawRect(rect, paint)
        // 绘制刻度线
        val width = rect.width() - 2 * RULER_INNER_HORIZONTAL_PADDING// 尺子上有效的绘制宽度
        val count = 40
        val scale = width / count // 每个刻度区间的宽度
        val startY = rect.bottom - 3.dp // 刻度线的纵向底部值
        val startX = RULER_INNER_HORIZONTAL_PADDING + RULER_PADDING
        val stopX = startX
        val baseline = startY - 20.dp
        for (i in 0..count) {
            if (i % 10 == 0) {
                // 画长线
                canvas.drawLine(startX, startY, stopX, startY - LONG_SCALE_HEIGHT, paint)
                val value = i / 10
                canvas.drawText(value.toString(), startX, baseline, textPaint)
            } else if (i % 5 == 0) {
                // 画中等长度的线
                canvas.drawLine(startX, startY, stopX, startY - MIDDLE_SCALE_HEIGHT, paint)
            } else {
                // 画短线
                canvas.drawLine(startX, startY, stopX, startY - SHORT_SCALE_HEIGHT, paint)
            }
            canvas.translate(scale, 0f)
        }
    }
}