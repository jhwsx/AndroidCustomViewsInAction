package com.example.chapter03.part4_objectanimator

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.chapter03.R
import com.example.chapter03.part3_valueanimator_advanced_ofobject.FallingBallEvaluator

/**
 *
 * @author wangzhichao
 * @since 2022/6/15
 */
class B_ObjectAnimatorFallingBallViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        inflate(context, R.layout.layout_objectanimator_falling_ball_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        val ball = findViewById<ImageView>(R.id.iv)
        btnStartAnim.setOnClickListener {
            ObjectAnimator.ofObject(ball, "fallingPos", FallingBallEvaluator(), Point(0,0), Point(500,500)).apply {
                duration = 2000
                start()
            }
        }
    }
}