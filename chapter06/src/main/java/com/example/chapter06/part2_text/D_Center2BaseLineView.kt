package com.example.chapter06.part2_text

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * 给定中间线找基线
 *
 * @author wzc
 * @date 2019/9/10
 */
class D_Center2BaseLineView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = RectF()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 文字需要居中绘制的矩形区域
        paint.color = Color.MAGENTA
        paint.style = Paint.Style.FILL
        rect.set(width * 0.05f, height * 0.4f, width * 0.95f, height * 0.6f)
        canvas.drawRect(rect, paint)

        paint.color = Color.WHITE
        paint.textSize = 120f
        paint.textAlign = Paint.Align.CENTER
        val fm = paint.fontMetrics
        val x = rect.centerX()
        val baseline = rect.centerY() - (fm.top + fm.bottom) / 2
        canvas.drawText("willwaywang6", x, baseline, paint)
    }
}