package com.example.chapter03.part6_animator_xml

import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
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
class B_ObjectAnimatorTagViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView

    init {
        inflate(context, R.layout.layout_object_animator_tag, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
        tv.setOnClickListener {
            Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doAnimation() {
        val objectAnimator = AnimatorInflater.loadAnimator(context, R.animator.object_animator) as ObjectAnimator
        objectAnimator.target = tv
        objectAnimator.start()
    }

    companion object {
        private const val TAG = "B_ObjectAnimatorTagViewGroup"
    }
}