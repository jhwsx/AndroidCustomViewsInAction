package com.example.chapter07.part3_maskfilter

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.chapter07.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/09/20
 */
class A_PaintSetMaskFilterView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val innerBlurMaskFilter: BlurMaskFilter
    private val solidBlurMaskFilter: BlurMaskFilter
    private val normalBlurMaskFilter: BlurMaskFilter
    private val outerBlurMaskFilter: BlurMaskFilter
    private val textSolidBlurMaskFilter: BlurMaskFilter
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 12.dp
        color = Color.BLACK
    }
    private val pic: Bitmap
    private val avatar: Bitmap
    private val dst = RectF()
    init {
        // 发光效果不支持硬件加速，所以下面这行代码必须要写上
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        /**
         * 参数一：模糊半径
         * 参数二：发光样式
         * BlurMaskFilter.Blur.INNER 内发光
         * BlurMaskFilter.Blur.SOLID 在边界内部填实，外发光
         * BlurMaskFilter.Blur.NORMAL 内外发光
         * BlurMaskFilter.Blur.OUTER 在边界内部什么都不填，外发光
         */
        innerBlurMaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.INNER)
        solidBlurMaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.SOLID)
        normalBlurMaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.NORMAL)
        outerBlurMaskFilter = BlurMaskFilter(50f, BlurMaskFilter.Blur.OUTER)
        textSolidBlurMaskFilter = BlurMaskFilter(5f, BlurMaskFilter.Blur.SOLID)
        pic = BitmapFactory.decodeResource(resources, R.drawable.pic)
        avatar = ImageUtils.decodeSampleBitmapFromResource(context.resources, R.drawable.avatar, 100.dp.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED
        paint.maskFilter = innerBlurMaskFilter
        canvas.drawCircle(150f, 200f, 100f, paint)
        canvas.drawText("内发光", 300f, 200f, textPaint)
        paint.maskFilter = solidBlurMaskFilter
        canvas.drawCircle(600f, 200f, 100f, paint)
        canvas.drawText("外发光", 750f, 200f, textPaint)
        paint.maskFilter = normalBlurMaskFilter
        canvas.drawCircle(150f, 500f, 100f, paint)
        canvas.drawText("内外发光", 300f, 500f, textPaint)
        paint.maskFilter = outerBlurMaskFilter
        canvas.drawCircle(600f, 500f, 100f, paint)
        canvas.drawText("仅显示外发光", 750f, 500f, textPaint)

        paint.maskFilter = textSolidBlurMaskFilter
        paint.textSize = 40f
        paint.style = Paint.Style.FILL
        canvas.drawText("我的边缘是什么颜色，就发什么光", 100f, 700f, paint)
        paint.maskFilter = solidBlurMaskFilter
        dst.set(100f, 800f, (100 + pic.width).toFloat(), (800 + pic.height).toFloat())
        canvas.drawBitmap(pic, null, dst, paint)
        canvas.drawBitmap(avatar, dst.right + 100, 800f, paint)
    }
}