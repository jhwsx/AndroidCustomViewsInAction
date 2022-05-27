package com.example.chapter08.part3

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.chapter08.R
import com.example.common.dp

/**
 * 橡皮擦效果
 * @author wangzhichao
 * @date 2019/09/26
 */
class C_EraserView_SRCOUT(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val srcBmp: Bitmap
    private val dstBmp: Bitmap
    private val eraserBmp: Bitmap
    private val path: Path
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OUT)
    private lateinit var c: Canvas
    init {
        srcBmp = BitmapFactory.decodeResource(resources, R.drawable.dog)
        dstBmp = Bitmap.createBitmap(srcBmp.width, srcBmp.height, Bitmap.Config.ARGB_8888)
        eraserBmp = BitmapFactory.decodeResource(resources, R.drawable.eraser)
        path = Path()
        paint.color = Color.RED
        paint.strokeWidth = 40f
        paint.style = Paint.Style.STROKE
        c = Canvas(dstBmp)

    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        c.drawPath(path, paint)
        // 离屏缓冲
        // 新建图层
        val layerId =
            canvas.saveLayer(30f.dp, 30f.dp,  30f.dp + width.toFloat(), 30f.dp + height.toFloat(), paint)
        // 这里是核心代码
        // 绘制目标图像，在下层，橡皮擦走过的图像
        canvas.drawBitmap(dstBmp, 30f.dp, 30f.dp, paint)
        // 给画笔设置混合模式 SRCOUT
        paint.xfermode = xfermode
        // 绘制源图像，在上层，待擦除的图像
        canvas.drawBitmap(srcBmp, 30f.dp, 30f.dp, paint)
        // 清除画笔的混合模式
        paint.xfermode = null
        // 还原图层
        canvas.restoreToCount(layerId)
        // 绘制橡皮擦
        canvas.drawBitmap(eraserBmp, lastX, lastY, paint)
    }

    private var lastX = 0f
    private var lastY = 0f
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = x
                lastY = y
                path.moveTo(x, y)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                val controlX = lastX
                val controlY = lastY
                val endX = (controlX + x) / 2
                val endY = (controlY + y) / 2
                path.quadTo(controlX, controlY, endX, endY)
                invalidate()
                lastX = x
                lastY = y
                super.onTouchEvent(event)
            }
            else -> super.onTouchEvent(event)
        }
    }
}