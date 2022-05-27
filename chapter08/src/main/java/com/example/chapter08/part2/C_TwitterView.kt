package com.example.chapter08.part2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter08.R

/**
 * @author wangzhichao
 * @date 2019/09/23
 */
class C_TwitterView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val dstBmp: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.twiter_bg)
    private val srcBmp: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.twiter_light)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.MULTIPLY)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerId =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(srcBmp, 0f, 0f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }
}