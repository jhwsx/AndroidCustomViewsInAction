package com.example.chapter07.part1_bezier

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * @author wangzhichao
 * @date 2019/09/19
 */
class E_PathrQuadToView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE

        // 使用 rQuadTo 绘制二阶贝塞尔曲线
        path.reset()
        path.moveTo(100f, 300f)
        path.rQuadTo(100f, -100f, 200f, 0f) // 相对的是 100, 300 这个点
        path.rQuadTo(100f, 100f, 200f, 0f) // 相对的是上一个的终点，即 (300, 300)
        canvas.drawPath(path, paint)

        // 绘制辅助线
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL
        canvas.drawCircle(100f, 300f, 5f, paint)
        canvas.drawCircle(200f, 200f, 5f, paint)
        canvas.drawCircle(300f, 300f, 5f, paint)
        canvas.drawCircle(400f, 400f, 5f, paint)
        canvas.drawCircle(500f, 300f, 5f, paint)
        paint.style = Paint.Style.STROKE
        path.reset()
        path.moveTo(100f, 300f)
        path.rLineTo(100f, -100f)
        path.rLineTo(100f, 100f)
        path.rLineTo(100f, 100f)
        path.rLineTo(100f, -100f)
        canvas.drawPath(path, paint)
    }
}