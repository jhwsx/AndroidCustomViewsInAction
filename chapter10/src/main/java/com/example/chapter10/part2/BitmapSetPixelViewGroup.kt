package com.example.chapter10.part2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.chapter10.R

/**
 * @author wangzhichao
 * @since 2019/12/10
 */
class BitmapSetPixelViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_bitmap_setpixel_viewgroup, this)
        val ivOriginal = findViewById<ImageView>(R.id.ivOriginal)
        val ivNew = findViewById<ImageView>(R.id.ivNew)
        val srcBmp = BitmapFactory.decodeResource(resources, R.drawable.dog)
        ivOriginal.setImageBitmap(srcBmp)
        // 变为可变的位图
        val desBmp = srcBmp.copy(Bitmap.Config.ARGB_8888, true)
        for (w in 0 until desBmp.width) {
            for (h in 0 until desBmp.height) {
                val originColor = desBmp.getPixel(w, h)
                val red = Color.red(originColor)
                var green = Color.green(originColor)
                val blue = Color.blue(originColor)
                val alpha = Color.alpha(originColor)
                if (green < 200) {
                    green += 30
                }
                desBmp.setPixel(w, h, Color.argb(alpha, red, green, blue))
            }
        }
        ivNew.setImageBitmap(desBmp)
    }
}