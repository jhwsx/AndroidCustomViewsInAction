package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Canvas 画布的平移操作
 * 参一：x 方向上的平移距离，（+）right(-)left,
 * 参二：y 方向上的移动距离，(+)bottom(-)top
 * public void translate(float dx, float dy)
 *
 * @author wangzhichao
 * @since 20-3-17
 */
class A_CanvasTranslateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect = Rect()
    init {
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 对 Canvas 进行平移后, 坐标系的位置也平移了.
        if (translate) {
            canvas.translate(100f, 100f)
        }
        rect.set(0, 0, 400, 220)
        canvas.drawRect(rect, paint)
    }

    var translate = false
    override fun onTouchEvent(event: MotionEvent): Boolean {
        translate = !translate
        invalidate()
        return super.onTouchEvent(event)
    }
}