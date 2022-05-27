package com.example.chapter07.part1_bezier

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * 关键是要找出控制点和终点
 * @author wangzhichao
 * @date 2019/09/19
 */
class C_FingerQuadtoPathView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val path = Path()
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val gestureDetector: GestureDetector

    init {
        paint.style = Paint.Style.STROKE
        paint.color = Color.GREEN
        gestureDetector =
            GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTapEvent(e: MotionEvent): Boolean {
                    // 双击清除手势轨迹
                    reset()
                    return super.onDoubleTapEvent(e)
                }
            })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    fun reset() {
        path.reset()
        postInvalidate()
    }

    private var lastX = 0f
    private var lastY = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = x
                lastY = y
                // 起始点
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                // 当前触点和上一个触点的中点作为结束点。
                val endX = (lastX + x) / 2
                val endY = (lastY + y) / 2
                // 将上一个触点作为控制点
                path.quadTo(lastX, lastY, endX, endY) // endX，endY 会作为下一个贝塞尔曲线的起始点。
                postInvalidate()
                lastX = x
                lastY = y
            }
        }
        return super.onTouchEvent(event)
    }


}