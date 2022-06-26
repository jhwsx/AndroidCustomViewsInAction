package com.example.chapter10.part2

import android.content.Context
import com.example.chapter10.R
import android.graphics.*
import android.util.AttributeSet
import android.view.*

/**
 * 演示从图像中取出 Alpha 通道，并把它染成天蓝色.
 * 思路：
 * 1，加载源图像；
 * 2，调用 bitmap.extractAlpha()，得到只有透明通道的图像；
 * 3, 以创建的空白图像作为画布，使用天蓝色画笔把 2 得到的图像绘制。
 * 4，把最终的 bitmap 绘制到 canvas 上。
 *
 * @author wangzhichao
 * @date 2019/10/31
 */
class BitmapExtractAlphaView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
    private val src: Bitmap
    private val bitmap: Bitmap
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(src, null, Rect(0, 0, width, height / 2), null)
        canvas.drawBitmap(bitmap, null, Rect(0, height / 2, width, height), null)
    }

    init {
        src = BitmapFactory.decodeResource(resources, R.drawable.cat_dog)
        bitmap = Bitmap.createBitmap(src.width, src.height, Bitmap.Config.ARGB_8888)
        val c = Canvas(bitmap)
        paint.color = Color.CYAN
        c.drawBitmap(src.extractAlpha(), 0f, 0f, paint)
    }
}