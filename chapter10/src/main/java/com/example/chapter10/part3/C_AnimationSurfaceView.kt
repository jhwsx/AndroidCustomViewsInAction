package com.example.chapter10.part3

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.chapter10.R
import java.util.concurrent.SynchronousQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * 动态背景效果
 *
 * @author wangzhichao
 * @since 2022/7/16
 */
class C_AnimationSurfaceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : SurfaceView(context, attrs) {
    // 使用 newCachedThreadPool 的配置
    private val threadPoolExecutor = ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, SynchronousQueue<Runnable>())
    private enum class State {
        FORWARD_LEFT, FORWARD_RIGHT
    }
    private val surfaceHolder = holder
    private var surfaceWidth: Int = 0
    private var surfaceHeight: Int = 0
    private var currentBitmapX = 0f
    private var currentState = State.FORWARD_LEFT
    private var surfaceExists = false
    private lateinit var bitmap: Bitmap
    private val step = 1
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        surfaceWidth = w
        surfaceHeight = h
        // 准备好要移动的图片
        val scenery = BitmapFactory.decodeResource(resources, R.drawable.scenery)
        bitmap = Bitmap.createScaledBitmap(scenery, 3 * surfaceWidth / 2,
            surfaceHeight, true)
    }

    init {
        surfaceHolder.addCallback(object : SurfaceHolder.Callback2 {
            override fun surfaceCreated(holder: SurfaceHolder) {
                surfaceExists = true
                // 开启动画
                startAnimation()
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                surfaceExists = false
            }

            override fun surfaceRedrawNeeded(holder: SurfaceHolder) {
            }
        })
    }

    private fun startAnimation() {
        threadPoolExecutor.execute {
            while (surfaceExists) {
                val canvas = surfaceHolder.lockCanvas()
                drawView(canvas)
                surfaceHolder.unlockCanvasAndPost(canvas)
                TimeUnit.MILLISECONDS.sleep(20L)
                updateBitmapXAndState()
            }
        }
    }

    private fun updateBitmapXAndState() {
        when (currentState) {
            State.FORWARD_RIGHT -> currentBitmapX += step
            State.FORWARD_LEFT -> currentBitmapX -= step
        }
        if (currentBitmapX < -surfaceWidth / 2f) {
            currentBitmapX = -surfaceWidth / 2f
            currentState = State.FORWARD_RIGHT
        }
        if (currentBitmapX > 0) {
            currentBitmapX = 0f
            currentState = State.FORWARD_LEFT
        }
    }

    private fun drawView(canvas: Canvas) {
        // TODO 这个不加也没事啊？
//        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        canvas.drawBitmap(bitmap, currentBitmapX, 0f, null)
    }
}