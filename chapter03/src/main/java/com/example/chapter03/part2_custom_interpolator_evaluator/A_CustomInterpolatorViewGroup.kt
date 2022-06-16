package com.example.chapter03.part2_custom_interpolator_evaluator

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.chapter03.R

/**
 * 演示：自定义插值器
 *
 * @author wangzhichao
 * @date 7/15/20
 */
class A_CustomInterpolatorViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView
    init {
        inflate(context, R.layout.layout_custom_interpolator_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
        tv.setOnClickListener { v: View? ->
            Toast.makeText(getContext(),
                "click me",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun doAnimation() {
        ValueAnimator.ofFloat(0f, 400f).apply {
            duration = 2000L
            interpolator = MyInterpolator()
            addUpdateListener { animator: ValueAnimator ->
                val currValue = animator.animatedValue as Float
                tv.translationX = currValue
                tv.translationY = 2 * currValue
            }
            start()
        }
    }
}