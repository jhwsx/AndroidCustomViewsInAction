package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorInt

/**
 * Canvas 的旋转操作
 *
 * // 没有指定旋转中心，就是原点
 * public void rotate(float degrees)
 *
 * public final void rotate(float degrees, float px, float py)
 *
 * 理解指定旋转点的文章: https://blog.csdn.net/iasxk/article/details/17411381
 *
 * @author wangzhichao
 * @since 20-3-17
 */
class CanvasRotateView : View {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val redPaint = getPaint(Color.RED, Paint.Style.STROKE, 3)
        val greenPaint = getPaint(Color.GREEN, Paint.Style.FILL, 3)
        val rect = Rect(300, 10, 500, 100)
        // 绘制一个空心矩形
        canvas.drawRect(rect, redPaint)
        when (value) {
            1 ->                 // 旋转中心是原点
                canvas.rotate(30f)
            3 ->                 // 旋转中心是指定的点
                canvas.rotate(30f, (width / 2).toFloat(), (height / 2).toFloat())
            else -> {}
        }
        // 再绘制一个实现矩形
        canvas.drawRect(rect, greenPaint)
    }

    fun getPaint(@ColorInt color: Int, style: Paint.Style?, strokeWidth: Int): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.style = style
        paint.color = color
        paint.strokeWidth = strokeWidth.toFloat()
        return paint
    }

    private var value = 0
    private var count = 0
    override fun onTouchEvent(event: MotionEvent): Boolean {
        value = count % 4
        count++
        invalidate()
        return super.onTouchEvent(event)
    }
}