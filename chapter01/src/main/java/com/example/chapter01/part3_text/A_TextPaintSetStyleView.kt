package com.example.chapter01.part3_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Paint填充样式（setStyle）对文字的影响
 * 可以看到：
 * Paint.Style.FILL 是实心填充的；
 * Paint.Style.STROKE 是描边的，
 * Paint.Style.FILL_AND_STROKE 也是实心填充的，但是比 FILL 要大半个 strokeWidth，相当于前面两个的叠加。
 *
 * @author wangzhichao
 * @since 20-3-11
 */
class A_TextPaintSetStyleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    init {
        paint.color = Color.RED
        paint.strokeWidth = 3f
        paint.textSize = 64f
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val text = "床前明月光"
        paint.style = Paint.Style.FILL
        canvas.drawText("$text-FILL", 16f, 100f, paint)
        paint.style = Paint.Style.STROKE
        canvas.drawText("$text-STROKE", 16f, 300f, paint)
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawText("$text-FILL_AND_STROKE", 16f, 500f, paint)
    }
}