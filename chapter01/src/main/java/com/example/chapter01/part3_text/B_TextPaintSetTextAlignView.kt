package com.example.chapter01.part3_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Paint 的 setTextAlign 对文字的影响
 * 设置的是所要绘制的字符串与原点的相对位置.
 *
 * 需要注意的是文字对齐相对的是绘制文字的原点
 * Paint.Align.LEFT：表示原点(x,y)在文字整体的左边，文字从原点(x,y)开始绘制
 * Paint.Align.CENTER：表示原点(x,y)在文字整体的正中间，系统会根据原点(x,y)的位置和文字所在矩形大小计算出
 * 当前开始绘制的点，以使原点(x,y)正好在文字整体矩形的正中间。
 * Paint.Align.RIGHT：表示原点(x,y)在文字整体在的右边，文字绘制到原点(x,y)为止，系统会根据原点(x,y)的位置
 * 和文字所在矩形大小计算出当前开始绘制的点，以使原点(x,y)正好在文字整体矩形的右边。
 *
 * @author wangzhichao
 * @since 20-3-11
 */
class B_TextPaintSetTextAlignView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    init {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.textSize = 48f
        paint.strokeWidth = 5f
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val text = "窗前明月光"
        val startX = width * 0.5f
        paint.color = Color.GREEN
        canvas.drawLine(startX, 0f, startX, 800f, paint)
        paint.color = Color.RED
        val x: Float = startX
        paint.textAlign = Paint.Align.LEFT
        // x The x-coordinate of the origin of the text being drawn 正绘制文字原点的x坐标
        // y The y-coordinate of the baseline of the text being drawn 正绘制文字基线的y坐标
        canvas.drawText(text + "Align.LEFT", x, 200f, paint)
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText(text + "Align.CENTER", x, 400f, paint)
        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText(text + "Align.RIGHT", x, 600f, paint)

    }
}