package com.example.chapter10.part2.section2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * BitmapFactory.decodeByteArray
 *
 * @author wangzhichao
 * @date 2019/10/20
 */
class B_BitmapFactoryDecodeByteArrayView(context: Context, attrs: AttributeSet?) :
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
    private fun getImage(link: String): ByteArray? {
        var result: ByteArray? = null
        val url = URL(link)
        val httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.readTimeout = 6 * 1000
        if (httpURLConnection.responseCode == HttpURLConnection.HTTP_OK) {
            val inputStream = httpURLConnection.inputStream
            result = readStream(inputStream)
//            result = readStream2(inputStream)
            inputStream.close()
        }
        return result
    }

    // 这是正常的代码：把输入流转换为字节内存输出流的字节数组形式，这才是 BitmapFactory.decodeByteArray 需要的。
    @Throws(Exception::class)
    private fun readStream(inputStream: InputStream): ByteArray {
        val baos = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var length: Int
        while (inputStream.read(buffer).also { length = it } != -1) {
            baos.write(buffer, 0, length)
        }
        inputStream.close()
        baos.close()
        return baos.toByteArray()
    }

    // 这是有问题的代码
    @Throws(Exception::class)
    private fun readStream2(inputStream: InputStream): ByteArray {
        val result = ByteArray(inputStream.available())
        inputStream.read(result)
        inputStream.close()
        return result
    }

    init {
        Thread(Runnable {
            try {
                val data =
                    getImage("https://profile.csdnimg.cn/7/C/B/1_willway_wang") ?: return@Runnable
                val bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
                mBitmap = bitmap
                postInvalidate()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
    }
}