package com.example.chapter02.part4_examples;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.chapter02.R;

/**
 * 加载框效果
 *
 * @author wangzhichao
 * @since 20-4-9
 */
public class LoadingViewGroup extends ConstraintLayout {
    public LoadingViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.loading_viewgroup, this);
        ImageView iv = findViewById(R.id.iv);
        RotateAnimation animation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5F);
        animation.setRepeatCount(Animation.INFINITE);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(2000L);
        iv.startAnimation(animation);
    }
}
