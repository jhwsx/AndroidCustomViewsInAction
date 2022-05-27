package com.example.chapter08.part4

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import com.example.chapter08.R

/**
 * 腾讯课堂加载波纹效果
 * @author wangzhichao
 * @since 2022/5/24
 */
class C_TencentClassWaveView_DSTIN @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val tencentClassSrcBmp: Bitmap =
        BitmapFactory.decodeResource(resources, R.drawable.tencent_class)
    private val w = tencentClassSrcBmp.width
    private val h = tencentClassSrcBmp.height
    private val waveDstBmp: Bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
    private val waveCanvas = Canvas(waveDstBmp)
    private val waveLength = w / 3f
    private val quarterWaveLength = waveLength / 4
    private val halfWaveLength = waveLength / 2
    private var waveHeight = h.toFloat()
    private val path = Path()
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
    private lateinit var valueAnimator1: ValueAnimator
    private lateinit var valueAnimator2: ValueAnimator
    private lateinit var valueAnimator3: ValueAnimator
    private var progress1 = 0
    private var progress2 = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        valueAnimator1 = ValueAnimator.ofInt(0, waveLength.toInt()).apply {
            duration = 500L
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener { animation ->
                progress1 = animation.animatedValue as Int
                invalidate()
            }
            start()
        }
        valueAnimator2 = ValueAnimator.ofInt(waveLength.toInt(), 0).apply {
            duration = 300L
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener { animation ->
                progress2 = animation.animatedValue as Int
                invalidate()
            }
            start()
        }
        valueAnimator3 = ValueAnimator.ofInt(height, 0).apply {
            duration = 2000L
            interpolator = AccelerateInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener { animation ->
                waveHeight = (animation.animatedValue as Int).toFloat()
                invalidate()
            }
            start()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(tencentClassSrcBmp, 0f, 0f, paint)
        waveCanvas.drawColor(Color.BLACK, PorterDuff.Mode.CLEAR)
        updateWavePath(progress2, height / 25f)
        paint.color = Color.parseColor("#9900A2E8")
        waveCanvas.drawPath(path, paint)
        paint.color = Color.parseColor("#E600A2E8")
        updateWavePath(progress1, height / 20f)
        waveCanvas.drawPath(path, paint)
        val layerId = canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
        canvas.drawBitmap(waveDstBmp, 0f, 0f, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(tencentClassSrcBmp, 0f, 0f, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(w, h)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valueAnimator1.cancel()
        valueAnimator2.cancel()
    }

    private fun updateWavePath(progress: Int, amplitude: Float) {
        path.reset()
        path.moveTo(-waveLength + progress, waveHeight)
        var start = -waveLength + progress
        while (start <= width + waveLength) {
            path.rQuadTo(quarterWaveLength, amplitude, halfWaveLength, 0f)
            path.rQuadTo(quarterWaveLength, -amplitude, halfWaveLength, 0f)
            start += waveLength
        }
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(0f, height.toFloat())
        path.close()
    }
}