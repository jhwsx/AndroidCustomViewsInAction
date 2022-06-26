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
class J_MagnifierView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val sceneryBmp: Bitmap
    private lateinit var bitmap: Bitmap
    private lateinit var drawable: ShapeDrawable
    private val mtx = Matrix()

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        sceneryBmp = BitmapFactory.decodeResource(resources, R.drawable.scenery)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // 让风景图片充满整个控件的大小
        bitmap = Bitmap.createScaledBitmap(sceneryBmp, width, height, false)
        // 以把风景图片放大到 3 倍控件大小的图片，来填充图片着色器
        val bitmapShader = BitmapShader(
            Bitmap.createScaledBitmap(
                sceneryBmp, width * FACTOR,
                height * FACTOR, true
            ), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP
        )
        drawable = ShapeDrawable(OvalShape())
        drawable.paint.shader = bitmapShader
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        drawable.draw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                updateDrawable(event)
                true
            }
            MotionEvent.ACTION_MOVE -> {
                updateDrawable(event)
                super.onTouchEvent(event)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                updateDrawable(event, true)
                super.onTouchEvent(event)
            }
            else -> super.onTouchEvent(event)
        }
    }

    private fun updateDrawable(event: MotionEvent, released: Boolean = false) {
        val x = event.x.toInt()
        val y = event.y.toInt()
        // 这两行代码的作用很关键：移动 Shader 到要显示的地方。
        // 将当前手指位置映射到放大到 3 倍控件大小的图片上的位置。
        // 将整个放大到 3 倍控件大小的图片向左上移动 3x, 3y 的距离，是 -3x,-3y
        // 将左上角显示的图像显示在 ShapeDrawable 区域中心，所以将图片再向右下移动一个半径的距离。
        // 所以总共移动：radius-3x, radius-3y
        mtx.setTranslate((RADIUS - x * FACTOR).toFloat(), (RADIUS - y * FACTOR).toFloat())
        drawable.paint.shader.setLocalMatrix(mtx)
        // 更新 Drawable 的 bounds
        if (released) {
            drawable.setBounds(0,0,0,0)
        } else {
            drawable.setBounds(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS)
        }
        invalidate()
    }

    companion object {
        // 放大倍数
        private const val FACTOR = 3
        private val RADIUS = 40.dp.toInt()
    }
}