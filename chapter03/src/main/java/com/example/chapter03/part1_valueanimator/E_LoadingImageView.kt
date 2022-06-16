package com.example.chapter03.part1_valueanimator

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import com.example.chapter03.R

/**
 *
 * @author wangzhichao
 * @since 2022/6/15
 */
class E_LoadingImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : AppCompatImageView(context, attrs) {
    private val valueAnimator: ValueAnimator?
    private var count = 0

    init {
        valueAnimator = ValueAnimator.ofInt(0, -100, 0).apply {
            removeAllUpdateListeners()
            removeAllListeners()
            duration = 1000L
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animator ->
                val currentValue = animator.animatedValue as Int
                translationY = currentValue.toFloat()
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationRepeat(animation: Animator) {
                    super.onAnimationRepeat(animation)
                    Log.d(TAG, "onAnimationRepeat: ")
                    count++
                    when (count % 3) {
                        0 -> setImageResource(R.drawable.pic_1)
                        1 -> setImageResource(R.drawable.pic_2)
                        2 -> setImageResource(R.drawable.pic_3)
                    }
                }
            })
            start()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners()
            valueAnimator.removeAllUpdateListeners()
            valueAnimator.cancel()
        }
    }

    companion object {
        private const val TAG = "E_LoadingImageView"
    }
}