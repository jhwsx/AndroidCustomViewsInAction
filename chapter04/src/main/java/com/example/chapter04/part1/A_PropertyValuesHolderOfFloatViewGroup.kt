package com.example.chapter04.part1

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewPropertyAnimator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.chapter04.R

/**
 *
 * @author wangzhichao
 * @since 2022/6/17
 */
class A_PropertyValuesHolderOfFloatViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val tv: TextView

    init {

        inflate(context, R.layout.layout_propertyvaluesholder_offloat, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
    }

    private fun doAnimation() {
        val rotationHolder = PropertyValuesHolder.ofFloat("rotation", 60f, -60f, 40f, -40f, 20f, -20f, 0f)
        val alphaHolder = PropertyValuesHolder.ofFloat("alpha", 0.1f, 1f, 0.1f, 1f)
        // 可以同时动画
        val animator = ObjectAnimator.ofPropertyValuesHolder(tv, rotationHolder, alphaHolder)
        animator.duration = 3000L
        animator.start()
    }
}