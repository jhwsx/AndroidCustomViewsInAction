package com.example.chapter06.part2_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.chapter06.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * 图文混排2
 * @author wangzhichao
 * @since 2022/5/25
 */
class H_BreakTextView2 @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 18.dp
    }
    private val text =
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer fringilla finibus neque, feugiat varius nisi consectetur ut. Quisque metus sapien, tincidunt sed eros vel, tincidunt lacinia nisl. In erat elit, dictum vel felis non, luctus scelerisque risus. Suspendisse potenti. Aliquam ultricies lorem id risus tristique lobortis. Etiam ut tempus augue. Mauris cursus libero at leo pharetra cursus. Etiam tortor libero, tempor ac imperdiet ut, hendrerit at augue."

    private val avatarTop = 10.dp
    private val avatarSide = 100.dp
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(context.resources,
        R.drawable.avatar, avatarSide.toInt())

    private val fontMetrics = Paint.FontMetrics()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val avatarLeft = 0f
        canvas.drawBitmap(avatar, avatarLeft, avatarTop, paint)
        val measuredWidth = floatArrayOf(0f)
        var measuredCount: Int
        var start = 0
        textPaint.getFontMetrics(fontMetrics)
        var baseline = -fontMetrics.top
        var maxWidth: Float
        var x: Float
        while (start < text.length) {
            if (baseline + fontMetrics.bottom > avatarTop // 文字的底部大于图片顶部
                && baseline + fontMetrics.top < avatarTop + avatarSide
            ) { // 文字的顶部小于图片底部
                x = avatarSide
                maxWidth = width - avatarSide
            } else {
                x = 0f
                maxWidth = width.toFloat()
            }
            // 截取文本，返回测量的字符个数
            measuredCount = textPaint.breakText(text, start, text.length,
                true, maxWidth, measuredWidth)
            canvas.drawText(text, start, start + measuredCount,
                x, baseline, textPaint)
            start += measuredCount
            baseline += textPaint.fontSpacing
        }
    }

    companion object {
        private const val TAG = "H_BreakTextView"
    }
}