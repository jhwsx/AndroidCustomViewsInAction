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
 * 接力型多点触控
 * @author wangzhichao
 * @since 2022/8/3
 */
class B_RelayMultiTouchView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val bitmap =
        ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, 200.dp.toInt())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var currentOffsetX = 0f
    private var currentOffsetY = 0f
    private var originalOffsetX = 0f
    private var originalOffsetY = 0f
    private var downX = 0f
    private var downY = 0f
    private var trackingPointerId = 0
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, currentOffsetX, currentOffsetY, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                trackingPointerId = event.getPointerId(0)
                downX = event.x
                downY = event.y
                originalOffsetX = currentOffsetX
                originalOffsetY = currentOffsetY
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = event.actionIndex
                trackingPointerId = event.getPointerId(actionIndex)
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
                originalOffsetX = currentOffsetX
                originalOffsetY = currentOffsetY
            }
            MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val pointerId = event.getPointerId(actionIndex)
                if (pointerId == trackingPointerId) {
                    val newPointerIndex = if (actionIndex == event.pointerCount - 1) {
                        event.pointerCount - 2
                    } else {
                        event.pointerCount - 1
                    }
                    trackingPointerId = event.getPointerId(newPointerIndex)
                    downX = event.getX(newPointerIndex)
                    downY = event.getY(newPointerIndex)
                    originalOffsetX = currentOffsetX
                    originalOffsetY = currentOffsetY
                }
            }
            MotionEvent.ACTION_MOVE -> {
                for (i in 0 until event.pointerCount) {
                    val x = event.getX(i)
                    val y = event.getY(i)
                    Log.d("TAG", "onTouchEvent: $i, x=$x, y=$y")
                }
                // 获取刚刚按下或抬起的手指的索引
//                val actionIndex = event.actionIndex
//                val x = event.getX(actionIndex)
                val pointerIndex = event.findPointerIndex(trackingPointerId)
                val newOffsetX = event.getX(pointerIndex) - downX
                val newOffsetY = event.getY(pointerIndex) - downY
                // 加上初始偏移
                currentOffsetX = newOffsetX + originalOffsetX
                currentOffsetY = newOffsetY + originalOffsetY
                invalidate()
            }
        }
        return true
    }
}