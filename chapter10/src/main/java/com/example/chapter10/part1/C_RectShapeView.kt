package com.example.chapter10.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.util.AttributeSet
import android.view.View
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/10/10
 */
class C_RectShapeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    // 在构造的时候传入 Shape 对象，有 RectShape，ArcShape，OvalShape，RoundRectShape，PathShape。
    private val drawable: ShapeDrawable = ShapeDrawable(RectShape())

    init {
        // 这里所设置的矩形位置是指在所在控件中的位置，而不是以屏幕上左上角点为坐标的。
        // 一定要设置 Drawable 的 bounds，否则什么也看不到的。
        // setBounds -> onBoundsChange -> updateShape -> Shape.resize -> 把尺寸传给形状了。
        drawable.bounds = Rect(50.dp.toInt(),
            50.dp.toInt(),
            200.dp.toInt(),
            100.dp.toInt())
        // 给 ShapeDrawable 自带的画笔设置颜色，画笔是在 ShapeState 中的。
        drawable.paint.color = Color.YELLOW
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 在 ShapeDrawable 的 draw 方法内部，会调用它的 onDraw 方法，
        // 内部会调用 Shape 的 draw 方法真正绘制形状。
        drawable.draw(canvas)
    }
}