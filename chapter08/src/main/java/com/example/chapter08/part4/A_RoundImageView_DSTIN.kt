package com.example.chapter08.part4

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter08.R

/**
 * @author wangzhichao
 * @date 2019/09/24
 */
class A_RoundImageView_DSTIN(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val dstBmp: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog)
    private val srcBmp: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog_shade)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 离屏缓冲
        // 新建图层
        val layer =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
        // 这里是核心代码
        // 绘制目标图像，在底部
        canvas.drawBitmap(dstBmp, 0f, 0f, paint)
        // 给画笔设置混合模式
        paint.xfermode = xfermode
        canvas.drawBitmap(srcBmp, 0f, 0f, paint)
        // 绘制源图像，在顶部
        // 清空画笔的混合模式
        paint.xfermode = null
        // 还原图层
        canvas.restoreToCount(layer)
    }


}