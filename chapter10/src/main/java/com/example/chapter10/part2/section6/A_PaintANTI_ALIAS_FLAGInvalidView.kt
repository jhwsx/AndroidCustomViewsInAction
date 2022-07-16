package com.example.chapter10.part2.section6

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @author wangzhichao
 * @since 2019/12/10
 */
class A_PaintANTI_ALIAS_FLAGInvalidView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private val paint = Paint()
    private val paintAntiAlias = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d("paint_antialiasflag", "onDraw")
        // 1，直接在 Canvas 上绘制
        // 会先清空 Canvas，然后重绘所有内容
        canvas.drawCircle(250f, 300f, 200f, paint)
        canvas.drawCircle(700f, 300f, 200f, paintAntiAlias)
        // 2，先在 Bitmap 上绘制，再将 Bitmap 绘制到 Canvas 上
        // 2.2 在绘制前清除 Bitmap
//        c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
//        c2.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        c.drawCircle(200f, 200f, 200f, paint)
        c2.drawCircle(200f, 200f, 200f, paintAntiAlias)
        canvas.drawBitmap(bitmap, 50f, 700f, paint)
        canvas.drawBitmap(bitmap2, 500f, 700f, paintAntiAlias)
    }
    private val bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
    private val bitmap2 = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888)
    private val c = Canvas(bitmap)
    private val c2 = Canvas(bitmap2)
    init {
        // 2.1 避免重绘
//        c.drawCircle(200f, 200f, 200f, paint)
//        c2.drawCircle(200f, 200f, 200f, paintAntiAlias)
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
        paintAntiAlias.color = Color.RED
        paintAntiAlias.style = Paint.Style.FILL
    }
}