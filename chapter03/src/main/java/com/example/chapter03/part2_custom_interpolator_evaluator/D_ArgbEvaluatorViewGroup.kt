package com.example.chapter03.part2_custom_interpolator_evaluator

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.chapter03.R

/**
 * 演示：Argb转换器
 *
 * @author wangzhichao
 * @date 7/15/20
 */
class D_ArgbEvaluatorViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView

    init {
        inflate(context, R.layout.layout_argb_evaluator_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
        tv.setOnClickListener {
            Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doAnimation() {
        ValueAnimator.ofInt(0xffffff00.toInt(), 0xff0000ff.toInt()).apply {
            duration = 3000L
            addUpdateListener { animation ->
                val fraction = animation.animatedFraction
                val currValue = animation.animatedValue as Int
                Log.d(TAG, "onAnimationUpdate: fraction=$fraction,currValue=$currValue")
                tv.setBackgroundColor(currValue)
            }
            setEvaluator(ArgbEvaluator())
            start()
        }
    }

    companion object {
        private const val TAG = "D_ArgbEvaluatorViewGroup"
    }
}