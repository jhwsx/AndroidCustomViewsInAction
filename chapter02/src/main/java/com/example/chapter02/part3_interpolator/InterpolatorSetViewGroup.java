package com.example.chapter02.part3_interpolator;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * 设置插值器
 * 通过 xml
 * 通过代码
 *
 * @author wangzhichao
 * @since 20-3-26
 */
public class InterpolatorSetViewGroup extends ConstraintLayout {
    public InterpolatorSetViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.interpolator_set_viewgroup, this);
        Button btnXml = findViewById(R.id.btn_xml);
        final TextView tvXml = findViewById(R.id.tv_xml);
        btnXml.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.alphaanim_interpolator);
                tvXml.startAnimation(animation);
            }
        });
        final TextView tvCode = findViewById(R.id.tv_code);
        findViewById(R.id.btn_code).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                <alpha xmlns:android="http://schemas.android.com/apk/res/android"
                    android:duration="3000"
                    android:fillBefore="true"
                    android:fromAlpha="1.0"
                    android:toAlpha="0.0"
                    android:interpolator="@android:anim/accelerate_interpolator"/>
                 */
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(3000L);
                alphaAnimation.setInterpolator(new AccelerateInterpolator());
//                alphaAnimation.setInterpolator(v.getContext(), android.R.anim.accelerate_interpolator);
                tvCode.startAnimation(alphaAnimation);
            }
        });
    }
}
