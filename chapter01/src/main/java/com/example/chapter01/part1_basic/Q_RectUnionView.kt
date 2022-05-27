package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

/**
 * Rect 类的合并方法演示：
 * 与某个矩形合并
 * public void union(int left, int top, int right, int bottom)
 * public void union(Rect r)
 * 如果传入的矩形是空的，则什么都不做；
 * 如果传入的矩形不为空，则判断调用的矩形：
 * 如果调用的矩形不为空，则进行合并，合并规则是：
 * 取最小的左边，上边赋值给调用矩形；
 * 取最大的右边，下边赋值给调用矩形
 * 如果调用的矩形为空，则返回传入的矩形
 * 与某个点合并
 * public void union(int x, int y)
 * 合并规则： x 比左边小，则取x；x比右边大，则取x；
 * y 比上边小，则取 y；y 比下边大，则取y。
 * @author wangzhichao
 * @since 20-3-7
 */
class Q_RectUnionView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect1 = Rect(50, 50, 100, 100)
    private val rect2 = Rect(250, 250, 300, 300)
    private val rect3 = Rect(50, 400, 300, 650)
    private val rect4 = Rect(50, 800, 200, 900)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 合并矩形
        paint.strokeWidth = 5f
        paint.style = Paint.Style.STROKE

        paint.color = 0x44FF0000
        canvas.drawRect(rect1, paint)
        paint.color = 0x4400FF00
        canvas.drawRect(rect2, paint)
        rect1.union(rect2)

        // 现在 rect1 的范围是：50,50,300,300
        paint.color = 0x440000FF
        canvas.drawRect(rect1, paint)

        // 合并点
        paint.color = 0x44FF00FF
        paint.strokeWidth = 5f

        canvas.drawRect(rect3, paint)
        paint.color = 0x4400ffff
        paint.strokeWidth = 10f
        canvas.drawPoint(700f, 700f, paint)
        rect3.union(700, 700)
        paint.color = 0x44ff0000
        // 现在 rect3 的范围是：50，400，700，700
        paint.strokeWidth = 5f
        canvas.drawRect(rect3, paint)

        paint.color = 0x66ff0000
        canvas.drawRect(rect4, paint)
        paint.color = 0x6600ff00
        paint.strokeWidth = 10f
        canvas.drawPoint(300f, 750f, paint)
        rect4.union(300, 750)
        paint.color = 0x66ff00ff
        paint.strokeWidth = 5f
        canvas.drawRect(rect4, paint)
    }
}