package com.example.chapter10.part2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

/**
 * 演示指定色彩创建对象的 Bitmap.createBitmap 方法
 *
 * @author wangzhichao
 * @date 2019/10/29
 */
class BitmapCreateBitmapColorView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap: Bitmap
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

    private fun initColors(width: Int, height: Int): IntArray {
        val colors = IntArray(width * height)
        for (y in 0 until height) { // 列
            for (x in 0 until width) { // 行
                val r = x * 255 / (width - 1)
                val g = y * 255 / (width - 1)
                val b = 255 - Math.min(r, g)
                val a = Math.max(r, g)
                colors[y * width + x] = Color.argb(a, r, g, b)
            }
        }
        return colors
    }

    init {
        val width = 300
        val height = 200
        val colors = initColors(width, height)
        bitmap = Bitmap.createBitmap(colors, width, height, Bitmap.Config.ARGB_8888)
    }
}