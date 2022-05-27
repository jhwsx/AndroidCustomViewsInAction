package com.example.chapter08.part3

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter08.R

/**
 * 图片倒影效果
 * SRC_IN模式是在相交时利用目标图像的透明度来改变源图像的透明度和饱和度。
 * 所以当目标图像的透明度在0-255之间时，就会把源图像的透明度和颜色值都会变小。
 *
 * @author wangzhichao
 * @date 2019/09/26
 */
class B_InvertedImageView_SRCIN(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap: Bitmap
    private val srcBitmap: Bitmap
    private val dstBitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

    init {
        bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog)
        dstBitmap = BitmapFactory.decodeResource(resources, R.drawable.dog_invert_shade)
        val matrix = Matrix()
        matrix.postScale(1f, -1f)
        // 生成倒着的图片
        srcBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val bitmapHeight = height * 0.4f
        val bitmapWidth = bitmapHeight * bitmap.width * 1f / bitmap.height
        rectF.left = 0f
        rectF.right = bitmapWidth
        rectF.top = 0f
        rectF.bottom = bitmapHeight

        // 画出正立的图片
        canvas.drawBitmap(bitmap, null, rectF, paint)

        // 离屏缓冲
        // 新建图层
        val layerId =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
        // 这里是核心代码
        canvas.translate(0f, bitmapHeight)
        // 绘制目标图像，在下层
        canvas.drawBitmap(dstBitmap, null, rectF, paint)
        // 给画笔设置混合模式 SRC_IN
        paint.xfermode = xfermode
        // 绘制源图像，在上层
        canvas.drawBitmap(srcBitmap, null, rectF, paint)
        // 清除画笔的混合模式
        paint.xfermode = null
        // 还原图层
        canvas.restoreToCount(layerId)
    }
}