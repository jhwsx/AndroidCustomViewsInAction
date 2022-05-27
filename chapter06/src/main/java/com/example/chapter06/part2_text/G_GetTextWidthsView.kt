package com.example.chapter06.part2_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * https://rengwuxian.com/ui-1-3/
 * 2.2.5 getTextWidths(String text, float[] widths)
 * 获取字符串中每个字符的宽度，并把结果填入参数 widths。
 * @author wangzhichao
 * @since 2022/5/22
 */
class G_GetTextWidthsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val text1 = "hello"
    private val widths1 = FloatArray(text1.length)
    private val text2 = "床前明月光。"
    private val widths2 = FloatArray(text2.length)

    init {
        paint.textSize = 48f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val x = 50f
        val baseline = 100f
        canvas.drawText(text1, x, baseline, paint)
        paint.getTextWidths(text1, widths1)
        canvas.drawText(widths1.joinToString(), x, baseline + paint.fontSpacing, paint)
        canvas.drawText(text2, x, baseline + paint.fontSpacing * 2, paint)
        paint.getTextWidths(text2, widths2)
        canvas.drawText(widths2.joinToString(), x, baseline + paint.fontSpacing * 3, paint)
    }
}