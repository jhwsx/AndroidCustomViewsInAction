package com.example.chapter10.part2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Environment
import android.util.AttributeSet
import android.view.View
import java.io.File
import java.io.FileInputStream

/**
 * BitmapFactory.decodeFileDescriptor
 *
 * @author wangzhichao
 * @date 2019/10/20
 */
class E_BitmapFactoryDecodeFileDescriptorView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private var bitmap: Bitmap? = null
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (bitmap != null) {
            canvas.drawBitmap(bitmap!!, 0f, 0f, paint)
        }
    }

    init {
        val path = File(context.getExternalFilesDir(null), "scenary_png.png")
        try {
            val fileInputStream = FileInputStream(path)
            bitmap = BitmapFactory.decodeFileDescriptor(fileInputStream.fd)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}