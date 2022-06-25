package com.example.chapter02.part2_view_animation_code

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * 视图动画的旋转动画，使用代码来实现
 *
 * @author wangzhichao
 * @since 20-3-26
 */
class ViewAnimationCodeRotateViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.view_animation_tag_rotate_viewgroup, this)
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_start).setOnClickListener {
            /*
                        <rotate xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="3000"
                            android:fillAfter="true"
                            android:fromDegrees="0"
                            android:toDegrees="-650">
        
                        </rotate>
                         */
            val animation: Animation = RotateAnimation(0f, -650f)
            animation.duration = 3000L
            animation.fillAfter = true
            tv.startAnimation(animation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim);
//                tv.startAnimation(animation);
        }
        val tvPivot50 = findViewById<TextView>(R.id.tv_pivot_50)
        findViewById<View>(R.id.btn_start_pivot_50).setOnClickListener {
            /*
                        <rotate xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="3000"
                            android:fillAfter="true"
                            android:fromDegrees="0"
                            android:toDegrees="-650"
                            android:pivotY="50"
                            android:pivotX="50">
        
                        </rotate>
                         */
            val animation: Animation = RotateAnimation(0f, -650f, 50f, 50f)
            animation.duration = 3000L
            animation.fillAfter = true
            tvPivot50.startAnimation(animation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim_pivot_50);
//                tvPivot50.startAnimation(animation);
        }
        val tvPivot50Percent = findViewById<TextView>(R.id.tv_pivot_50_percent)
        findViewById<View>(R.id.btn_start_pivot_50_percent).setOnClickListener {
            /*
                        <rotate xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="3000"
                            android:fillAfter="true"
                            android:fromDegrees="0"
                            android:toDegrees="-650"
                            android:pivotY="50%"
                            android:pivotX="50%">
        
                        </rotate>
                         */
            val animation: Animation = RotateAnimation(0f, -650f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f)
            animation.duration = 3000L
            animation.fillAfter = true
            tvPivot50Percent.startAnimation(animation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim_pivot_50_percent);
//                tvPivot50Percent.startAnimation(animation);
        }
        val tvPivot50PercentP = findViewById<TextView>(R.id.tv_pivot_50_percent_p)
        findViewById<View>(R.id.btn_start_pivot_50_percent_p).setOnClickListener {
            /*
                        <rotate xmlns:android="http://schemas.android.com/apk/res/android"
                            android:duration="3000"
                            android:fillAfter="true"
                            android:fromDegrees="0"
                            android:toDegrees="-650"
                            android:pivotY="50%p"
                            android:pivotX="50%p">
        
                        </rotate>
                         */
            val animation: Animation = RotateAnimation(0f, -650f,
                RotateAnimation.RELATIVE_TO_PARENT, 0.5f, RotateAnimation.RELATIVE_TO_PARENT, 0.5f)
            animation.duration = 3000L
            animation.fillAfter = true
            tvPivot50PercentP.startAnimation(animation)
            //                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim_pivot_50_percent_p);
//                tvPivot50PercentP.startAnimation(animation);
        }
    }
}