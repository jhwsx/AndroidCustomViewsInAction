package com.example.chapter02.part1_view_animation_tag

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * alpha 标签
 * 没有中心的概念
 *
 *
 * android:fromAlpha="1.0" // 起始是完全不透明 opaque
 * android:toAlpha="0.0" // 结束是完全透明 transparent
 *
 * @author wangzhichao
 * @since 20-3-25
 */
class ViewAnimationTagAlphaViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.view_animation_tag_alpha_viewgroup, this)
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_start).setOnClickListener { v ->
            val animation = AnimationUtils.loadAnimation(v.context, R.anim.alphaanim)
            tv.startAnimation(animation)
        }
    }
}