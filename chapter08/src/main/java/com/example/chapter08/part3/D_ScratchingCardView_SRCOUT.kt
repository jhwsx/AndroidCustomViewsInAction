package com.example.chapter08.part3

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.HandlerThread
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.chapter08.R

/**
 * 刮刮卡效果
 * 思路：
 * 1，把卡绘制在最下面；
 * 2，构建目标图像：绘制一个和卡一样大小的透明板，把手指轨迹绘制以纯色绘制在这个透明板上；
 * 3，给画笔设置混合模式：SRC_OUT
 * 4，绘制源图像，就是刮层
 *
 * 参考：https://www.jianshu.com/p/ba45f4d6c7cc
 *
 * @author wangzhichao
 * @date 2019/09/26
 */
class D_ScratchingCardView_SRCOUT(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val cardBmp: Bitmap
    private val srcBmp: Bitmap
    private val dstBmp: Bitmap
    private val path: Path
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val c: Canvas
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
    private var lastX = 0f
    private var lastY = 0f
    private var completed = false

    init {
        cardBmp = BitmapFactory.decodeResource(resources, R.drawable.guaguaka_text)
        srcBmp = BitmapFactory.decodeResource(resources, R.drawable.guaguaka_pic)
        dstBmp = Bitmap.createBitmap(srcBmp.width, srcBmp.height, Bitmap.Config.ARGB_8888)
        path = Path()
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 40f
        c = Canvas(dstBmp)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制卡
        canvas.drawBitmap(cardBmp, 0f, 0f, paint)
        if (completed)
            return
        c.drawPath(path, paint)
        // 离屏缓冲
        // 新建图层
        val layerId =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
        // 这里是核心代码
        // 绘制目标图像，在下层，也就是手指轨迹层
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        // 给画笔设置混合模式SRC_OUT
        paint.xfermode = xfermode
        // 绘制源图像，在上层，也就是刮刮层
        canvas.drawBitmap(srcBmp, 0f, 0f, paint)
        // 清除画笔的混合模式
        paint.xfermode = null
        // 还原图层
        canvas.restoreToCount(layerId)
    }

    private val myRunnable = Runnable {
        val pixels = IntArray(srcBmp.width * srcBmp.height)
        dstBmp.getPixels(pixels, 0, srcBmp.width, 0, 0, srcBmp.width, srcBmp.height)
        val wipedPixels = pixels.count { it != 0}
        val wipedPercent = wipedPixels * 100 / pixels.size
        Log.d(TAG, "run: wipedPercent=${wipedPercent}%")
        if (wipedPercent >= 50) {
            completed = true
            invalidate()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        return when {
            completed -> super.onTouchEvent(event)
            event.action == MotionEvent.ACTION_DOWN -> {
                lastX = x
                lastY = y
                path.moveTo(x, y)
                true
            }
            event.action == MotionEvent.ACTION_MOVE -> {
                val controlX = lastX
                val controlY = lastY
                val endX = (lastX + x) / 2
                val endY = (lastY + y) / 2
                path.quadTo(controlX, controlY, endX, endY)
                invalidate()
                lastX = x
                lastY = y
                post(myRunnable)
                super.onTouchEvent(event)
            }
            else -> super.onTouchEvent(event)
        }
    }

    companion object {
        private const val TAG = "ScratchingCardView"
    }
}