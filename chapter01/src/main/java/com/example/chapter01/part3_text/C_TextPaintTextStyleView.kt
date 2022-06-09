package com.example.chapter01.part3_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Paint 的设置文字样式的方法演示
 *
 * @author wangzhichao
 * @since 20-3-11
 */
class C_TextPaintTextStyleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    init {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paint.textSize = 60f
        paint.strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val text = "窗前明月光-High quality(region)"
        canvas.drawText(text, 16f, 100f, paint)

        // 设置粗体
        paint.isFakeBoldText = true
        canvas.drawText(text, 16f, 200f, paint)
        paint.isFakeBoldText = false

        // 设置中间删除线
        paint.isStrikeThruText = true
        canvas.drawText(text, 16f, 300f, paint)
        paint.isStrikeThruText = false

        // 设置下划线
        paint.isUnderlineText = true
        canvas.drawText(text, 16f, 400f, paint)
        paint.isUnderlineText = false

        // 设置文字倾斜度
        // 注意：倾斜的时候是底部不动，顶部向右倾斜，或向左倾斜。
        paint.textSkewX = 0.25f // 负值表示向右倾斜
        canvas.drawText(text, 16f, 500f, paint)
        paint.textSkewX = -0.25f // 正值表示向右倾斜
        canvas.drawText(text, 16f, 600f, paint)
        paint.textSkewX = 0f

        // 设置文字水平拉伸
        paint.textScaleX = 2f // 拉伸为原来宽度的 2 倍
        canvas.drawText(text, 16f, 700f, paint)
        paint.textScaleX = 0.5f
        canvas.drawText(text, 16f, 800f, paint)
        paint.textScaleX = 1.0f

        // 设置字符间距
        paint.letterSpacing = 0.5f
        canvas.drawText(text, 16f, 900f, paint)
        paint.letterSpacing = 0f
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }
}