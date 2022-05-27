package com.example.chapter06.part2_text

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.common.dp

/**
 * 动态文字整体居中
 * @author wangzhichao
 * @since 2022/5/25
 */
class F_DynamicCenterTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 48.dp
        textAlign = Paint.Align.CENTER
        color = Color.RED
    }
    private var text = ""
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 10.dp
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }
    private val rectF = RectF()
    private val fontMetrics = Paint.FontMetrics()
    private lateinit var va: ValueAnimator

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.CYAN
        rectF.set(width * 0.1f, height * 0.2f, width * 0.9f, height * 0.2f + width * 0.8f )
        canvas.drawOval(rectF, paint)
        val x = width / 2f
        textPaint.getFontMetrics(fontMetrics)
        val baseline = rectF.centerY() - (fontMetrics.top + fontMetrics.bottom) / 2f
        canvas.drawText(text, x, baseline, textPaint)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        va = ValueAnimator.ofInt(0, words.size - 1).apply {
            duration = 3000
            this.repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener { animation ->
                text = words[animation.animatedValue as Int]
                invalidate()
            }
            start()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        va.cancel()
    }
}