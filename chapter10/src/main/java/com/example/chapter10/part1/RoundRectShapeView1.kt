package com.example.chapter10.part1

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.example.common.dp

/**
 * RoundRectShape 实现单纯的带有圆角的矩形
 *
 * @author wangzhichao
 * @date 2019/10/11
 */
class RoundRectShapeView1(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable: ShapeDrawable

    init {
        val outRadii = floatArrayOf(
            12.dp, 12.dp,
            24.dp, 24.dp, 0f, 0f, 0f, 0f)
        val roundRectShape = RoundRectShape(outRadii, null, null)
        drawable = ShapeDrawable(roundRectShape)
        drawable.bounds = Rect(50.dp.toInt(),
            50.dp.toInt(),
            200.dp.toInt(),
            100.dp.toInt())
        drawable.paint.color = Color.YELLOW
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.draw(canvas)
    }
}