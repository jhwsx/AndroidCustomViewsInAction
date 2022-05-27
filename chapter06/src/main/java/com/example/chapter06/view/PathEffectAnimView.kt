package com.example.chapter06.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * @author wangzhichao
 * @date 2019/09/18
 */
class PathEffectAnimView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var phase = 0
    private var path1 = Path()
    private var path2 = Path()
    private var paint = Paint()
    private var rect: RectF? = null
    private var valueAnimator: ValueAnimator? = null

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (rect == null) {
            rect = RectF(width * 0.2f, height * 0.3f, width * 0.8f, height * 0.7f)
        }
        paint.isAntiAlias = true
        paint.strokeWidth = 10f
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.isLinearText = false
        path1.reset()
        path1.addRect(rect!!, Path.Direction.CCW)
        paint.pathEffect = DashPathEffect(floatArrayOf(40f, 20f), phase.toFloat())
        canvas.drawPath(path1, paint)
        paint.isAntiAlias = true
        paint.strokeWidth = 10f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        paint.isLinearText = false
        path2.reset()
        path2.addRect(rect!!, Path.Direction.CCW)
        paint.pathEffect = DashPathEffect(floatArrayOf(0f, 40f, 20f, 0f), phase.toFloat())
        canvas.drawPath(path2, paint)
    }

    private fun setPhase(phase: Int) {
        this.phase = phase
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        valueAnimator  = ValueAnimator.ofInt(0, 60).apply {
            duration = 500L
            interpolator = LinearInterpolator()
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animation ->
                val current = animation.animatedValue as Int
                setPhase(current)
                postInvalidate()
            }
            start()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valueAnimator?.cancel()
    }
}