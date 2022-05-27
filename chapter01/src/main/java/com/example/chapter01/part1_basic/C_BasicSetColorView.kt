package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Paint setColor 方法的使用演示
 *
 * @author wangzhichao
 * @since 20-3-6
 */
class C_BasicSetColorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = Color.parseColor("#ffff0000")
        canvas.drawCircle(300f, 300f, 200f, paint)
        paint.color = 0x7effff00
        canvas.drawCircle(300f, 300f, 120f, paint)
    }
}