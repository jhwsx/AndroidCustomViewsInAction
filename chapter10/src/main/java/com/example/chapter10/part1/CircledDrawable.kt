package com.example.chapter10.part1

import android.graphics.drawable.Drawable
import android.graphics.*
import android.util.Log
import com.example.common.dp

/**
 * 圆角 Drawable
 * 思路：
 * 1，传入 bitmap 对象
 * 2，把这个 bitmap 设置为 Shader
 * 3，绘制和印章一样大小的圆角矩形
 * @author wangzhichao
 * @date 2019/10/15
 */
class CircledDrawable(private val bitmap: Bitmap) : Drawable() {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var rect: RectF? = null
    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
        val shader =
            BitmapShader(Bitmap.createScaledBitmap(bitmap, right - left, bottom - top, true),
                Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
        rect = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
        Log.d(TAG, "rect = " + rect!!.toShortString())
    }

    override fun draw(canvas: Canvas) {
        canvas.drawRoundRect(rect!!, 10.dp, 10.dp, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun getIntrinsicWidth(): Int {
        return bitmap.width
    }

    override fun getIntrinsicHeight(): Int {
        return bitmap.height
    }

    companion object {
        private const val TAG = "CircledDrawable"
    }
}