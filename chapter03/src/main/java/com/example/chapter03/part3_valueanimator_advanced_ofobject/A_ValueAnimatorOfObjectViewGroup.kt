package com.example.chapter03.part3_valueanimator_advanced_ofobject

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.animation.AccelerateInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.chapter03.R


/**
 * 演示：ValueAnimator 的 ofObject 方法，实现文本中的字母从 A 到 Z 变化。
 *
 * @author wangzhichao
 * @since 2021/5/11
 */
class A_ValueAnimatorOfObjectViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView

    init {
        inflate(context, R.layout.layout_value_animator_of_object_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
        tv.setOnClickListener {
            Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doAnimation() {
        ValueAnimator.ofObject(CharEvaluator(), 'A', 'Z').apply {
            duration = 10000L
            addUpdateListener { animation ->
                val fraction = animation.animatedFraction
                val currValue = animation.animatedValue as Char
                Log.d(TAG,
                    "onAnimationUpdate: fraction=$fraction,currValue=$currValue")
                tv.text = currValue.toString()
                Log.d(TAG,
                    "onAnimationUpdate: left=" + tv.left + ", top=" + tv.top)
            }
            interpolator = AccelerateInterpolator()
            start()
        }

    }

    companion object {
        private const val TAG = "ValueAnimatorOfObject"
    }
}