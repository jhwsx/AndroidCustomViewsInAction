package com.example.chapter02.part2_view_animation_code;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * 视图动画的偏移动画，使用代码来实现
 *
 * @author wangzhichao
 * @since 20-3-26
 */
public class ViewAnimationCodeTranslateViewGroup extends ConstraintLayout {

    public ViewAnimationCodeTranslateViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_code_translate_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Animation animation = new TranslateAnimation(0f, 50f, 0f, 50f);
                animation.setDuration(3000L);
                animation.setFillAfter(true);
                tv.startAnimation(animation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.translateanim_delta_50);
//                tv.startAnimation(animation);
            }
        });
        final TextView tvDelta50Percent = findViewById(R.id.tv_delta_50_percent);
        findViewById(R.id.btn_delta_50_percent).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Animation animation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f,
                        TranslateAnimation.RELATIVE_TO_SELF, 0.5f,
                        TranslateAnimation.RELATIVE_TO_SELF, 0f,
                        TranslateAnimation.RELATIVE_TO_SELF, 0.5f);
                animation.setDuration(3000L);
                animation.setFillAfter(true);
                tvDelta50Percent.startAnimation(animation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.translateanim_delta_50_percent);
//                tvDelta50Percent.startAnimation(animation);
            }
        });
        final TextView tvDelta50PercentP = findViewById(R.id.tv_delta_50_percent_p);
        findViewById(R.id.btn_start_delta_50_percent_p).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Animation animation = new TranslateAnimation(
                        TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                        TranslateAnimation.RELATIVE_TO_PARENT, 0.5f,
                        TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                        TranslateAnimation.RELATIVE_TO_PARENT, 0.5f);
                animation.setDuration(3000L);
                animation.setFillAfter(true);
                tvDelta50PercentP.startAnimation(animation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.translateanim_delta_50_percent_p);
//                tvDelta50PercentP.startAnimation(animation);
            }
        });
    }

}
