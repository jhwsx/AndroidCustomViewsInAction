package com.example.chapter01.part3_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * getFontSpacing()
 * Return the recommend line spacing based on the current typeface and text size.
 *
 * @author wangzhichao
 * @since 2022/5/22
 */
class I_GetFontSpacingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val text1 = "静夜思"
    private val text2 = "床前明月光，疑是地上霜。"
    private val text3 = "举头望明月，低头思故乡。"

    init {
        paint.textSize = 48f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val x = 50f
        canvas.drawText("根据推荐行距，绘制三行文本：", x, 100f, paint)

        val baseline = 200f
        canvas.drawText(text1, x, baseline, paint)
        canvas.drawText(text2, x, baseline + paint.fontSpacing, paint)
        canvas.drawText(text3, x, baseline + paint.fontSpacing * 2, paint)

    }
}