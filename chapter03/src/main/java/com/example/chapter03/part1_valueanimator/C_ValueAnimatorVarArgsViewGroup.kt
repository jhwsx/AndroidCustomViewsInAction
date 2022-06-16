package com.example.chapter03.part1_valueanimator

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.chapter03.R

/**
 * @author wangzhichao
 * @date 7/15/20
 */
class C_ValueAnimatorVarArgsViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView

    init {
        inflate(context, R.layout.layout_value_animator_varargs_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
        tv.setOnClickListener {
            Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doAnimation() {
        val width = tv.width
        val height = tv.height
        val top = tv.top
        val left = tv.left
        ValueAnimator.ofFloat(0f, 400f, 50f, 200f).apply {
            duration = 2000L
            interpolator = LinearInterpolator()
            addUpdateListener { animator ->
                val fraction = animator.animatedFraction
                val currValue = animator.animatedValue as Float
                Log.d(TAG, "onAnimationUpdate: fraction=$fraction,currValue=$currValue")
                tv.translationX = currValue
                tv.translationY = 2 * currValue
                //                tv.layout(left + currValue, top + 2 * currValue, left + currValue + width, top + 2 * currValue + height);
                Log.d(TAG, "onAnimationUpdate: left=" + tv.left + ", top=" + tv.top)
            }
            start()
        }

    }

    companion object {
        private const val TAG = "C_ValueAnimatorVarArgsV"
    }
}