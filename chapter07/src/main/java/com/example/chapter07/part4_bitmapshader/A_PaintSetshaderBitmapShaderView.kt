package com.example.chapter07.part4_bitmapshader

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter07.R

/**
 * 演示 BitmapShader 各个参数的含义
 *
 * @author wangzhichao
 * @date 2019/09/21
 */
class A_PaintSetshaderBitmapShaderView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog_edge)
    private var tileX: Shader.TileMode? = null
    private var tileY: Shader.TileMode? = null
    private var smallRect = false

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (tileX != null) {
            // bitmap: 指定用什么图来填充
            // tileX: 当需要填充的图形区域 x 向大于 bitmap 的宽度时，采用什么重复策略来填满 x 向的区域。
            // 当设置了 Shader 之后，Paint 在绘制图形和文字时就不使用 setColor/ARGB() 设置的颜色了，
            // 而是使用 Shader 的方案中的颜色。
            paint.shader = BitmapShader(bitmap, tileX!!, tileY!!)
        }
        if (smallRect) {
            canvas.drawRect((width / 3).toFloat(),
                (height / 3).toFloat(),
                (width * 2 / 3).toFloat(),
                (height * 2 / 3).toFloat(),
                paint)
        } else {
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        }
    }

    fun setTileX(tileX: Shader.TileMode?) {
        this.tileX = tileX
        invalidate()
    }

    fun setTileY(tileY: Shader.TileMode?) {
        this.tileY = tileY
        invalidate()
    }

    fun setSmallRect(smallRect: Boolean) {
        this.smallRect = smallRect
        invalidate()
    }
}