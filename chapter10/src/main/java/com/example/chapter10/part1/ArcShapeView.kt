package com.example.chapter10.part1

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.ArcShape
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/10/11
 */
class ArcShapeView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable: ShapeDrawable = ShapeDrawable(ArcShape(0f, 90f))

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