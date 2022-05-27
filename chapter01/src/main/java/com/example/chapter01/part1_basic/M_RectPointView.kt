package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Rect 是否包含某个点 contains(int x, int y) 方法演示
 *
 * 绘制一个矩形,当手指在这个矩形区域内
 * 时,矩形变为绿色;否则是红色的。
 *
 * 学习 View 中的重绘方法 invalidate 函数和 postInvalidate 函数的区别：
 * invalidate 必须在主线程调用，否则会报错；postInvalidate 可以在主线程调用，也可以
 * 在子线程调用；
 * invalidate 比 postInvalidate 效率要高些，因为前者是直接重绘界面的，而后者是通过 Handler 发消息
 * 到主线程再进行重绘界面的。
 * @author wangzhichao
 * @since 20-3-6
 */
class M_RectPointView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var rect = Rect()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE
        rect.set(100, 100, 500, 500)
        paint.color = if (rect.contains(downX, downY)) Color.GREEN else Color.RED
        canvas.drawRect(rect, paint)
    }

    // rect.contains 的实现理解：
    private fun isRectContainsPoint(rect: Rect, x: Int, y: Int): Boolean {
        // 首先判断是否是个正常的矩形
        if (!(rect.left < rect.right && rect.top < rect.bottom)) {
            return false
        }
        // 点的横坐标位于矩形左边和矩形右边之间
        // 点的纵坐标位于矩形顶边和矩形底边之间
        return if (x > rect.left && x < rect.right
            && y > rect.top && y < rect.bottom
        ) {
            true
        } else false
    }

    private var downX = 0
    private var downY = 0
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            downX = Math.round(event.x)
            downY = Math.round(event.y)
            invalidate()
            // 这里需要返回 true，因为只有返回 true，表示当前控件已经在拦截这个消息了,后续的 MOVE，UP 才会传递到当前控件中。
            // 如果返回 false,表示当前控件不需要这个消息,后续的 MOVE,UP 就不会再传入当前控件了.
            return true
        } else if (event.action == MotionEvent.ACTION_UP) {
            downX = -1
            downY = -1
            postInvalidate()
        }
        return super.onTouchEvent(event)
    }
}