package com.example.chapter04.part1

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.util.AttributeSet
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.chapter04.R

/**
 *
 * @author wangzhichao
 * @since 2022/6/22
 */
class E_KeyframeInterpolatorViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val iv: ImageView
    init {
        inflate(context, R.layout.layout_keyframe_interpolator, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        iv = findViewById(R.id.iv)
        btnStartAnim.setOnClickListener { doAnimation() }
    }

    private fun doAnimation() {
        // 给 frame0 设置插值器是无效的
        val frame0 = Keyframe.ofFloat(0f, 0f)
        // 给 frame1 设置的插值器在 frame0 到 frame1 之间起作用
        val frame1 = Keyframe.ofFloat(0.5f, 100f).apply {
//            interpolator = LinearInterpolator()
        }
        // 给 frame2 设置的插值器在 frame1 到 frame2 之间起作用
        val frame2 = Keyframe.ofFloat(1f, 0f).apply {
            interpolator = BounceInterpolator()
        }
        val framesHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2)
        ObjectAnimator.ofPropertyValuesHolder(iv, framesHolder).apply {
            duration = 3000L
            start()
        }
    }
}