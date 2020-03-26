package com.example.chapter02.part2_view_animation_code;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * 视图动画的渐变动画，使用代码来实现
 *
 * @author wangzhichao
 * @since 20-3-26
 */
public class ViewAnimationCodeAlphaViewGroup extends ConstraintLayout {
    public ViewAnimationCodeAlphaViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_code_alpha_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                <alpha xmlns:android="http://schemas.android.com/apk/res/android"
                    android:duration="3000"
                    android:fillBefore="true"
                    android:fromAlpha="1.0"
                    android:toAlpha="0.0" />
                 */
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(3000L);
                alphaAnimation.setFillBefore(true);
                tv.startAnimation(alphaAnimation);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.alphaanim);
//                tv.startAnimation(animation);
            }
        });
    }
}
