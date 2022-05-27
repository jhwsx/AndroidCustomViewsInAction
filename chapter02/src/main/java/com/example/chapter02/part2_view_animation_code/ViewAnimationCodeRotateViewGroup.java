package com.example.chapter02.part2_view_animation_code;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * 视图动画的旋转动画，使用代码来实现
 *
 * @author wangzhichao
 * @since 20-3-26
 */
public class ViewAnimationCodeRotateViewGroup extends ConstraintLayout {

    public ViewAnimationCodeRotateViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_tag_rotate_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                <rotate xmlns:android="http://schemas.android.com/apk/res/android"
                    android:duration="3000"
                    android:fillAfter="true"
                    android:fromDegrees="0"
                    android:toDegrees="-650">

                </rotate>
                 */
                Animation animation = new RotateAnimation(0f, -650f);
                animation.setDuration(3000L);
                animation.setFillAfter(true);
                tv.startAnimation(animation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim);
//                tv.startAnimation(animation);
            }
        });
        final TextView tvPivot50 = findViewById(R.id.tv_pivot_50);
        findViewById(R.id.btn_start_pivot_50).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Animation animation = new RotateAnimation(0f, -650f, 50f, 50f);
                animation.setDuration(3000L);
                animation.setFillAfter(true);
                tvPivot50.startAnimation(animation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim_pivot_50);
//                tvPivot50.startAnimation(animation);
            }
        });
        final TextView tvPivot50Percent = findViewById(R.id.tv_pivot_50_percent);
        findViewById(R.id.btn_start_pivot_50_percent).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Animation animation = new RotateAnimation(0f, -650f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5F, RotateAnimation.RELATIVE_TO_SELF, 0.5F);
                animation.setDuration(3000L);
                animation.setFillAfter(true);
                tvPivot50Percent.startAnimation(animation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim_pivot_50_percent);
//                tvPivot50Percent.startAnimation(animation);
            }
        });
        final TextView tvPivot50PercentP = findViewById(R.id.tv_pivot_50_percent_p);
        findViewById(R.id.btn_start_pivot_50_percent_p).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Animation animation = new RotateAnimation(0f, -650f,
                        RotateAnimation.RELATIVE_TO_PARENT, 0.5F, RotateAnimation.RELATIVE_TO_PARENT, 0.5F);
                animation.setDuration(3000L);
                animation.setFillAfter(true);
                tvPivot50PercentP.startAnimation(animation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim_pivot_50_percent_p);
//                tvPivot50PercentP.startAnimation(animation);
            }
        });
    }

}
