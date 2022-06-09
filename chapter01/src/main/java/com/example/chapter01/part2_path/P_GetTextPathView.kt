package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.common.dp

/**
 * 获取文字路径
 *
 * TODO 用途：自定义的下划线效果 https://medium.com/androiddevelopers/a-better-underline-for-android-90ba3a2e4fb
 * @author wangzhichao
 * @since 2022/5/31
 */
class P_GetTextPathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
    }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }
    private val text = "willwaywang6"
    private var drawTextPathOnly: Boolean = false
    private val fontMetrics = Paint.FontMetrics()
    private val path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textPaint.textSize = 18.dp
        canvas.drawText("点击屏幕在文字和文字路径之间切换", width / 2f, -fontMetrics.top, textPaint)
        textPaint.textSize = 56.dp
        textPaint.getFontMetrics(fontMetrics)
        val x = width / 2f
        val y = -fontMetrics.top + textPaint.fontSpacing
        if (!drawTextPathOnly) {
            canvas.drawText(text, x, y, textPaint)
        }
        textPaint.getTextPath(text, 0, text.length, x, y, path)
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        drawTextPathOnly = !drawTextPathOnly
        invalidate()
        return super.onTouchEvent(event)
    }
}