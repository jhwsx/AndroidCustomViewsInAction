package com.example.chapter10.part1

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.example.common.dp

/**
 * RoundRectShape 实现中间带有镂空矩形的圆角矩形并且中间的镂空矩形也带有圆角
 *
 * @author wangzhichao
 * @date 2019/10/11
 */
class RoundRectShapeView2(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable: ShapeDrawable
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.draw(canvas)
    }

    init {
        // 外围矩形的各个角的圆角
        val outRadii = floatArrayOf(
            12.dp, 12.dp,
            24.dp, 24.dp, 0f, 0f, 0f, 0f)
        // 内部矩形与外部矩形各边的间距
        val inset = RectF(10.dp, 10.dp, 10.dp, 10.dp)
        // 内部矩形的各个角的圆角
        val innerRadii = floatArrayOf(0f, 0f, 0f, 0f,
            12.dp, 12.dp,
            24.dp, 24.dp,)
        val roundRectShape = RoundRectShape(outRadii, inset, innerRadii)
        drawable = ShapeDrawable(roundRectShape)
        drawable.bounds = Rect(50.dp.toInt(),
            50.dp.toInt(),
            200.dp.toInt(),
            100.dp.toInt())
        drawable.paint.color = Color.YELLOW
    }
}