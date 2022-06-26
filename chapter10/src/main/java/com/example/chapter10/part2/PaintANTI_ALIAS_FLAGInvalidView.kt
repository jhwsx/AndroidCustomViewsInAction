package com.example.chapter10.part2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @author wangzhichao
 * @since 2019/12/10
 */
class PaintANTI_ALIAS_FLAGInvalidView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private val paint1 = Paint()
    private val paint2 = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap: Bitmap
    private val bitmap2: Bitmap
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("paint_antialiasflag", "onDraw")
        canvas.drawCircle(250f, 300f, 200f, paint1)
        canvas.drawCircle(700f, 300f, 200f, paint2)
        val c = Canvas(bitmap)
        c.drawCircle(200f, 200f, 200f, paint1)
        canvas.drawBitmap(bitmap, 50f, 700f, paint1)
        val c2 = Canvas(bitmap2)
        c2.drawCircle(200f, 200f, 200f, paint2)
        canvas.drawBitmap(bitmap2, 500f, 700f, paint2)
    }

    init {
        paint1.color = Color.RED
        paint1.style = Paint.Style.FILL
        paint2.color = Color.RED
        paint2.style = Paint.Style.FILL
        bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
        bitmap2 = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
    }
}