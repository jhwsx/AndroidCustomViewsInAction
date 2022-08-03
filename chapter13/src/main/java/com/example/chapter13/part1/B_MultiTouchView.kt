package com.example.chapter13.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.chapter13.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * 多点触控
 * @author wangzhichao
 * @since 2022/8/3
 */
class B_MultiTouchView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val bitmap =
        ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, 200.dp.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var currentOffsetX = 0f
    private var currentOffsetY = 0f
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var downX = 0f
    private var downY = 0f
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, currentOffsetX, currentOffsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                originalOffsetX = currentOffsetX
                originalOffsetY = currentOffsetY
            }
            MotionEvent.ACTION_MOVE -> {
                val newOffsetX = event.x - downX
                val newOffsetY = event.y - downY
                // 加上初始偏移
                currentOffsetX = newOffsetX + originalOffsetX
                currentOffsetY = newOffsetY + originalOffsetY
                invalidate()
            }
        }
        return true
    }
}