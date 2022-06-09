package com.example.chapter09.part1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * 使用 Bitmap 创建 Canvas
 *
 * @author wzc
 * @date 2019/10/1
 */
class B_BitmapCanvasView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap: Bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val c: Canvas

    init {
        c = Canvas(bitmap)
        paint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.textSize = 50f
        c.drawText("欢迎光临", 0f, 100f, paint)
        // 不写下面这行，是不会画到 View 上的。
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
    }


}