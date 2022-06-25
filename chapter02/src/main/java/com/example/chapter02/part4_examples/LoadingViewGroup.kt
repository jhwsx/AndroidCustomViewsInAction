package com.example.chapter02.part4_examples

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * 加载框效果
 *
 * @author wangzhichao
 * @since 20-4-9
 */
class LoadingViewGroup(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.loading_viewgroup, this)
        val iv = findViewById<ImageView>(R.id.iv)
        val animation = RotateAnimation(0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        animation.repeatCount = Animation.INFINITE
        animation.interpolator = LinearInterpolator()
        animation.duration = 2000L
        iv.startAnimation(animation)
    }
}