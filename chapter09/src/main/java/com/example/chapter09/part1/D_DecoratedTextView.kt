package com.example.chapter09.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * 带背景的文字
 * 在 super.onDraw() 方法之前
 * @author wangzhichao
 * @since 2022/6/13
 */
class D_DecoratedTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#FFC107")
    }
    private val bounds = RectF()

    override fun onDraw(canvas: Canvas) {
        fillAndDrawBoundsByLine(canvas, 0)
        fillAndDrawBoundsByLine(canvas, layout.lineCount - 1)
        super.onDraw(canvas)
    }

    private fun fillAndDrawBoundsByLine(canvas: Canvas, lineIndex: Int) {
        bounds.apply {
            left = layout.getLineLeft(lineIndex)
            top = layout.getLineTop(lineIndex).toFloat()
            right = layout.getLineRight(lineIndex)
            bottom = layout.getLineBottom(lineIndex).toFloat()
        }
        canvas.drawRect(bounds, paint)
    }
}