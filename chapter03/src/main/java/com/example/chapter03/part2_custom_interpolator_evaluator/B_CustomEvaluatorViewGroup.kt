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
 * 演示：自定义转换器
 *
 * 既可以通过重写插值器改变数值进度来改变数值位置，也可以通过改变 Evaluator 中数值进度所对
 * 应的具体数值来改变数值位置。
 *
 * @author wangzhichao
 * @date 7/15/20
 */
class B_CustomEvaluatorViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView

    init {
        inflate(context, R.layout.layout_custom_evaluator_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
        tv.setOnClickListener {
            Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doAnimation() {
        ValueAnimator.ofInt(0, 400).apply {
            duration = 2000L
            addUpdateListener { animation ->
                val fraction = animation.animatedFraction
                val currValue = animation.animatedValue as Int
                Log.d(TAG, "onAnimationUpdate: fraction=$fraction,currValue=$currValue")
                tv.translationX = currValue.toFloat()
                tv.translationY = (2 * currValue).toFloat()
            }
            //        setEvaluator(new IntEvaluator()) // ok
//        setEvaluator(new FloatEvaluator()) // error: java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.Float
            setEvaluator(MyEvaluator())
            start()
        }

    }

    companion object {
        private const val TAG = "B_CustomEvaluatorViewGroup"
    }
}