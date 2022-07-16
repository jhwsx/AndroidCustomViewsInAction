package com.example.chapter10.part3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.common.dp
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Surface - M
 * SurfaceView - V
 * SurfaceHolder - C
 *
 * @author wangzhichao
 * @since 2022/7/16
 */
class B_SurfaceGesturePath @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SurfaceView(context, attrs) {
    // 使用 newCachedThreadPool 的配置
    private val threadPoolExecutor = ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, SynchronousQueue<Runnable>())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 5.dp
        color = Color.RED
    }

    private val path = Path()

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
                // 不需要调用 invalidate() 方法了。
                drawCanvas()
            }
        }
        return super.onTouchEvent(event)
    }

    private fun drawCanvas() {
        threadPoolExecutor.execute {
            // 绘图操作放在子线程，这样不会占用主线程的资源，这是使用 SurfaceView 的正确方法。
            val surfaceHolder = holder
            // 得到 SurfaceView 中自带的缓冲画布，并对画布加锁
            val canvas = surfaceHolder.lockCanvas()
            canvas.drawPath(path, paint)
            // 将缓冲画布释放，并将所画内容更新到主线程的画布上，显示在屏幕上。
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    init {
        // 监听 Surface 生命周期
        holder.addCallback(object : SurfaceHolder.Callback2 {
            override fun surfaceCreated(holder: SurfaceHolder) {
                Log.d(TAG, "surfaceCreated: ")
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
                Log.d(TAG, "surfaceChanged: ")
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                Log.d(TAG, "surfaceDestroyed: ")
            }

            override fun surfaceRedrawNeeded(holder: SurfaceHolder) {
                Log.d(TAG, "surfaceRedrawNeeded: ")
            }
        })
    }

    companion object {
        private const val TAG = "B_SurfaceGesturePath"
    }
}