package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * 演示 Path 的 addCircle 方法
 * public void addCircle(float x, float y, float radius, Direction dir)
 *
 * @author wangzhichao
 * @since 20-3-8
 */
class E_CanvasAddCirclePathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    init {
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f

        textPaint.textSize = 40f
        textPaint.strokeWidth = 3f
        textPaint.style = Paint.Style.STROKE
        textPaint.color = 0x44ff0000
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        path.reset()
        path.addCircle(300f, 300f, 150f, Path.Direction.CCW)
        canvas.drawPath(path, paint)

        path.reset()
        path.addCircle(800f, 300f, 150f, Path.Direction.CW)
        canvas.drawPath(path, paint)
        val text = "好好学习，天天向上"

        path.reset()
        path.addCircle(300f, 800f, 150f, Path.Direction.CCW)
        canvas.drawPath(path, paint)
        //起点是在 3 点钟的方向
        canvas.drawTextOnPath(text, path, 0f, 9f, textPaint)

        path.reset()
        path.addCircle(800f, 800f, 150f, Path.Direction.CW)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 0f, -16f, textPaint)
    }
}