package com.example.chapter09.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.example.common.dp

/**
 * 在 super.onDrawForeground() 前面插入代码
 * @author wangzhichao
 * @since 2022/6/14
 */
class H_BeforeOnDrawForegroundImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val fontMetrics = Paint.FontMetrics()
    private val bounds = RectF()
    private val TEXT = "Old"
    override fun onDrawForeground(canvas: Canvas) {
        // 在 super.onDrawForeground() 的上面，让绘制内容被前景所遮盖。
        paint.getFontMetrics(fontMetrics)
        val textWidth = paint.measureText(TEXT)
        val startX = 3.dp
        bounds.apply {
            left = startX
            top = 3.dp
            right = left + textWidth
            bottom = top + fontMetrics.bottom - fontMetrics.top
        }
        paint.color = Color.parseColor("#f44336")
        canvas.drawRect(bounds, paint)
        val baseline = bounds.top - fontMetrics.top
        paint.color = Color.WHITE
        canvas.drawText(TEXT, startX, baseline, paint)
        super.onDrawForeground(canvas)
    }
}