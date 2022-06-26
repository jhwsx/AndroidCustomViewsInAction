package com.example.chapter10.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.View
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/10/11
 */
class OvalShapeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable: ShapeDrawable = ShapeDrawable(OvalShape())
    init {
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