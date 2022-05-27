package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.chapter01.R
import com.example.chapter01.part5_canvas.CanvasRestoreToCountView
import com.example.chapter01.part5_canvas.CanvasScaleView
import com.example.chapter01.part5_canvas.ClipAnimView
import com.example.chapter01.part5_canvas.ClipPathAnimView

/**
 * 裁剪动画示例(自己实现的)
 * 思路：
 * 1，先绘制图片出来,铺满屏幕是使用 Matrix 设置的缩放比例；
 * 2，在图片上遮盖一条一条的矩形条；
 * 3，遍历矩形条，偶数索引的向左缩放，奇数索引的向右缩放。
 *
 * @author wangzhichao
 * @since 20-3-18
 */
class ClipAnimView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap: Bitmap
    private val paint: Paint
    private val matrix: Matrix
    private var width = 0
    private val rect = Rect()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        width = getWidth()
        // 1, 绘制图片
        matrix.setScale(width * 1f / bitmap.width,
            height * 1f / bitmap.height)
        canvas.drawBitmap(bitmap, matrix, paint)
        // 2, 绘制矩形条
        val count = Math.round(height * 1f / RECT_HEIGHT) + 1
        var top = 0
        for (i in 0 until count) {
            if (i and 1 == 0) {
                rect[0, top, getWidth() - progress] = top + RECT_HEIGHT
            } else {
                rect[progress, top, getWidth()] = top + RECT_HEIGHT
            }
            top += RECT_HEIGHT
            canvas.drawRect(rect, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        post(MyRunnable())
        invalidate()
        return super.onTouchEvent(event)
    }

    private var progress = 0

    private inner class MyRunnable : Runnable {
        override fun run() {
            progress += 10
            if (progress > width) {
                progress = width
                invalidate()
            } else {
                invalidate()
                post(this)
            }
        }
    }

    companion object {
        private const val RECT_HEIGHT = 40
    }

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
        paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        matrix = Matrix()
    }
}