package com.example.chapter02.part1_view_animation_tag

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * set 标签动画文件放在 res/anim/ 下面
 * 容器类标签, 用于定义动画集
 *
 * 利用 AnimationSet 播放时整个动画播放完毕才会判断是否 fillAfter 的特性
 *
 * duration, repeatMode, fillBefore, fillAfter:
 * These properties, when set on an AnimationSet object, will be pushed down to all child animations.
 * repeatCount, fillEnabled:
 * These properties are ignored for AnimationSet.
 * startOffset, shareInterpolator:
 * These properties apply to the AnimationSet itself.
 * 语法:
 *
 * <set xmlns:android="http://schemas.android.com/apk/res/android" android:interpolator="@[package:]anim/interpolator_resource" android:shareInterpolator=["true" |></set> "false"] >
 * <alpha android:fromAlpha="float" android:toAlpha="float"></alpha>
 * <scale android:fromXScale="float" android:toXScale="float" android:fromYScale="float" android:toYScale="float" android:pivotX="float" android:pivotY="float"></scale>
 * <translate android:fromXDelta="float" android:toXDelta="float" android:fromYDelta="float" android:toYDelta="float"></translate>
 * <rotate android:fromDegrees="float" android:toDegrees="float" android:pivotX="float" android:pivotY="float"></rotate>
 * <set>
 * ...
</set> *
 *
 * @author wangzhichao
 * @since 20-3-24
 */
class ViewAnimationTagSetViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.view_animation_tag_set_viewgroup, this)
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_start).setOnClickListener { v ->
            val animation = AnimationUtils.loadAnimation(v.context, R.anim.setanim)
            tv.startAnimation(animation)
        }
        val tvBounce1 = findViewById<TextView>(R.id.tv_bounce1)
        findViewById<View>(R.id.btn_start_bounce1).setOnClickListener { v ->
            val animation = AnimationUtils.loadAnimation(v.context, R.anim.setanim_bounce1)
            tvBounce1.startAnimation(animation)
        }
        val tvBounce2 = findViewById<TextView>(R.id.tv_bounce2)
        findViewById<View>(R.id.btn_start_bounce2).setOnClickListener { v ->
            val animation = AnimationUtils.loadAnimation(v.context, R.anim.setanim_bounce2)
            tvBounce2.startAnimation(animation)
        }
        val tv3 = findViewById<TextView>(R.id.tv_3)
        findViewById<View>(R.id.btn_start_3).setOnClickListener { v ->
            val animation = AnimationUtils.loadAnimation(v.context, R.anim.setanim_3)
            tv3.startAnimation(animation)
        }
    }
}