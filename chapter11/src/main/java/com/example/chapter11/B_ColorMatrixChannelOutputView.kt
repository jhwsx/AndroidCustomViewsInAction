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
 * 图片通道输出效果
 * @author wangzhichao
 * @since 2022/5/31
 */
class B_ColorMatrixChannelOutputView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 14.dp
    }
    private val fontMetrics = Paint.FontMetrics()
    private val avatarSize = 100.dp
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, avatarSize.toInt())
    private val redColorMatrix = ColorMatrix(floatArrayOf(
        1f, 0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f,
    ))
    private val redColorMatrixColorFilter = ColorMatrixColorFilter(redColorMatrix)
    private val greenColorMatrix = ColorMatrix(floatArrayOf(
        0f, 0f, 0f, 0f, 0f,
        0f, 1f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f,
    ))
    private val greenColorMatrixColorFilter = ColorMatrixColorFilter(greenColorMatrix)
    private val blueColorMatrix = ColorMatrix(floatArrayOf(
        0f, 0f, 0f, 0f, 0f,
        0f, 0f, 0f, 0f, 0f,
        0f, 0f, 1f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f,
    ))
    private val blueColorMatrixColorFilter = ColorMatrixColorFilter(blueColorMatrix)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textPaint.getFontMetrics(fontMetrics)
        canvas.drawText("原图：", 0f, -fontMetrics.top, textPaint)
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top + 10.dp, paint)
        paint.colorFilter = null

        canvas.drawText("红色通道的图片：", 0f, -fontMetrics.top  * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = redColorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top  * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null

        canvas.save()
        canvas.translate(avatarSize + 20.dp, 0f)
        canvas.drawText("绿色通道的图片：", 0f, -fontMetrics.top  * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = greenColorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top  * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null
        canvas.restore()

        canvas.save()
        canvas.translate(2 * avatarSize + 40.dp, 0f)
        canvas.drawText("蓝色通道的图片：", 0f, -fontMetrics.top  * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = blueColorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top  * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null
        canvas.restore()
    }
}