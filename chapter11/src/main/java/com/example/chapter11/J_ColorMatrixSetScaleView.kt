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
import kotlin.math.cos
import kotlin.math.sin

/**
 * 色彩缩放
 * @author wangzhichao
 * @since 2022/6/2
 */
class J_ColorMatrixSetScaleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val fontMetrics = Paint.FontMetrics()
    private val avatarSize = 150.dp
    private val avatar =
        ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, avatarSize.toInt())
    private var colorMatrixColorFilter: ColorMatrixColorFilter? = null
    private val colorMatrix = ColorMatrix()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textPaint.getFontMetrics(fontMetrics)
        canvas.drawText("原图：", 0f, -fontMetrics.top, textPaint)
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top + 10.dp, paint)
        paint.colorFilter = null

        canvas.drawText("设置红色色彩缩放的图片：", 0f, -fontMetrics.top * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = colorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null
    }

    var scale: Float = 1f
        set(value) {
            colorMatrix.setScale(value, 1f, 1f, 1f)
            colorMatrixColorFilter = ColorMatrixColorFilter(colorMatrix)
            invalidate()
        }

}