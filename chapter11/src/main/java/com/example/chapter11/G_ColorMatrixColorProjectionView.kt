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
 * 色彩投射
 * 利用其它色彩分量的倍数来更改自己色彩分量的值，这种运算就叫投射运算。
 * @author wangzhichao
 * @since 2022/5/31
 */
class G_ColorMatrixColorProjectionView @JvmOverloads constructor(
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
    private val monoChromeColorMatrixColorFilter = ColorMatrixColorFilter(ColorMatrix(floatArrayOf(
        0.213f, 0.715f, 0.072f, 0f, 0f,
        0.213f, 0.715f, 0.072f, 0f, 0f,
        0.213f, 0.715f, 0.072f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f,
    )))
    // 当我们利用色彩矩阵将两个颜色反转，这种操作就叫做色彩反色
    private val invertColorMatrixColorFilter = ColorMatrixColorFilter(ColorMatrix(floatArrayOf(
        0f, 1f, 0f, 0f, 0f,
        1f, 0f, 0f, 0f, 0f,
        0f, 0f, 1f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f,
    )))
    private val oldColorMatrixColorFilter = ColorMatrixColorFilter(ColorMatrix(floatArrayOf(
        1/2f,  1/2f,  1/2f, 0f, 0f,
        1/3f,  1/3f,  1/3f, 0f, 0f,
        1/4f,  1/4f,  1/4f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f,
    )))
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textPaint.getFontMetrics(fontMetrics)
        canvas.drawText("原图：", 0f, -fontMetrics.top, textPaint)
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top + 10.dp, paint)
        paint.colorFilter = null

        canvas.drawText("黑白图片：", 0f, -fontMetrics.top * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = monoChromeColorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null

        canvas.save()
        canvas.translate(avatarSize + 20.dp, 0f)
        canvas.drawText("色彩反色：", 0f, -fontMetrics.top * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = invertColorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null
        canvas.restore()

        canvas.save()
        canvas.translate(2 * avatarSize + 40.dp, 0f)
        canvas.drawText("变旧图片：", 0f, -fontMetrics.top * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = oldColorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null
        canvas.restore()

    }

}