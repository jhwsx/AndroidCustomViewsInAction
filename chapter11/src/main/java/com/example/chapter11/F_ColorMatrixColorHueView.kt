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
 * 色相调节
 * @author wangzhichao
 * @since 2022/5/31
 */
class F_ColorMatrixColorHueView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val fontMetrics = Paint.FontMetrics()
    private val avatarSize = 100.dp
    private val avatar =
        ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, avatarSize.toInt())

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textPaint.getFontMetrics(fontMetrics)
        canvas.drawText("原图：", 0f, -fontMetrics.top, textPaint)
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top + 10.dp, paint)
        paint.colorFilter = null

        canvas.drawText("红色色相的图片：", 0f, -fontMetrics.top * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = redHueColorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null

        canvas.save()
        canvas.translate(avatarSize + 20.dp, 0f)
        canvas.drawText("绿色色相的图片：", 0f, -fontMetrics.top * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = greenHueColorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null
        canvas.restore()

        canvas.save()
        canvas.translate(2 * avatarSize + 40.dp, 0f)
        canvas.drawText("蓝色色相的图片：", 0f, -fontMetrics.top * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = blueHueColorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null
        canvas.restore()

    }

    private var redHueColorMatrixColorFilter: ColorMatrixColorFilter? = null

    var redHueDegrees: Double
        get() = 0.0
        set(value) {
            val colorMatrix = ColorMatrix()
            colorMatrix.setRotate(0, value.toFloat())
            redHueColorMatrixColorFilter = ColorMatrixColorFilter(colorMatrix)
            invalidate()
        }

    private var greenHueColorMatrixColorFilter: ColorMatrixColorFilter? = null

    var greenHueDegrees: Double
        get() = 0.0
        set(value) {
            val colorMatrix = ColorMatrix()
            colorMatrix.setRotate(1, value.toFloat())
            greenHueColorMatrixColorFilter = ColorMatrixColorFilter(colorMatrix)
            invalidate()
        }

    private var blueHueColorMatrixColorFilter: ColorMatrixColorFilter? = null

    var blueHueDegrees: Double
        get() = 0.0
        set(value) {
            val colorMatrix = ColorMatrix()
            colorMatrix.setRotate(2, value.toFloat())
            blueHueColorMatrixColorFilter = ColorMatrixColorFilter(colorMatrix)
            invalidate()
        }
}