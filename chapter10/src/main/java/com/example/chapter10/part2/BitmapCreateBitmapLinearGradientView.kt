package com.example.chapter10.part2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.example.common.dp

/**
 * 自定义线性渐变带描边的 View
 * 步骤：
 * 1.先创建一个空白的bitmap；
 * 2，在空白的 bitmap 上绘制线性渐变的颜色；
 * 3，把绘制好渐变色的 bitmap 绘制到画布上；
 * 4，绘制描边
 *
 * @author wangzhichao
 * @date 2019/10/29
 */
class BitmapCreateBitmapLinearGradientView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private val bitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val width = width
        val height = height
        val left = width / 2 - bitmap.width / 2
        val top = height / 2 - bitmap.height / 2
        canvas.drawBitmap(bitmap, left.toFloat(), top.toFloat(), paint)
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3.dp
        canvas.drawRect(left.toFloat(),
            top.toFloat(),
            (left + bitmap.width).toFloat(),
            (top + bitmap.height).toFloat(),
            paint)
    }

    init {
        bitmap = Bitmap.createBitmap(200.dp.toInt(), 300.dp.toInt(), Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val linearGradient = LinearGradient(0f,
            0f,
            0f,
            300.dp,
            intArrayOf(-0x1, 0x00ffffff),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP)
        val p = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
        p.shader = linearGradient
        val rect = Rect(0, 0, 200.dp.toInt(), 300.dp.toInt())
        canvas.drawRect(rect, p)
    }
}