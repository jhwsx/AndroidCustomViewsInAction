package com.example.chapter11

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * ColorMatrix 设置，重置函数
 * @author wangzhichao
 * @since 2022/6/3
 */
class H_ColorMatrixSetResetView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val fontMetrics = Paint.FontMetrics()
    private val avatarSize = 150.dp
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, avatarSize.toInt())
    private val colorMatrix = ColorMatrix(floatArrayOf(
        0f, 1f, 0f, 0f, 0f,
        1f, 0f, 0f, 0f, 0f,
        0f, 0f, 1f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f,
    ))
    private val cm = ColorMatrix()
    private var colorMatrixColorFilter :ColorMatrixColorFilter? = null
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.colorFilter = colorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top + 10.dp, paint)
        paint.colorFilter = null
    }

    fun reset() {
        cm.reset()
        colorMatrixColorFilter = ColorMatrixColorFilter(cm)
        invalidate()
    }

    fun set() {
        cm.set(colorMatrix)
        colorMatrixColorFilter = ColorMatrixColorFilter(cm)
        invalidate()
    }

}