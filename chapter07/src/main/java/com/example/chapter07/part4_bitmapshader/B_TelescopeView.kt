package com.example.chapter07.part4_bitmapshader

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.chapter07.R
import com.example.common.dp

/**
 * 望远镜效果
 *
 * @author wangzhichao
 * @date 2019/09/21
 */
class B_TelescopeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
    private var canvasBitmap: Bitmap? = null
    private var bitmapShader: BitmapShader? = null
    private var cx = -1f
    private var cy = -1f
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (canvasBitmap == null) {
            // 创建一张与背景一样大的空白位图
            canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            // 把背景图画到空白位图上
            val bgCanvas = Canvas(canvasBitmap!!)
            bgCanvas.drawBitmap(bitmap, null, Rect(0, 0, width, height), paint)
        }
        if (bitmapShader == null) {
            bitmapShader =
                BitmapShader(canvasBitmap!!, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        }
        paint.shader = bitmapShader
        if (cx != -1f) {
            // 真正会绘制出来的部分是一个圆形的部分
            canvas.drawCircle(cx, cy, 100.dp, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "ACTION_DOWN")
                cx = event.x
                cy = event.y
                return true // 这里需要返回 true，这样后续的事件才会陆续过来。
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "ACTION_MOVE")
                cx = event.x
                cy = event.y
            }
            else -> {
                cx = -1f
                cy = -1f
            }
        }
        invalidate()
        return super.onTouchEvent(event)
    }

    companion object {
        private const val TAG = "TelescopeView"
    }

}