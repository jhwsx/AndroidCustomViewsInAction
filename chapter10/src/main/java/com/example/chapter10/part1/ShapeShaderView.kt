package com.example.chapter10.part1

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import com.example.chapter10.R
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/10/12
 */
class ShapeShaderView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable: ShapeDrawable = ShapeDrawable(RectShape())

    init {
        drawable.setBounds(50.dp.toInt(), 50.dp.toInt(), 300.dp.toInt(), 300.dp.toInt())
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.avator)
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        drawable.paint.shader = bitmapShader
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.draw(canvas)
    }


}