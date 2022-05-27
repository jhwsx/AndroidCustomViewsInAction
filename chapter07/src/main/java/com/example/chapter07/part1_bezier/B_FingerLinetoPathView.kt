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
 * @author wangzhichao
 * @date 2019/09/19
 */
class B_FingerLinetoPathView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
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

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                postInvalidate()
            }
        }
        return super.onTouchEvent(event)
    }


}