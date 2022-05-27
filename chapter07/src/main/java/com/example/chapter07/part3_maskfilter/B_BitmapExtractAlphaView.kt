package com.example.chapter07.part3_maskfilter

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter07.R
import com.example.common.dp

/**
 * 为 Bitmap 添加阴影，并且指定阴影的颜色
 * @author wangzhichao
 * @date 2019/09/20
 */
class B_BitmapExtractAlphaView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val bitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val blurMaskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)
    private val rect: RectF = RectF()
    private val alphaBitmap: Bitmap
    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.avatar)
        // 1, 绘制出一个一样大小的灰色图形
        alphaBitmap = bitmap.extractAlpha() // 生成仅具有透明度的空白图像
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val imageWidth = width * 0.4f
        val imageHeight = imageWidth * alphaBitmap.height / alphaBitmap.width
        val dx = 15.dp
        val dy = 15.dp

        // 2, 对灰色图形应用BlurMaskFilter使其内外发光
        paint.color = Color.RED
        paint.maskFilter = blurMaskFilter
        rect.set(dx, dy, imageWidth + dx, imageHeight + dy)
        canvas.drawBitmap(alphaBitmap, null, rect, paint)
        paint.color = Color.GREEN
        rect.set(dx, imageHeight * 1.2f + dy, imageWidth + dx, imageHeight * 2.2f + dy)
        canvas.drawBitmap(alphaBitmap, null, rect, paint) // 在绘制仅具有透明度的空白图像时，图像的颜色是由画笔指定的。
        paint.maskFilter = null // 清除发光效果

        // 3, 偏移原图形一段距离绘制出来
        rect.set(0f, 0f, imageWidth, imageHeight)
        canvas.drawBitmap(bitmap, null, rect, paint)
        rect.set(0f, imageHeight * 1.2f, imageWidth, imageHeight * 2.2f)
        canvas.drawBitmap(bitmap, null, rect, paint)
    }
}