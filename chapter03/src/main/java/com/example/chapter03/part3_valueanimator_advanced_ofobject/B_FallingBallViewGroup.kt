package com.example.chapter03.part3_valueanimator_advanced_ofobject

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.chapter03.R

/**
 * 抛物动画
 *
 * @author wangzhichao
 * @since 2021/5/11
 */
class B_FallingBallViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private lateinit var valueAnimator: ValueAnimator
    var initialLeft = 0
    var initialTop = 0

    init {
        inflate(context, R.layout.layout_falling_ball_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        val ball = findViewById<ImageView>(R.id.iv)
        ball.post {
            initialLeft = ball.left
            initialTop = ball.top
        }
        btnStartAnim.setOnClickListener {
            valueAnimator =
                ValueAnimator.ofObject(FallingBallEvaluator(), Point(0, 0), Point(500, 500)).apply {
                    duration = 2000
                    addUpdateListener { animator ->
                        val currValue = animator.animatedValue as Point
                        ball.layout(
                            initialLeft + currValue.x,
                            initialTop + currValue.y,
                            initialLeft + ball.width + currValue.x,
                            initialTop + ball.height + currValue.y)
                    }
                    start()
                }

        }
    }
}