package com.example.chapter02.part2_view_animation_code;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * 视图动画的缩放动画，使用代码来实现
 *
 * @author wangzhichao
 * @since 20-3-26
 */
public class ViewAnimationCodeScaleViewGroup extends ConstraintLayout {

    public ViewAnimationCodeScaleViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_code_scale_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                <scale xmlns:android="http://schemas.android.com/apk/res/android"
//                android:fillBefore="false"
//                android:fillEnabled="false"
//                android:duration="700"
//                android:fromXScale="0.0"
//                android:fromYScale="0.0"
//                android:toXScale="1.4"
//                android:toYScale="1.4" />
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f);
                scaleAnimation.setDuration(700L);
                scaleAnimation.setFillEnabled(false);
                scaleAnimation.setFillBefore(false);
                tv.startAnimation(scaleAnimation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim);
//                tv.startAnimation(animation);
            }
        });
        final TextView tvPivot50 = findViewById(R.id.tv_pivot_50);
        findViewById(R.id.btn_start_pivot_50).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Animation scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f, 50f, 50f);
                scaleAnimation.setDuration(700L);
                scaleAnimation.setRepeatCount(1);
                scaleAnimation.setRepeatMode(Animation.REVERSE);
                tvPivot50.startAnimation(scaleAnimation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim_pivot_50);
//                tvPivot50.startAnimation(animation);
            }
        });
        final TextView tvPivot50Percent = findViewById(R.id.tv_pivot_50_percent);
        findViewById(R.id.btn_start_pivot_50_percent).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Animation scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                        ScaleAnimation.RELATIVE_TO_SELF,0.5F, ScaleAnimation.RELATIVE_TO_SELF, 0.5F);
                scaleAnimation.setDuration(700L);
                tvPivot50Percent.startAnimation(scaleAnimation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim_pivot_50_percent);
//                tvPivot50Percent.startAnimation(animation);
            }
        });
        final TextView tvPivot50PercentP = findViewById(R.id.tv_pivot_50_percent_p);
        findViewById(R.id.btn_start_pivot_50_percent_p).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Animation scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                        ScaleAnimation.RELATIVE_TO_PARENT,0.5F, ScaleAnimation.RELATIVE_TO_PARENT, 0.5F);
                scaleAnimation.setDuration(700L);
                tvPivot50PercentP.startAnimation(scaleAnimation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.scaleanim_pivot_50_percent_p);
//                tvPivot50PercentP.startAnimation(animation);
            }
        });
    }

}
