package com.example.chapter02.part2_view_animation_code

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * 视图动画的缩放动画，使用代码来实现
 *
 * @author wangzhichao
 * @since 20-3-26
 */
class ViewAnimationCodeScaleViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.view_animation_code_scale_viewgroup, this)
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_start).setOnClickListener {
            //                <scale xmlns:android="http://schemas.android.com/apk/res/android"
//                android:fillBefore="false"
//                android:fillEnabled="false"
//                android:duration="700"
//                android:fromXScale="0.0"
//                android:fromYScale="0.0"
//                android:toXScale="1.4"
//                android:toYScale="1.4" />
            val scaleAnimation = ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f)
            scaleAnimation.duration = 700L
            scaleAnimation.isFillEnabled = false
            scaleAnimation.fillBefore = false
            tv.startAnimation(scaleAnimation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim);
//                tv.startAnimation(animation);
        }
        val tvPivot50 = findViewById<TextView>(R.id.tv_pivot_50)
        findViewById<View>(R.id.btn_start_pivot_50).setOnClickListener {
            /*
                        <scale xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="700"
                            android:fromXScale="0.0"
                            android:fromYScale="0.0"
                            android:pivotX="50"
                            android:pivotY="50"
                            android:repeatCount="1"
                            android:repeatMode="reverse"
                            android:toXScale="1.4"
                            android:toYScale="1.4" />
                         */
            val scaleAnimation: Animation = ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f, 50f, 50f)
            scaleAnimation.duration = 700L
            scaleAnimation.repeatCount = 1
            scaleAnimation.repeatMode = Animation.REVERSE
            tvPivot50.startAnimation(scaleAnimation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim_pivot_50);
//                tvPivot50.startAnimation(animation);
        }
        val tvPivot50Percent = findViewById<TextView>(R.id.tv_pivot_50_percent)
        findViewById<View>(R.id.btn_start_pivot_50_percent).setOnClickListener {
            /*
                        <scale xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="700"
                            android:fromXScale="0.0"
                            android:fromYScale="0.0"
                            android:pivotX="50%"
                            android:pivotY="50%"
                            android:toXScale="1.4"
                            android:toYScale="1.4" />
                        */
            val scaleAnimation: Animation = ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f)
            scaleAnimation.duration = 700L
            tvPivot50Percent.startAnimation(scaleAnimation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim_pivot_50_percent);
//                tvPivot50Percent.startAnimation(animation);
        }
        val tvPivot50PercentP = findViewById<TextView>(R.id.tv_pivot_50_percent_p)
        findViewById<View>(R.id.btn_start_pivot_50_percent_p).setOnClickListener {
            /*
                        <scale xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="700"
                            android:fromXScale="0.0"
                            android:fromYScale="0.0"
                            android:pivotX="50%p"
                            android:pivotY="50%p"
                            android:toXScale="1.4"
                            android:toYScale="1.4" />
                         */
            val scaleAnimation: Animation = ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                ScaleAnimation.RELATIVE_TO_PARENT, 0.5f, ScaleAnimation.RELATIVE_TO_PARENT, 0.5f)
            scaleAnimation.duration = 700L
            tvPivot50PercentP.startAnimation(scaleAnimation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim_pivot_50_percent_p);
//                tvPivot50PercentP.startAnimation(animation);
        }
    }
}