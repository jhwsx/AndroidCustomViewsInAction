package com.example.chapter10.part3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.common.dp
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * SurfaceView 双缓冲技术
 *
 * @author wangzhichao
 * @since 2022/7/16
 */
class D_DoubleBufferingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SurfaceView(context, attrs) {
    private val surfaceHolder = holder
    // 使用 newCachedThreadPool 的配置
    private val threadPoolExecutor = ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, SynchronousQueue<Runnable>())
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        textSize = 16.dp
    }

    /**
     * 每获取一次画布写一个数字，循环 10 次。
     * @param holder SurfaceHolder
     */
    private fun drawText1(holder: SurfaceHolder) {
        for (i in 0 until 10) {
            val canvas: Canvas? = holder.lockCanvas()
            canvas?.drawText(i.toString(), i * 20.dp, 50.dp, paint)
            holder.unlockCanvasAndPost(canvas)

        }
    }

    /**
     * 每获取一次画布写一个数字，循环 10 次。
     * 这个方法可以明显地看到每次所绘制的数字
     *
     * @param holder SurfaceHolder
     */
    private fun drawText2(holder: SurfaceHolder) {
        threadPoolExecutor.execute {
            for (i in 0 until 10) {
                val canvas: Canvas? = holder.lockCanvas()
                canvas?.drawText(i.toString(), i * 20.dp, 50.dp, paint)
                holder.unlockCanvasAndPost(canvas)
//                Thread.sleep(500L)
            }
        }
    }
    init {
        surfaceHolder.addCallback(object : SurfaceHolder.Callback2 {
            override fun surfaceCreated(holder: SurfaceHolder) {
                drawText2(holder)
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {

            }

            override fun surfaceRedrawNeeded(holder: SurfaceHolder) {

            }
        })
    }
}