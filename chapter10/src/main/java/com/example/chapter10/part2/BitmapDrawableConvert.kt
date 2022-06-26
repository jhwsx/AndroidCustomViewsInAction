package com.example.chapter10.part2

import android.content.Context
import com.example.chapter10.R
import android.graphics.drawable.Drawable
import com.example.chapter10.part1.CircledDrawable
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.example.common.dp

/**
 * https://blog.csdn.net/l_lhc/article/details/50923372
 * @author wangzhichao
 * @date 2019/10/15
 */
class BitmapDrawableConvert(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate((width / 2).toFloat(), (height / 2).toFloat())
        canvas.drawText("演示 Drawable，Bitmap 的相互转换，去看代码吧", 0f, 0f, paint)
    }

    companion object {
        fun drawableToBitmap(drawable: Drawable): Bitmap {
            val w = drawable.intrinsicWidth
            val h = drawable.intrinsicHeight
            val config =
                if (drawable.opacity != PixelFormat.OPAQUE) Bitmap.Config.ARGB_8888 else Bitmap.Config.RGB_565
            val bitmap = Bitmap.createBitmap(w, h, config)
            //注意，下面三行代码要用到，否则在View或者SurfaceView里的canvas.drawBitmap会看不到图
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, w, h)
            drawable.draw(canvas)
            return bitmap
        }
    }

    init {
        paint.textSize = 16.dp
        paint.color = Color.GREEN
        paint.textAlign = Paint.Align.CENTER
        // 1, Drawable 资源转成 Bitmap
        // 1.1
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.avator)
        // 1.2 适用于 21 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val drawable = resources.getDrawable(R.drawable.avator, null)
            val bitmapDrawable = drawable as BitmapDrawable
            val bmp = bitmapDrawable.bitmap
        }
        // 1.3
        val drawable = CircledDrawable(bitmap)
        val bitmap1 = drawableToBitmap(drawable)
        // 2, Bitmap 转 Drawable
        val bitmapDrawable = BitmapDrawable(resources, bitmap)
    }
}