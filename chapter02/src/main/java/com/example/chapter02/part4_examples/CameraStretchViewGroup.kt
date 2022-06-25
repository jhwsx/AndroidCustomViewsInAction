package com.example.chapter02.part4_examples

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * 镜头由远及近效果
 *
 * @author wangzhichao
 * @since 20-4-9
 */
class CameraStretchViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.camera_stretch_viewgroup, this)
        val iv = findViewById<ImageView>(R.id.iv)
        iv.setOnClickListener {
            val animation = ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
            animation.interpolator = BounceInterpolator()
            animation.fillAfter = true
            animation.duration = 6000
            iv.startAnimation(animation)
        }
    }
}