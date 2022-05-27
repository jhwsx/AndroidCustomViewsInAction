package com.example.chapter06.part3_paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * 设置拐角的形状
 * @author wangzhichao
 * @date 2019/09/17
 */
class B_SetStrokeJoinView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint()
    private val path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.isAntiAlias = true
        paint.strokeWidth = 40f
        // 设置成 stroke，避免 path 被填充。
        paint.style = Paint.Style.STROKE
        paint.color = Color.GREEN

        path.reset()
        path.moveTo(100f, 100f)
        path.lineTo(450f, 100f)
        path.lineTo(X_END, 300f)
        paint.strokeJoin = Paint.Join.MITER // 尖角，是默认的
        canvas.drawPath(path, paint)

        path.reset()
        path.moveTo(100f, 400f)
        path.lineTo(450f, 400f)
        path.lineTo(X_END, 600f)
        paint.strokeJoin = Paint.Join.BEVEL // 平角
        canvas.drawPath(path, paint)

        path.reset()
        path.moveTo(100f, 700f)
        path.lineTo(450f, 700f)
        path.lineTo(X_END, 900f)
        paint.strokeJoin = Paint.Join.ROUND // 圆角
        canvas.drawPath(path, paint)
    }

    companion object {
        private const val X_END = 450f
    }
}