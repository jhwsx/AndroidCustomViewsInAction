package com.example.chapter07.part6_radialgradient

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.FloatEvaluator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.sqrt

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
class B_RippleView(context: Context?, attrs: AttributeSet?) : AppCompatImageView(
    context!!, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var centerX = 0f
    private var centerY = 0f
    private var objectAnimator: ObjectAnimator? = null
    private var maxRadius = 0f
    // 不要加 private
    var radius = 0f
        set(value) {
            field = value
            if (field > 0) {
                // 径向渐变着色器
                val radialGradient = RadialGradient(centerX,
                    centerY,
                    // 不断改变的半径，不可以为 0
                    field,
                    // 从透明色 -> 天蓝色
                    Color.parseColor("#00FFFFFF"),
                    Color.parseColor("#FF58FAAC"),
                    // 空余部分使用 clamp 填充实现水波纹
                    Shader.TileMode.CLAMP)
                paint.shader = radialGradient
            }
            invalidate()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (maxRadius == 0f) {
            maxRadius = sqrt((width * width + height * height).toDouble()).toFloat()
        }
        if (radius > 0) {
            // 绘制的圆和径向渐变区域是一样大的。
            canvas.drawCircle(centerX, centerY, radius, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "onTouchEvent: ACTION_DOWN")
                if (objectAnimator != null && objectAnimator!!.isRunning) {
                    Log.d(TAG, "onTouchEvent: cancel running anim")
                    objectAnimator!!.cancel()
                }
                // 手指按下时，绘制一个默认大小的圆圈
                centerX = event.x
                centerY = event.y
                radius = DEFAULT_RADIUS
                return true
            }
            MotionEvent.ACTION_UP -> {
                Log.d(TAG, "onTouchEvent: ACTION_UP")
                // 手指抬起时，绘制扩散的圆圈
                if (objectAnimator == null) {
                    objectAnimator = ObjectAnimator.ofObject(this,
                        "radius",
                        FloatEvaluator(),
                        DEFAULT_RADIUS,
                        maxRadius).apply {
                        duration = 1000L
                        interpolator = LinearInterpolator()
                        addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                super.onAnimationEnd(animation)
                                radius = 0f
                            }
                        })
                    }
                }
                objectAnimator!!.start()
            }
        }
        return super.onTouchEvent(event)
    }

    companion object {
        const val TAG = "RippleView"
        private const val DEFAULT_RADIUS = 20f
    }
}