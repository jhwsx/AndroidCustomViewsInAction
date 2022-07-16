package com.example.chapter10.part2.section5

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.*
import com.example.chapter10.R
import com.example.common.dp

/**
 * 这个例子展示 Bitmap 的带参数的 extractAlpha 的用法
 * 1，获取 Alpha Bitmap；
 * 2，创建 Bitmap；
 * 3，绘制到画布上。
 *
 * @author wangzhichao
 * @date 2019/12/02
 */
class C_BitmapExtractAlphaWithParamsView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG)
    private val offsetXY: IntArray
    private val bitmap: Bitmap
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

    init {
        // 获取 Alpha Bitmap
        val srcBitmap = BitmapFactory.decodeResource(resources, R.drawable.cat_dog)
        paint.maskFilter = BlurMaskFilter(10.dp, BlurMaskFilter.Blur.NORMAL)
        offsetXY = IntArray(2)
        // offsetXY 就是相对源图像的建议绘制起始位置，其取值并不一定与 BlurMaskFilter 的模糊半径一致。
        val alphaBitmap = srcBitmap.extractAlpha(paint, offsetXY)
        Log.d("wzc", "offsetX = " + offsetXY[0] + ", offsetY = " + offsetXY[1])
        // 创建 Bitmap，在 onDraw 方法里面要用到的 Bitmap。
        bitmap = Bitmap.createBitmap(alphaBitmap.width, alphaBitmap.height, Bitmap.Config.ARGB_8888)
        val c = Canvas(bitmap)
        // 清除之前设置的值
        paint.maskFilter = null
        paint.color = Color.CYAN
        // 绘制发光
        c.drawBitmap(alphaBitmap, 0f, 0f, paint) // 这里给 alphaBitmap 填充天蓝色。
        // 绘制源图像
        c.drawBitmap(srcBitmap, -offsetXY[0].toFloat(), -offsetXY[1].toFloat(), paint)
    }
}