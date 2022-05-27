package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * 判断两个矩形是否相交的方法
 * 1，Rect 类中的静态方法 intersects(Rect a, Rect b)方法演示
 * 2，Rect 类中的成员方法 intersects(int left, int top, int right, int bottom)
 * @author wangzhichao
 * @since 20-3-7
 */
class O_RectIntersectsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val rect1 = Rect(10, 10, 200, 200)
    val rect2 = Rect(190, 10, 400, 200)
    val rect3 = Rect(10, 210, 200, 300)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.color = Color.RED
        canvas.drawRect(rect1, paint)
        paint.color = Color.GREEN
        canvas.drawRect(rect2, paint)
        paint.color = Color.YELLOW
        canvas.drawRect(rect3, paint)
        Log.d("wzc", "Rect.intersects(rect1, rect2) =" + Rect.intersects(rect1, rect2)) // true
        Log.d("wzc", "Rect.intersects(rect1, rect3) =" + Rect.intersects(rect1, rect3)) // false
        Log.d("wzc", "rect1.intersects(rect2.left, rect2.top, rect2.right, rect2.bottom) = " +
                rect1.intersects(rect2.left, rect2.top, rect2.right, rect2.bottom)) // true
        Log.d("wzc", "rect1.intersects(rect3.left, rect3.top, rect3.right, rect3.bottom) = " +
                rect1.intersects(rect3.left, rect3.top, rect3.right, rect3.bottom)) // false
    }
}