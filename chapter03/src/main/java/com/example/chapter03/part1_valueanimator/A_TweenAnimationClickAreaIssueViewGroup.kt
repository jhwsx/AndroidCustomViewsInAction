package com.example.chapter03.part1_valueanimator

import android.content.Context
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.chapter03.R

/**
 * 补间动画的单击区域问题
 *
 *
 * 补间动画虽然可以对控件做动画,但是并没有改变控件内部的属性值.
 *
 *
 * 为什么补间动画会有点击区域问题而属性动画就没有?
 * https://juejin.im/post/5d137218e51d4555fd20a36d
 * https://www.wanandroid.com/wenda/show/8644
 *
 * @author wangzhichao
 * @date 7/15/20
 */
class A_TweenAnimationClickAreaIssueViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_tween_animation_click_area_issue_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        val tv = findViewById<TextView>(R.id.tv)
        btnStartAnim.setOnClickListener {
            val translateAnimation = TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF,
                0f,
                TranslateAnimation.RELATIVE_TO_SELF,
                1f,
                TranslateAnimation.RELATIVE_TO_SELF,
                0f,
                TranslateAnimation.RELATIVE_TO_SELF,
                2f)
            translateAnimation.duration = 2000L
            translateAnimation.fillAfter = true
            translateAnimation.interpolator = LinearInterpolator()
            tv.startAnimation(translateAnimation)
        }
        tv.setOnClickListener {
            Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show()
        }
    }
}