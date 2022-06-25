package com.example.chapter02.part2_view_animation_code

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * 视图动画的渐变动画，使用代码来实现
 *
 * @author wangzhichao
 * @since 20-3-26
 */
class ViewAnimationCodeAlphaViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.view_animation_code_alpha_viewgroup, this)
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_start).setOnClickListener {
            /*
                        <alpha xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="3000"
                            android:fillBefore="true"
                            android:fromAlpha="1.0"
                            android:toAlpha="0.0" />
                         */
            val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
            alphaAnimation.duration = 3000L
            alphaAnimation.fillBefore = true
            tv.startAnimation(alphaAnimation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.alphaanim);
//                tv.startAnimation(animation);
        }
    }
}