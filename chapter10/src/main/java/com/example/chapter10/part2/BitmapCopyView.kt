package com.example.chapter10.part2

import android.annotation.SuppressLint
import android.content.Context
import com.example.chapter10.R
import android.text.TextPaint
import android.text.StaticLayout
import android.graphics.*
import android.text.Layout
import android.util.AttributeSet
import android.util.Log
import android.view.*
import com.example.common.dp
import java.lang.Exception
import java.lang.StringBuilder

/**
 * Bitmap.copy() 方法的使用
 * @author wangzhichao
 * @date 2019/10/31
 */
class BitmapCopyView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val sb = StringBuilder()
    private val textPaint = TextPaint()
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val staticLayout = StaticLayout(sb.toString(),
            textPaint,
            width,
            Layout.Alignment.ALIGN_NORMAL,
            1f,
            0f,
            false)
        staticLayout.draw(canvas)
    }

    init {
        // 先加载一张图片
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog)
        Log.d("BitmapCopyView", "bitmap.isMutable() = " + bitmap.isMutable)
        //
        val copy1 = bitmap.copy(Bitmap.Config.ARGB_8888, true)
        Log.d("BitmapCopyView", "copy1.isMutable() = " + copy1.isMutable)
        //
        val copy2 = bitmap.copy(Bitmap.Config.ARGB_8888, false)
        Log.d("BitmapCopyView", "copy2.isMutable() = " + copy2.isMutable)
        try {
            copy2.setPixel(1, 1, 0x000000)
        } catch (e: Exception) {
            Log.e("BitmapCopyView", "error: ", e)
        }
        sb.append("1,通过 BitmapFactory 加载的 Bitmap 都是像素不可变的，只有通过 Bitmap 中的几个函数创建的 Bitmap 才是像素可变的；\n")
            .append("2,对于像素不可变的 Bitmap，是不可以作为画布的；\n")
            .append("3,使用 bitmap.copy() 方法可以实现像素可变与不可变的转化.")
        textPaint.color = Color.GREEN
        textPaint.textSize = 20.dp
        textPaint.style = Paint.Style.FILL
    }
}