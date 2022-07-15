package com.example.chapter10.part2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.example.common.dp
import kotlin.math.max
import kotlin.math.min

/**
 * 演示指定色彩创建对象的 Bitmap.createBitmap 方法
 *
 * @author wangzhichao
 * @date 2019/10/29
 */
class P_BitmapCreateBitmapColorView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap: Bitmap
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

    private fun initColors(width: Int, height: Int): IntArray {
        val colors = IntArray(width * height)
        for (row in 0 until height) { // 行
            for (column in 0 until width) { // 列
                val r = column * 255 / (width - 1)
                val g = row * 255 / (height - 1)
                val b = 255 - min(r, g)
                val a = max(r, g)
                colors[row * width + column] = Color.argb(a, r, g, b)
            }
        }
        return colors
    }

    init {
        val width = 300.dp.toInt()
        val height = 400.dp.toInt()
        val colors = initColors(width, height)
        // colors: Array of sRGB colors used to initialize the pixels. This array must be at least as large as width * height.
        bitmap = Bitmap.createBitmap(colors, width, height, Bitmap.Config.ARGB_8888)
    }
}