package com.example.chapter13.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.chapter13.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * 配合型多点触控
 * @author wangzhichao
 * @since 2022/8/4
 */
class C_CooperateMultiTouchView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

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
        var sumX = 0f
        var sumY = 0f
        var pointerCount = event.pointerCount
        val isPointerUp = event.actionMasked == MotionEvent.ACTION_POINTER_UP
        for (i in 0 until pointerCount) {
            if (!(isPointerUp && i == event.actionIndex)) {
                sumX += event.getX(i)
                sumY += event.getY(i)
            }
        }
        if (isPointerUp) {
            pointerCount -= 1
        }
        val focusX: Float = sumX / pointerCount
        val focusY: Float = sumY / pointerCount
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN, MotionEvent.ACTION_POINTER_UP -> {
                downX = focusX
                downY = focusY
                originalOffsetX = currentOffsetX
                originalOffsetY = currentOffsetY
            }
            MotionEvent.ACTION_MOVE -> {
                val newOffsetX = focusX - downX
                val newOffsetY = focusY - downY
                // 加上初始偏移
                currentOffsetX = newOffsetX + originalOffsetX
                currentOffsetY = newOffsetY + originalOffsetY
                invalidate()
            }
        }
        return true
    }
}