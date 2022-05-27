package com.example.chapter08.part4

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.chapter08.R

/**
 * 区域波纹效果：在不规则区域中显示波纹效果
 *
 * @author wangzhichao
 * @date 2019/09/27
 */
class C_AreaWaveView_DSTIN(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val textShadeSrcBmp: Bitmap
    private val waveDstBmp: Bitmap
    private val path = Path()
    private val paint = Paint()
    private val waveLength: Float
    private val halfWaveLength: Float
    private val height: Float
    private val amplitude: Float
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    private val waveCanvas: Canvas
    private var valueAnimator: ValueAnimator? = null

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        // 不规则区域用作源图像
        textShadeSrcBmp = BitmapFactory.decodeResource(resources, R.drawable.text_shade)
        waveDstBmp = Bitmap.createBitmap(textShadeSrcBmp.width,
            textShadeSrcBmp.height,
            Bitmap.Config.ARGB_8888)
        waveCanvas = Canvas(waveDstBmp)
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = Color.GREEN
        waveLength = textShadeSrcBmp.width / 3f
        halfWaveLength = waveLength / 2
        height = 0.5f * textShadeSrcBmp.height
        amplitude = 50f
        startAnimation()
    }

    private fun startAnimation() {
        valueAnimator = ValueAnimator.ofFloat(0f, waveLength).apply {
            duration = 2000L
            interpolator = LinearInterpolator()
            addUpdateListener { animation ->
                val curr = animation.animatedValue as Float
                updateWavePath(curr)
            }
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            start()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valueAnimator?.cancel()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val defaultWidth = textShadeSrcBmp.width
        val defaultHeight = textShadeSrcBmp.height
        val width = if (widthMode == MeasureSpec.EXACTLY) widthSize else defaultWidth
        val height = if (heightMode == MeasureSpec.EXACTLY) heightSize else defaultHeight
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.BLACK)
        // 先清空 dstBmp 上的图像，再绘制 path
        waveCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR)
        waveCanvas.drawPath(path, paint)

        // 绘制src图像，才能显示文字全貌
        canvas.drawBitmap(textShadeSrcBmp, 0f, 0f, paint)
        // 离屏缓冲
        // 新建图层
        val layerId = canvas.saveLayer(0f,
            0f,
            width.toFloat(),
            getHeight().toFloat(),
            paint)
        // 这里是核心代码
        // 绘制目标图像，在下层，水波纹图像
        canvas.drawBitmap(waveDstBmp, 0f, 0f, paint)
        // 给画笔添加混合模式DST_IN
        paint.xfermode = xfermode
        // 绘制源图像，在上层，不规则区域图像
        canvas.drawBitmap(textShadeSrcBmp, 0f, 0f, paint)
        // 清除画笔的混合模式
        paint.xfermode = null
        // 还原图层
        canvas.restoreToCount(layerId)
    }

    private fun updateWavePath(progress: Float) {
        path.reset()
        path.moveTo(-waveLength + progress, height)
        var i = -waveLength + progress
        while (i <= width + waveLength) {
            // 这里要注意的是相对的是起始点后者是上一个控制点。
            path.rQuadTo(halfWaveLength / 2, amplitude, halfWaveLength, 0f)
            path.rQuadTo(halfWaveLength / 2, -amplitude, halfWaveLength, 0f)
            i += waveLength
        }
        path.lineTo(width.toFloat(), getHeight().toFloat())
        path.lineTo(0f, getHeight().toFloat())
        path.close()
        invalidate()
    }
}