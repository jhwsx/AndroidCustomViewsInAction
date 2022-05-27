package com.example.chapter07.part1_bezier

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * 获取包裹路径的边框
 *
 * @author wangzhichao
 * @date 2019/09/19
 */
class D_PathComputeBoundsView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val rectF = RectF()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.strokeWidth = 1f
        paint.style = Paint.Style.STROKE

        paint.color = Color.GREEN
        path.reset()
        path.moveTo(100f, 100f)
        path.lineTo(200f, 200f)
        path.lineTo(300f, 200f)
        path.lineTo(400f, 400f)
        canvas.drawPath(path, paint)

        path.computeBounds(rectF, true)
        paint.color = Color.BLUE
        canvas.drawRect(rectF, paint)

        paint.color = Color.GREEN
        path.reset()
        path.addCircle(200f, 800f, 100f, Path.Direction.CCW)
        canvas.drawPath(path, paint)

        path.computeBounds(rectF, true)
        paint.color = Color.BLUE
        canvas.drawRect(rectF, paint)
    }
}