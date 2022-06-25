package com.example.chapter02.part3_interpolator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * 设置插值器
 * 通过 xml
 * 通过代码
 *
 * @author wangzhichao
 * @since 20-3-26
 */
class InterpolatorSetViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.interpolator_set_viewgroup, this)
        val btnXml = findViewById<Button>(R.id.btn_xml)
        val tvXml = findViewById<TextView>(R.id.tv_xml)
        btnXml.setOnClickListener { v ->
            val animation = AnimationUtils.loadAnimation(v.context, R.anim.alphaanim_interpolator)
            tvXml.startAnimation(animation)
        }
        val tvCode = findViewById<TextView>(R.id.tv_code)
        findViewById<View>(R.id.btn_code).setOnClickListener { /*
                <alpha xmlns:android="http://schemas.android.com/apk/res/android"
                    android:duration="3000"
                    android:fillBefore="true"
                    android:fromAlpha="1.0"
                    android:toAlpha="0.0"
                    android:interpolator="@android:anim/accelerate_interpolator"/>
                 */
            val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
            alphaAnimation.duration = 3000L
            alphaAnimation.interpolator = AccelerateInterpolator()
            //                alphaAnimation.setInterpolator(v.getContext(), android.R.anim.accelerate_interpolator);
            tvCode.startAnimation(alphaAnimation)
        }
    }
}