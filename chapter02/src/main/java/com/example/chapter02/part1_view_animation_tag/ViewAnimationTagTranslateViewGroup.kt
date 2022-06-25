package com.example.chapter02.part1_view_animation_tag

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * translate 标签动画文件放在 res/anim/ 下面
 * android:fromXDelta="0" // 起始点 X 轴上的位置
 * android:fromYDelta="0" // 起始点 Y 轴上的位置
 * android:toXDelta="50" // 终点 X 轴上的位置
 * android:toYDelta="50" // 终点 Y 轴上的位置
 *
 * @author wangzhichao
 * @since 20-3-24
 */
class ViewAnimationTagTranslateViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.view_animation_tag_translate_viewgroup, this)
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_start).setOnClickListener { v ->
            val animation = AnimationUtils.loadAnimation(v.context, R.anim.translateanim_delta_50)
            tv.startAnimation(animation)
        }
        val tvDelta50Percent = findViewById<TextView>(R.id.tv_delta_50_percent)
        findViewById<View>(R.id.btn_delta_50_percent).setOnClickListener { v ->
            val animation =
                AnimationUtils.loadAnimation(v.context, R.anim.translateanim_delta_50_percent)
            tvDelta50Percent.startAnimation(animation)
        }
        val tvDelta50PercentP = findViewById<TextView>(R.id.tv_delta_50_percent_p)
        findViewById<View>(R.id.btn_start_delta_50_percent_p).setOnClickListener { v ->
            val animation =
                AnimationUtils.loadAnimation(v.context, R.anim.translateanim_delta_50_percent_p)
            tvDelta50PercentP.startAnimation(animation)
        }
    }
}