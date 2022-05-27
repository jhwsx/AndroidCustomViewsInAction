package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * @author wangzhichao
 * @since 20-3-6
 */
class A_BasicView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 设置画笔的颜色
        paint.color = Color.RED
        // 设置画笔的填充样式
        // 设置 STROKE，为红色的圆环
        paint.style = Paint.Style.STROKE
        // 设置画笔的宽度
        // Pass 0 to stroke in hairline mode. 输入负数，还是头发丝模式。
        // 发现传入 Float.MAX_VALUE，运行后屏幕变黑了。
        paint.strokeWidth = 50f

        // 画圆
        canvas.drawCircle(190f, 200f, 150f, paint)
    }
}