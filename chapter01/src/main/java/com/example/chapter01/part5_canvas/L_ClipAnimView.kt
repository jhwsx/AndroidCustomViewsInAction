package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.chapter01.R
import com.example.common.dp
import kotlin.math.roundToInt

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
class L_ClipAnimView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mtx: Matrix = Matrix()
    private val rect = Rect()

    init {
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 1, 绘制图片
        mtx.setScale(width * 1f / bitmap.width,
            height * 1f / bitmap.height)
        canvas.drawBitmap(bitmap, mtx, paint)
        // 2, 绘制矩形条
        val count = (height * 1f / RECT_HEIGHT).roundToInt() + 1
        var top = 0
        for (i in 0 until count) {
            if (i and 1 == 0) {
                rect.set(0, top, width - progress,top + RECT_HEIGHT)
            } else {
                rect.set(progress, top, width, top + RECT_HEIGHT)
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
            progress += 5
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
}