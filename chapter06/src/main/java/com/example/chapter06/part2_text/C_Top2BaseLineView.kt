package com.example.chapter06.part2_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * 给定顶线找基线
 * @author wzc
 * @date 2019/9/10
 */
class C_Top2BaseLineView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val paint = Paint()
    init {
        paint.textSize = 120f
        paint.textAlign = Paint.Align.LEFT
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val text = "willwaywang6"
        val top = 200f
        val x = 0f
        val stopX = width.toFloat()
        // 画 top
        paint.color = Color.BLUE
        canvas.drawLine(x, top, stopX, top, paint)

        // 计算出 baseline 线的位置
        val fm = paint.fontMetrics
        // fm.top = top - baseline
        val baseline = top - fm.top

        // 画基线
        paint.color = Color.RED
        canvas.drawLine(x, baseline, stopX, baseline, paint)

        // 写文字
        paint.color = Color.GREEN
        canvas.drawText(text, x, baseline, paint)
    }
}