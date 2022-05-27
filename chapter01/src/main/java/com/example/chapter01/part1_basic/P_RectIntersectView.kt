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
 * 判断两个矩形是否相交并且如果相交就把相交的矩形赋值给调用者，如果不相交则调用矩形不变。
 * 1，public boolean intersect(int left, int top, int right, int bottom)
 * 2，public boolean intersect(Rect r)
 * 觉得这种方法不纯粹，判断里面还有修改。
 *
 * @author wangzhichao
 * @since 20-3-7
 */
class P_RectIntersectView @JvmOverloads constructor(
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
        val intersect12 = rect1.intersect(rect2)
        // intersect12:true,intersectRect=[190,10][200,200]
        Log.d(TAG, "intersect12:" + intersect12 + ",intersectRect=" + rect1.toShortString())
        Log.d(TAG, "isIntersect(rect1, rect2) = " + isIntersect(rect1, rect2))
        val intersect23 = rect2.intersect(rect3)
        // intersect23:false,intersectRect=[190,10][400,200]
        Log.d(TAG, "intersect23:" + intersect23 + ",intersectRect=" + rect2.toShortString())
        Log.d(TAG, "isIntersect(rect2, rect3) = " + isIntersect(rect2, rect3))
    }

    // 判断两个矩形相交的思路
    // 先考虑对立事件:不相交的情况有哪些
    private fun isIntersect(rect1: Rect, rect2: Rect): Boolean {
        if (rect1.top > rect2.bottom) {
            return false
        }
        if (rect1.bottom < rect2.top) {
            return false
        }
        if (rect1.left > rect2.right) {
            return false
        }
        return if (rect1.right < rect2.left) {
            false
        } else true
        // 等价于下面的写法
//        return rect1.bottom >= rect2.top && rect1.top <= rect2.bottom
//                && rect1.left <= rect2.right && rect1.right >= rect2.left;
    }

    companion object {
        private const val TAG = "RectIntersectView"
    }
}