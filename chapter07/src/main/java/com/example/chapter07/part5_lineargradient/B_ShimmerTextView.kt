package com.example.chapter07.part5_lineargradient

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView

/**
 * TODO 参考学习：https://github.com/facebook/shimmer-android
 * @author wangzhichao
 * @date 2019/09/22
 */
class B_ShimmerTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    private var linearGradient: LinearGradient? = null
    private lateinit var valueAnimator: ValueAnimator
    private var offsetX: Int = 0
    private val mtx = Matrix()
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        // 用于移动线性渐变区域的动画，总移动范围是两个文字宽度
        valueAnimator = ValueAnimator.ofInt(0, 2 * measuredWidth).apply {
            duration = 2000L
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.RESTART
            addUpdateListener {
                offsetX = it.animatedValue as Int
                invalidate()
            }
            start()
        }

        val colors = intArrayOf(currentTextColor, Color.parseColor("#FFFF0F0F"), currentTextColor)
        val positions = floatArrayOf(0f, 0.5f, 1f)
        if (linearGradient == null) {
            // 线性渐变的区域是从负的文字宽度到文字起始的地方
            // 当控件区域不在线性渐变的区域范围内时，采用边缘填充模式。
            linearGradient = LinearGradient(-measuredWidth.toFloat() , 0f, 0f, 0f, colors, positions, Shader.TileMode.CLAMP)
        }
    }

    override fun onDraw(canvas: Canvas) {
        mtx.setTranslate(offsetX.toFloat(), 0f)
        linearGradient!!.setLocalMatrix(mtx)
        // 一定要用 TextView 里面的 paint，不要自己再创建一个 Paint 了。
        paint.shader = linearGradient
        // 在 TextView 开始绘制之前设置着色器。
        super.onDraw(canvas)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        valueAnimator.cancel()
    }

    companion object {
        private const val TAG = "ShimmerTextView"
    }
}