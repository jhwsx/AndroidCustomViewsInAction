package com.example.chapter04.part1

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.chapter04.R

/**
 * 关键帧
 * @author wangzhichao
 * @since 2022/6/22
 */
class C_PropertyValuesHolderOfKeyframeViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView

    init {
        inflate(context, R.layout.layout_propertyvaluesholder_ofkeyframe, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
    }

    private fun doAnimation() {
        // fraction 对应于插值器函数返回的值。
        // value 表示属性的当前动画数值。
        val frame0 = Keyframe.ofFloat(0f, 0f)
        val frame1 = Keyframe.ofFloat(0.1f, -200f)
        val frame2 = Keyframe.ofFloat(1f, 0f)
        val frameHolder = PropertyValuesHolder.ofKeyframe("rotation", frame0, frame1, frame2)
        val animator = ObjectAnimator.ofPropertyValuesHolder(tv, frameHolder)
        animator.duration = 3000L
        animator.start()
    }
}