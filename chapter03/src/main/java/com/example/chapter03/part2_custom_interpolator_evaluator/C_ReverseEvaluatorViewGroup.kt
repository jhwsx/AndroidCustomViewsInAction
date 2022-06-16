package com.example.chapter03.part2_custom_interpolator_evaluator

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
 * 演示：倒序转换器
 *
 * 既可以通过重写插值器改变数值进度来改变数值位置，也可以通过改变 Evaluator 中数值进度所对
 * 应的具体数值来改变数值位置。
 *
 * @author wangzhichao
 * @date 7/15/20
 */
class C_ReverseEvaluatorViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView

    init {
        inflate(context, R.layout.layout_reverse_evaluator_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
        tv.setOnClickListener {
            Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doAnimation() {
        val valueAnimator = ValueAnimator.ofInt(0, 400)
        valueAnimator.duration = 2000L
        valueAnimator.addUpdateListener { animation ->
            val fraction = animation.animatedFraction
            val currValue = animation.animatedValue as Int
            Log.d(TAG, "onAnimationUpdate: fraction=$fraction,currValue=$currValue")
            tv.translationX = currValue.toFloat()
            tv.translationY = (2 * currValue).toFloat()
            Log.d(TAG, "onAnimationUpdate: left=" + tv.left + ", top=" + tv.top)
        }
        valueAnimator.setEvaluator(ReverseEvaluator())
        valueAnimator.start()
    }

    companion object {
        private const val TAG = "C_ValueAnimatorVarArgsV"
    }
}