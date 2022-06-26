package com.example.chapter10.part2

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView

/**
 * 单击描边效果
 *
 * 思路：
 * 1，给图片设置一个 selector 作为背景；
 * 2，给图片添加 padding 和可点击。
 *
 * @author wangzhichao
 * @date 2019/12/02
 */
class StrokeImage @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    // 在系统将 XML 解析出对应的控件实例的时候调用。这时候控件已经生成，但还没有被使用，
    // 可以在这里对控件做一些基础设置。
    override fun onFinishInflate() {
        super.onFinishInflate()
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        setStateDrawable(this, paint)
    }

    private fun setStateDrawable(imageView: ImageView, paint: Paint) {
        // 制作纯色背景
        val drawable = imageView.drawable
        val srcBitmap = drawableToBitmap(drawable)
        val bitmap = Bitmap.createBitmap(srcBitmap.width, srcBitmap.height, Bitmap.Config.ARGB_8888)
        val alphaBitmap = srcBitmap.extractAlpha()
        val c = Canvas(bitmap)
        paint.color = Color.CYAN
        c.drawBitmap(alphaBitmap, 0f, 0f, paint)
        // 添加状态
        val stateListDrawable = StateListDrawable()
        stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), BitmapDrawable(bitmap))
        background = stateListDrawable
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
}