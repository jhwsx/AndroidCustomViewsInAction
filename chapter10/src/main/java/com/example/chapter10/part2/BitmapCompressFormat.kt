package com.example.chapter10.part2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.Bitmap.CompressFormat
import android.os.Environment
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.chapter10.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * @author wangzhichao
 * @date 2019/10/16
 */
class BitmapCompressFormat(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.textSize = 40f
        paint.color = Color.GREEN
        paint.strokeWidth = 5f
        paint.style = Paint.Style.FILL
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
        save("scenary_png.png", bitmap, CompressFormat.PNG)
        save("scenary_jpeg.jpeg", bitmap, CompressFormat.JPEG)
        save("scenary_webp.webp", bitmap, CompressFormat.WEBP)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val staticLayout = StaticLayout(
            """Bitmap 的三种压缩格式：
  Bitmap.CompressFormat.PNG
 Bitmap.CompressFormat.JPEG
 Bitmap.CompressFormat.WEBP
可以去 SD 卡根目录查看转化成的三种照片。""", paint,
            width, Layout.Alignment.ALIGN_CENTER, 1f, 0f, true)
        staticLayout.draw(canvas)
    }

    private fun save(fileName: String, bitmap: Bitmap, compressFormat: CompressFormat) {
        Thread {
            try {
                val file = File(Environment.getExternalStorageDirectory()
                    .toString() + File.separator + fileName)
                val fos = FileOutputStream(file)
                bitmap.compress(compressFormat, 100, fos)
                fos.flush()
                fos.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }
}