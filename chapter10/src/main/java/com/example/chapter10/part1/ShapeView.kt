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
class ShapeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable: ShapeDrawable = ShapeDrawable(RectShape())

    init {
        // 这里所设置的矩形位置是指在所在控件中的位置，而不是以屏幕上左上角点为坐标的。
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