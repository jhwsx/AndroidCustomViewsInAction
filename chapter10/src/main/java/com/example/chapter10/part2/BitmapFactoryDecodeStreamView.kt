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
class BitmapFactoryDecodeStreamView(context: Context, attrs: AttributeSet?) :
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
            //            result = readStream(inputStream);
//            result = readStream2(inputStream);
        }
        return result
    } //    private byte[] readStream(InputStream inputStream) throws Exception{

    //        ByteArrayOutputStream baos = new ByteArrayOutputStream();
    //        byte[] buffer = new byte[1024];
    //        int length;
    //        while((length = inputStream.read(buffer)) != -1) {
    //            baos.write(buffer, 0, length);
    //        }
    //        inputStream.close();
    //        baos.close();
    //        return baos.toByteArray();
    //    }
    //
    //    private byte[] readStream2(InputStream inputStream) throws Exception{
    //        byte[] result = new byte[inputStream.available()];
    //        inputStream.read(result);
    //        inputStream.close();
    //        return result;
    //    }
    init {
        Thread(Runnable {
            try {
                val data = getImage("http://172.16.40.10:8080/dog.jpg") ?: return@Runnable
                val bitmap = BitmapFactory.decodeStream(data)
                data.close()
                post {
                    mBitmap = bitmap
                    invalidate()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }).start()
    }
}