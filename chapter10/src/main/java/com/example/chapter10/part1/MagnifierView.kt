package com.example.chapter10.part1

import android.content.Context
import android.graphics.*
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.chapter10.R
import com.example.common.dp

/**
 * 放大镜效果
 * 思路：
 * 1，把图放大成和背景一样大，并绘制出来；
 * 2，跟手指移动的圆心；
 * 3，利用 ShapeDrawable setShader 画以上面的圆心以及一定半径的印章；
 * 4，把 ShapeDrawable 绘制到 canvas 上面。
 *
 * @author wangzhichao
 * @date 2019/10/13
 */
class MagnifierView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val sceneryBmp: Bitmap
    private var bitmap: Bitmap? = null
    private val currX = -1
    private val currY = -1
    private var drawable: ShapeDrawable? = null
    private val mtx = Matrix()

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        sceneryBmp = BitmapFactory.decodeResource(resources, R.drawable.scenery)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (bitmap == null) {
            bitmap = Bitmap.createScaledBitmap(sceneryBmp, width, height, false)
            val bitmapShader = BitmapShader(Bitmap.createScaledBitmap(sceneryBmp, width * FACTOR,
                height * FACTOR, true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            drawable = ShapeDrawable(OvalShape())
            drawable!!.paint.shader = bitmapShader
            drawable!!.setBounds(0, 0, RADIUS * 2, RADIUS * 2)
        }
        canvas.drawBitmap(bitmap!!, 0f, 0f, null)
        drawable!!.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x.toInt()
        val y = event.y.toInt()
        // 这两行代码的作用很关键：移动 Shader 到要显示的地方。
        mtx.setTranslate((RADIUS - x * FACTOR).toFloat(), (RADIUS - y * FACTOR).toFloat())
        drawable!!.paint.shader.setLocalMatrix(mtx)
        drawable!!.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS)
        invalidate()
        return true
    }

    companion object {
        // 放大倍数
        private const val FACTOR = 3
        private val RADIUS = 40.dp.toInt()
    }
}