package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import com.example.common.dp

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
class C_CanvasRotateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val redPaint = getPaint(Color.RED, Paint.Style.STROKE)
    private val greenPaint = getPaint(Color.GREEN, Paint.Style.FILL)
    private val rect = Rect(300, 10, 500, 100)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制一个空心矩形
        canvas.drawRect(rect, redPaint)
        canvas.save()
        when (value) {
            1 -> {
                canvas.rotate(30f) // 旋转中心是原点
                Toast.makeText(context, "以坐标原点为旋转中心", Toast.LENGTH_SHORT).show()
            }
            3 -> {
                canvas.rotate(30f, width / 2f, height / 2f) // 旋转中心是指定的点
                Toast.makeText(context, "以绘图中心点为旋转中心", Toast.LENGTH_SHORT).show()

            }
        }
        // 再绘制一个实现矩形
        canvas.drawRect(rect, greenPaint)
        canvas.restore()
    }

    private fun getPaint(@ColorInt color: Int, style: Paint.Style): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.style = style
        paint.color = color
        paint.strokeWidth = 3.dp
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