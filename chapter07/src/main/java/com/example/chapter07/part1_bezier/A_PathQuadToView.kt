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
class A_PathQuadToView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        path.reset()
        // 这个点就是起点了，如果不指定的话是0, 0
        path.moveTo(100f, 300f)
        // x1, y1 是控制点；x2, y2 是终点
        path.quadTo(200f, 200f, 300f, 300f)
        // 它的起点是 300, 300, 因为是连续调用 quadTo 方法的
        path.quadTo(400f, 400f, 500f, 300f)
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
        path.lineTo(200f, 200f)
        path.lineTo(300f, 300f)
        path.lineTo(400f, 400f)
        path.lineTo(500f, 300f)
        canvas.drawPath(path, paint)
    }
}