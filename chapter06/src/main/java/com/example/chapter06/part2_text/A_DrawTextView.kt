package com.example.chapter06.part2_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @author wzc
 * @date 2019/9/8
 */
class A_DrawTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.strokeWidth = 0f
        val baseLine = height / 3f
        val startX = 0f
        val stopX = width.toFloat()
        // 画基线
        paint.color = Color.RED
        canvas.drawLine(startX, baseLine, stopX, baseLine, paint)

        // 写文字
        paint.color = Color.GREEN
        paint.textSize = 240f
        canvas.drawText("Hey", startX, baseLine, paint)

        // 绘制 Text 四线格的各线位置
        val fm = paint.fontMetrics
        // 在红米 Note9 Pro 上打印的 ascent 和 top 是一样的，descent 和 bottom 是一样的。
        // 打印结果：fm.ascent = -148.4375,fm.descent = 39.0625,fm.top = -168.98438,fm.bottom = 43.359375
        Log.d("DrawTextView", "fm.ascent = " + fm.ascent + "," // fm.ascent 为负数
                + "fm.descent = " + fm.descent + "," // fm.descent 为正数
                + "fm.top = " + fm.top + "," // fm.top 为负数
                + "fm.bottom = " + fm.bottom) // fm.bottom 为正数
        val ascent = baseLine + fm.ascent
        val descent = baseLine + fm.descent
        val top = baseLine + fm.top
        val bottom = baseLine + fm.bottom
        // 画 top
        paint.color = Color.BLUE
        canvas.drawLine(startX, top, stopX, top, paint)
        // 画 ascent
        paint.color = Color.GREEN
        canvas.drawLine(startX, ascent, stopX, ascent, paint)
        // 画 descent
        paint.color = Color.DKGRAY
        canvas.drawLine(startX, descent, stopX, descent, paint)
        // 画 bottom
        paint.color = Color.MAGENTA
        canvas.drawLine(startX, bottom, stopX, bottom, paint)

        paint.strokeWidth = 48f
        paint.color = Color.BLUE
        canvas.drawPoint(100f, baseLine + 100f, paint)
        paint.strokeWidth = 0f
        paint.textSize = 48f
        canvas.drawText("top",160f,baseLine + 120f, paint)

        paint.strokeWidth = 48f
        paint.color = Color.GREEN
        canvas.drawPoint(100f, baseLine + 200f, paint)
        paint.strokeWidth = 0f
        paint.textSize = 48f
        canvas.drawText("ascent",160f,baseLine + 220f, paint)

        paint.strokeWidth = 48f
        paint.color = Color.RED
        canvas.drawPoint(100f, baseLine + 300f, paint)
        paint.strokeWidth = 0f
        paint.textSize = 48f
        canvas.drawText("baseline",160f,baseLine + 320f, paint)

        paint.strokeWidth = 48f
        paint.color = Color.DKGRAY
        canvas.drawPoint(100f, baseLine + 400f, paint)
        paint.strokeWidth = 0f
        paint.textSize = 48f
        canvas.drawText("descent",160f,baseLine + 420f, paint)

        paint.strokeWidth = 48f
        paint.color = Color.MAGENTA
        canvas.drawPoint(100f, baseLine + 500f, paint)
        paint.strokeWidth = 0f
        paint.textSize = 48f
        canvas.drawText("bottom",160f,baseLine + 520f, paint)
    }
}