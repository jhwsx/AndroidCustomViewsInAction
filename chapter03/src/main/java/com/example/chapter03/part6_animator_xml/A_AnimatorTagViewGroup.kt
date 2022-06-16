package com.example.chapter03.part6_animator_xml

import android.animation.AnimatorInflater
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
 *
 *
 * @author wangzhichao
 * @date 7/15/20
 */
class A_AnimatorTagViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView

    init {
        inflate(context, R.layout.layout_animator_tag, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
        tv.setOnClickListener {
            Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doAnimation() {
        val valueAnimator = AnimatorInflater.loadAnimator(context, R.animator.animator) as ValueAnimator
        valueAnimator.addUpdateListener { animator ->
                val fraction = animator.animatedFraction
                val currValue = animator.animatedValue as Int
                // fraction 会从0.0变化到1.0，currValue会从0变化到width。
                Log.d(TAG, "onAnimationUpdate: fraction=$fraction,currValue=$currValue")
                tv.translationX = currValue.toFloat()
                tv.translationY = 2f * currValue
            }
        valueAnimator.start()
    }

    companion object {
        private const val TAG = "A_AnimatorTagViewGroup"
    }
}