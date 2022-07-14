package com.example.chapter10.part2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * @author wangzhichao
 * @date 2019/10/20
 */
class F_BitmapFactoryDecodeStreamView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mBitmap: Bitmap? = null
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mBitmap == null) {
            return
        }
        canvas.drawBitmap(mBitmap!!, 0f, 0f, paint)
    }

    @Throws(Exception::class)
    private fun getImage(link: String): InputStream? {
        var result: InputStream? = null
        val url = URL(link)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.readTimeout = 6 * 1000
        if (httpURLConnection.responseCode == 200) {
            result = httpURLConnection.inputStream
        }
        return result
    }

    init {
        Thread(Runnable {
            try {
                val data = getImage("https://profile.csdnimg.cn/7/C/B/1_willway_wang") ?: return@Runnable
                val bitmap = BitmapFactory.decodeStream(data)
                data.close()
                mBitmap = bitmap
                postInvalidate()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
    }
}