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
 * 色彩的缩放运算其实就是色彩的乘法运算。
 * 当对 R、G、B、A 同时进行放大/缩小运算时，就是对亮度进行调节。
 * @author wangzhichao
 * @since 2022/5/31
 */
class E_ColorMatrixIncreaseBrightnessView @JvmOverloads constructor(
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
        1.2f, 0f, 0f, 0f, 0f,
        0f, 1.2f, 0f, 0f, 0f,
        0f, 0f, 1.2f, 0f, 0f,
        0f, 0f, 0f, 1.2f, 0f,
    ))
    private val colorMatrixColorFilter = ColorMatrixColorFilter(colorMatrix)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textPaint.getFontMetrics(fontMetrics)
        canvas.drawText("原图：", 0f, -fontMetrics.top, textPaint)
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top + 10.dp, paint)
        paint.colorFilter = null

        canvas.drawText("增加亮度的图片：", 0f, -fontMetrics.top  * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = colorMatrixColorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top  * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null

    }
}