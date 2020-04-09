package com.example.chapter02.part3_interpolator;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * @author wangzhichao
 * @since 20-4-9
 */
public class AccelerateDecelerateInterpolatorViewGroup extends ConstraintLayout {
    public AccelerateDecelerateInterpolatorViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.accelerate_decelerate_interpolator_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_translate).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.translateanim_delta_50_percent);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.setFillAfter(false);
                tv.startAnimation(animation);
            }
        });
        findViewById(R.id.btn_rotate).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotateanim_pivot_50_percent);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.setFillAfter(false);
                tv.startAnimation(animation);
            }
        });
        findViewById(R.id.btn_scale).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.scaleanim_pivot_50_percent);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.setFillAfter(false);
                tv.startAnimation(animation);
            }
        });
        findViewById(R.id.btn_alpha).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.alphaanim);
                animation.setInterpolator(new AccelerateDecelerateInterpolator());
                animation.setFillAfter(false);
                tv.startAnimation(animation);
            }
        });
    }
}
