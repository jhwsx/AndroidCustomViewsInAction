package com.example.chapter07.part1_bezier

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator

/**
 * @author wangzhichao
 * @date 2019/09/19
 */
class F_WaveView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var waveLength: Int = 0
    private var waveHeight = 300
    private val path = Path()
    private var progress1 = 0
    private var progress2 = 0
    private lateinit var valueAnimator1: ValueAnimator
    private lateinit var valueAnimator2: ValueAnimator
    init {
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL_AND_STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        waveLength = width / 3
        valueAnimator1 = ValueAnimator.ofInt(0, waveLength).apply {
            duration = 1000L
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener { animation ->
                progress1 = animation.animatedValue as Int
                invalidate()
            }
            start()
        }
        valueAnimator2 = ValueAnimator.ofInt(waveLength, 0).apply {
            duration = 800L
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener { animation ->
                progress2 = animation.animatedValue as Int
                invalidate()
            }
            start()
        }
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.parseColor("#EEEEEE"))
        // 绘制浅蓝波
        drawWavePath(canvas, progress2, Color.parseColor("#90DBF6"), 20f)
        // 绘制深蓝波
        drawWavePath(canvas, progress1, Color.parseColor("#4ACDFD"), 30f)
    }

    private fun drawWavePath(canvas: Canvas, progress: Int, color: Int, amplitude: Float) {
        val halfWaveLength = waveLength / 2
        // 绘制波浪区域路径
        path.reset()
        // 波浪之左上角的点，起始位置向左一个波长
        path.moveTo((-waveLength + progress).toFloat(), waveHeight.toFloat())
        var i = -waveLength
        while (i <= width + waveLength) {
            // 绘制前半个波
            path.rQuadTo((halfWaveLength / 2).toFloat(), -amplitude, halfWaveLength.toFloat(), 0f)
            // 绘制后半个波
            path.rQuadTo((halfWaveLength / 2).toFloat(), amplitude, halfWaveLength.toFloat(), 0f)
            i += waveLength
        }
        path.lineTo(width.toFloat(), height.toFloat())
        path.lineTo(0f, height.toFloat())
        path.close()
        paint.color = color
        canvas.drawPath(path, paint)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valueAnimator1.cancel()
        valueAnimator2.cancel()
    }
}