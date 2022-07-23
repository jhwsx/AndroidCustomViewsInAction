package com.example.chapter10.part3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import com.example.common.dp

/**
 * 直接点击 SurfaceView，看不到源码，需要自己去找源码查看。
 *
 * @author wangzhichao
 * @since 2022/7/16
 */
class A_SurfaceGesturePath @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SurfaceView(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 5.dp
        color = Color.RED
    }

    private val path = Path()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
        Log.d(TAG, "onDraw: ")
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                invalidate()
                Log.d(TAG, "onTouchEvent: invalidate() called")
            }
        }
        return super.onTouchEvent(event)
    }

    init {
        // 默认情况下是调用了setWillNotDraw(true)的，这说明 SurfaceView 的开发人员并不希望我们通过重写 onDraw()
        // 函数来绘制 SurfaceView 的控制界面，而是要使用 SurfaceView 独有的特性（双缓冲机制）来操作画布。
        setWillNotDraw(false)
    }

    companion object {
        private const val TAG = "A_SurfaceGesturePath"
    }
}