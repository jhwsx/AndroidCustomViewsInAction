package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Rect 是否包含某个 Rect 方法演示
 *
 *
 * 手指在屏幕一个角开始点击，滑动到对角构成一个矩形，如果这个矩形包含在预定的矩形框内，
 * 预定的矩形框就是绿色，否则就是红色
 *
 * @author wangzhichao
 * @since 20-3-6
 */
class N_RectContainsRectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = Rect()
    private val r = Rect()
    private var action = MotionEvent.ACTION_DOWN

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        rect.set(100, 100, 500, 500)
        paint.color = when {
            action == MotionEvent.ACTION_DOWN -> {
                Log.d("wzc", "onDraw if")
                Color.RED
            }
            rect.contains(r) -> {
                Log.d("wzc", "onDraw else green")
                Color.GREEN
            }
            else -> {
                Log.d("wzc", "onDraw else red")
                Color.RED
            }
        }
        canvas.drawRect(rect, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        if (event.action == MotionEvent.ACTION_DOWN) {
            action = MotionEvent.ACTION_DOWN
            Log.d("wzc", "down")
            invalidate()
            r.left = x
            r.top = y
            return true
        } else if (event.action == MotionEvent.ACTION_UP) {
            action = MotionEvent.ACTION_UP
            r.right = x
            r.bottom = y
            Log.d("wzc", "r = " + r.toShortString())
            r.sort()
            Log.d("wzc", "up")
            invalidate()
        }
        return super.onTouchEvent(event)
    }
}