package com.example.chapter04.part1

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.chapter04.R

/**
 *
 * @author wangzhichao
 * @since 2022/6/22
 */
class D_TelephoneRingViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val iv: ImageView
    init {
        inflate(context, R.layout.layout_telephone_ring, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        iv = findViewById(R.id.iv)
        btnStartAnim.setOnClickListener { doAnimation() }
    }

    private fun doAnimation() {
        val rotationKeyframes = mutableListOf<Keyframe>()
        for (i in 0 .. 10) {
            val fraction = i * 0.1f
            val value = when {
                i == 0 || i == 10 -> 0f
                i % 2 == 0 -> 20f
                else -> -20f
            }
            Log.d(TAG, "doAnimation: fraction=$fraction, value=$value")
            rotationKeyframes.add(Keyframe.ofFloat(fraction, value))
        }
        // 左右震动
        val rotationHolder = PropertyValuesHolder.ofKeyframe("rotation", *rotationKeyframes.toTypedArray())
        // 放大效果
        val scaleKeyframes = mutableListOf<Keyframe>().apply {
            add(Keyframe.ofFloat(0f, 1f))
            add(Keyframe.ofFloat(0.1f, 1.1f))
            add(Keyframe.ofFloat(0.9f, 1.1f))
            add(Keyframe.ofFloat(1f, 1f))
        }
        val scaleXHolder = PropertyValuesHolder.ofKeyframe("scaleX", *scaleKeyframes.toTypedArray())
        val scaleYHolder = PropertyValuesHolder.ofKeyframe("scaleY", *scaleKeyframes.toTypedArray())
        // 同时播放
        ObjectAnimator.ofPropertyValuesHolder(iv, rotationHolder, scaleXHolder, scaleYHolder).apply {
            duration = 1000L
            start()
        }
    }

    companion object {
        private const val TAG = "D_TelephoneRingViewGrou"
    }
}