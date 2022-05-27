package com.example.chapter08.part2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter08.R

/**
 * @author wangzhichao
 * @date 2019/09/23
 */
class B_LightBookView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val dstBmp: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.book_bg)
    private val srcBmp: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.book_light)
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.LIGHTEN)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 使用离屏缓存
        // 新建图层
        val layerId =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
        // 这里是核心代码
        // 绘制目标图像，在底部
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        // 设置混合模式
        paint.xfermode = xfermode
        // 绘制源图像，在顶部
        canvas.drawBitmap(srcBmp, 0f, 0f, paint)
        // 清空混合模式的设置
        paint.xfermode = null
        // 还原图层
        canvas.restoreToCount(layerId)
    }
}