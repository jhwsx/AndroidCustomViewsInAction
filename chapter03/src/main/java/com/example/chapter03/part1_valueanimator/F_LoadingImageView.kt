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
 * 把弹跳加载中动画，封装成一个单独的控件
 *
 * @author wangzhichao
 * @date 7/29/20
 */
class F_LoadingImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(
    context, attrs) {
    private var valueAnimator: ValueAnimator? = null

    /**
     * 图片的总数目
     */
    private val imageCount = 3

    /**
     * 计数器
     */
    private var count = 0
    private var initialTop = 0
    init {
        valueAnimator = ValueAnimator.ofInt(0, -100, 0).apply {
            removeAllUpdateListeners()
            removeAllListeners()
            duration = 1000L
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener { animation ->
                val currentValue = animation.animatedValue as Int
                // 更新的是控件的 top 值
                top = initialTop + currentValue
            }
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationRepeat(animation: Animator) {
                    super.onAnimationRepeat(animation)
                    Log.d(TAG, "onAnimationRepeat: ")
                    count++
                    when (count % imageCount) {
                        0 -> setImageResource(R.drawable.pic_1)
                        1 -> setImageResource(R.drawable.pic_2)
                        2 -> setImageResource(R.drawable.pic_3)
                    }
                }
            })
            start()
        }
    }

    // 每次控件被布局时都会调用这个方法
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout: ")
        this.initialTop = top
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (valueAnimator != null) {
            valueAnimator!!.removeAllListeners()
            valueAnimator!!.removeAllUpdateListeners()
            valueAnimator!!.cancel()
        }
    }

    companion object {
        private const val TAG = "LoadingImageView"
    }


}