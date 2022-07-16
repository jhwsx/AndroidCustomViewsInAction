package com.example.chapter10.part2.section4

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.example.common.dp

/**
 * 自定义线性渐变带描边的 View
 * 步骤：
 * 1，先创建一个空白的bitmap；
 * 2，在空白的 bitmap 上绘制线性渐变的颜色；
 * 3，把绘制好渐变色的 bitmap 绘制到画布上；
 * 4，绘制描边
 *
 * @author wangzhichao
 * @date 2019/10/29
 */
class A_BitmapCreateBitmapLinearGradientView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    // 1，先创建一个空白的bitmap；
    private val bitmapWidth = 200.dp
    private val bitmapHeight = 300.dp
    private val bitmap =
        Bitmap.createBitmap(bitmapWidth.toInt(), bitmapHeight.toInt(), Bitmap.Config.ARGB_8888)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 3，把绘制好渐变色的 bitmap 绘制到画布上
        val left = width / 2f - bitmap.width / 2f
        val top = height / 2f - bitmap.height / 2f
        canvas.drawBitmap(bitmap, left, top, paint)
        // 4，绘制描边
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3.dp
        canvas.drawRect(left, top, left + bitmap.width, top + bitmap.height, paint)
    }

    init {
        // 2，在空白的 bitmap 上绘制线性渐变的颜色
        val c = Canvas(bitmap)
        val p = Paint(Paint.ANTI_ALIAS_FLAG)
        val linearGradient = LinearGradient(
            bitmapWidth / 2, 0f, bitmapWidth / 2, bitmapHeight,
            intArrayOf(0xffffffff.toInt(), 0x00ffffff),
            floatArrayOf(0f, 1f), Shader.TileMode.CLAMP
        )
        p.shader = linearGradient
        c.drawRect(0f, 0f, bitmapWidth, bitmapHeight, p)
    }
}