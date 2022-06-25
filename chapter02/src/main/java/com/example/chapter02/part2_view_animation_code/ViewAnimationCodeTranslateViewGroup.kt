package com.example.chapter02.part2_view_animation_code

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * 视图动画的偏移动画，使用代码来实现
 *
 * @author wangzhichao
 * @since 20-3-26
 */
class ViewAnimationCodeTranslateViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.view_animation_code_translate_viewgroup, this)
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_start).setOnClickListener {
            /*
                        <translate xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="3000"
                            android:fillAfter="true"
                            android:fromXDelta="0"
                            android:fromYDelta="0"
                            android:toXDelta="50"
                            android:toYDelta="50">
        
                        </translate>
                         */
            val animation: Animation = TranslateAnimation(0f, 50f, 0f, 50f)
            animation.duration = 3000L
            animation.fillAfter = true
            tv.startAnimation(animation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.translateanim_delta_50);
//                tv.startAnimation(animation);
        }
        val tvDelta50Percent = findViewById<TextView>(R.id.tv_delta_50_percent)
        findViewById<View>(R.id.btn_delta_50_percent).setOnClickListener {
            /*
                        <translate xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="3000"
                            android:fillAfter="true"
                            android:fromXDelta="0"
                            android:fromYDelta="0"
                            android:toXDelta="50%"
                            android:toYDelta="50%">
        
                        </translate>
                         */
            val animation: Animation = TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.5f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0.5f)
            animation.duration = 3000L
            animation.fillAfter = true
            tvDelta50Percent.startAnimation(animation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.translateanim_delta_50_percent);
//                tvDelta50Percent.startAnimation(animation);
        }
        val tvDelta50PercentP = findViewById<TextView>(R.id.tv_delta_50_percent_p)
        findViewById<View>(R.id.btn_start_delta_50_percent_p).setOnClickListener {
            /*
                        <translate xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="3000"
                            android:fillAfter="true"
                            android:fromXDelta="0"
                            android:fromYDelta="0"
                            android:toXDelta="50%p"
                            android:toYDelta="50%p">
        
                        </translate>
                         */
            val animation: Animation = TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.5f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.5f)
            animation.duration = 3000L
            animation.fillAfter = true
            tvDelta50PercentP.startAnimation(animation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.translateanim_delta_50_percent_p);
//                tvDelta50PercentP.startAnimation(animation);
        }
    }
}