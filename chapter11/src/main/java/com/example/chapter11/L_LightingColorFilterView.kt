package com.example.chapter11

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * 光照颜色过滤器
 *
 * 可以简单的完成色彩过滤和色彩增强功能
 *
 * @author wangzhichao
 * @since 2022/6/9
 */
class L_LightingColorFilterView @JvmOverloads constructor(
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

    private val colorFilter = LightingColorFilter(0xffff00, 0x000000)
    private val colorFilter2 = LightingColorFilter(0xffffff, 0x003000)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        textPaint.getFontMetrics(fontMetrics)
        canvas.drawText("原图：", 0f, -fontMetrics.top, textPaint)
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top + 10.dp, paint)
        paint.colorFilter = null

        canvas.drawText("黄色过滤的图片：", 0f, -fontMetrics.top * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = colorFilter
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null

        canvas.save()
        canvas.translate(avatarSize + 20.dp, 0f)
        canvas.drawText("绿色增强的图片：", 0f, -fontMetrics.top * 2 + avatarSize + 20.dp, textPaint)
        paint.colorFilter = colorFilter2
        canvas.drawBitmap(avatar, 0f, -fontMetrics.top * 2 + avatarSize + 30.dp, paint)
        paint.colorFilter = null
        canvas.restore()
    }
}