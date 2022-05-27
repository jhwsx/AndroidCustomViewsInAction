package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Paint.setAntiAlias 方法演示
 * 也可以通过 Paint 的构造函数 Paint(int flags) 传入 Paint.ANTI_ALIAS_FLAG 来打开设置
 * @author wangzhichao
 * @since 20-3-6
 */
class B_BasicAntiAliasView  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var paint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 设置画笔的颜色
        paint.color = Color.RED
        // 设置画笔的填充样式
        // 设置 STROKE，为红色的圆环
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 50f

        // 画圆
        paint.isAntiAlias = false // 关闭抗锯齿，边缘有些毛糙
        canvas.drawCircle(190f, 200f, 150f, paint)
        paint.isAntiAlias = true // 打开抗锯齿，边缘更加光滑
        canvas.drawCircle(600f, 200f, 150f, paint)
    }
}