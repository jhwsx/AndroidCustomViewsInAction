package com.example.chapter08.part4

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.chapter08.R

/**
 * 不规则波纹
 *
 * @author wangzhichao
 * @date 2019/09/29
 */
class E_IrregularWaveView_DSTIN(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val waveDstBmp: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.wave_bg)
    private val circleSrcBmp: Bitmap =
        BitmapFactory.decodeResource(resources, R.drawable.circle_shape)
    private var position = 0
    private lateinit var va: ValueAnimator
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    private val rect = Rect()
    private val rectF = RectF()
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val defaultWidth = circleSrcBmp.width
        val defaultHeight = circleSrcBmp.height
        val width = if (widthMode == MeasureSpec.EXACTLY) widthSize else defaultWidth
        val height = if (heightMode == MeasureSpec.EXACTLY) heightSize else defaultHeight
        setMeasuredDimension(width, height)
        startAnim()
    }

    private fun startAnim() {
        va = ValueAnimator.ofInt(0, waveDstBmp.width - circleSrcBmp.width)
        va.duration = 3000L
        va.interpolator = LinearInterpolator()
        va.repeatMode = ValueAnimator.RESTART
        va.repeatCount = ValueAnimator.INFINITE
        va.addUpdateListener { animation ->
            val curr = animation.animatedValue as Int
            position = curr
            invalidate()
        }
        va.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 先画上圆形
        canvas.drawBitmap(circleSrcBmp, 0f, 0f, paint)
        // 离屏缓冲
        // 新建图层
        val layer =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null)
        // 这里是核心代码
        // 绘制目标图像，在下层，不规则波纹
        // 这句代码是取出 dstBmp 的这个矩形 new Rect(position, 0, position + srcBmp.getWidth(), srcBmp.getHeight()) 位置，
        // 绘制在目标矩形 new RectF(0, 0, srcBmp.getWidth(), srcBmp.getHeight()) 的地方。
        rect.set(position, 0, position + circleSrcBmp.width, circleSrcBmp.height)
        rectF.set(0f, 0f, circleSrcBmp.width.toFloat(), circleSrcBmp.height.toFloat())
        canvas.drawBitmap(waveDstBmp, rect, rectF, paint)
        // 给画笔设置混合模式DST_IN
        paint.xfermode = xfermode
        // 绘制源图像，在上层，是圆
        canvas.drawBitmap(circleSrcBmp, 0f, 0f, paint)
        // 清空画笔设置的混合模式
        paint.xfermode = null
        // 还原图层
        canvas.restoreToCount(layer)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        va.cancel()
    }
}