package com.example.chapter02.part2_view_animation_code;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * 视图动画的集合动画，使用代码来实现
 *
 * @author wangzhichao
 * @since 20-3-26
 */
public class ViewAnimationCodeSetViewGroup extends ConstraintLayout {

    public ViewAnimationCodeSetViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_code_set_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                <set xmlns:android="http://schemas.android.com/apk/res/android"
                    android:duration="3000"
                    android:fillAfter="true">
                    <alpha
                        android:fromAlpha="0.0"
                        android:toAlpha="1.0" />
                    <scale
                        android:fromXScale="0.0"
                        android:fromYScale="0.0"
                        android:pivotX="50%"
                        android:pivotY="50%"
                        android:toXScale="1.4"
                        android:toYScale="1.4" />
                    <rotate
                        android:fromDegrees="0"
                        android:pivotX="50%"
                        android:pivotY="50%"
                        android:toDegrees="-600" />
                </set>
                 */
                AnimationSet animationSet = new AnimationSet(true);
                AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f,
                        ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                        ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
                RotateAnimation rotateAnimation = new RotateAnimation(0f, -600f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                animationSet.addAnimation(alphaAnimation);
                animationSet.addAnimation(scaleAnimation);
                animationSet.addAnimation(rotateAnimation);
                animationSet.setDuration(3000L);
                animationSet.setFillAfter(true);
                tv.startAnimation(animationSet);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.setanim);
//                tv.startAnimation(animation);
            }
        });
        final TextView tvBounce1 = findViewById(R.id.tv_bounce1);
        findViewById(R.id.btn_start_bounce1).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.setanim_bounce1);
                tvBounce1.startAnimation(animation);
            }
        });
        final TextView tvBounce2 = findViewById(R.id.tv_bounce2);
        findViewById(R.id.btn_start_bounce2).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.setanim_bounce2);
                tvBounce2.startAnimation(animation);
            }
        });
    }

}
