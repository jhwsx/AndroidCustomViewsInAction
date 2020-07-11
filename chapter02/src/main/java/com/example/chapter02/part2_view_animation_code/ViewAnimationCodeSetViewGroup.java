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
import android.view.animation.TranslateAnimation;
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
                /*
                <?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <!--效果: 控件弹跳三次, 一次比一次跳得低的效果.-->
    <!--先向上偏移控件高度的 40%，时间是 350 毫秒-->
    <translate
        android:duration="350"
        android:fillBefore="false"
        android:fillEnabled="true"
        android:fromYDelta="0%"
        android:toYDelta="-40%" />
    <!--350 毫秒后，向下偏移控件高度的 40%，时间是 200 毫秒-->
    <translate
        android:duration="200"
        android:fillBefore="false"
        android:fillEnabled="true"
        android:fromYDelta="-40%"
        android:startOffset="350"
        android:toYDelta="0%" />
    <!--550 毫秒后，向上偏移控件高度的 25%，时间是 300 毫秒-->
    <translate
        android:duration="300"
        android:fillBefore="false"
        android:fillEnabled="true"
        android:fromYDelta="0%"
        android:startOffset="550"
        android:toYDelta="-25%" />
    <!--850 毫秒后，向下偏移控件高度的 25%，时间是 200 毫秒-->
    <translate
        android:duration="200"
        android:fillBefore="false"
        android:fillEnabled="true"
        android:fromYDelta="-25%"
        android:startOffset="850"
        android:toYDelta="0%" />
    <!--1050 毫秒后，向上偏移控件高度的 8%，时间是 200 毫秒-->
    <translate
        android:duration="200"
        android:fillBefore="false"
        android:fillEnabled="true"
        android:fromYDelta="0%"
        android:startOffset="1050"
        android:toYDelta="-8%" />
    <!--1250 毫秒后，向下偏移控件高度的 8%，时间是 150 毫秒-->
    <translate
        android:duration="150"
        android:fillBefore="false"
        android:fillEnabled="true"
        android:fromYDelta="-8%"
        android:startOffset="1250"
        android:toYDelta="0%" />
</set>
                */
                AnimationSet animationSet = new AnimationSet(true);
                TranslateAnimation translateAnimation1 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, -0.4f);
                translateAnimation1.setFillEnabled(true);
                translateAnimation1.setFillBefore(false);
                translateAnimation1.setDuration(350L);
                TranslateAnimation translateAnimation2 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, -0.4f,
                        TranslateAnimation.RELATIVE_TO_SELF,0 );
                translateAnimation2.setFillEnabled(true);
                translateAnimation2.setFillBefore(false);
                translateAnimation2.setStartOffset(350L);
                translateAnimation2.setDuration(200L);
                TranslateAnimation translateAnimation3 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF,-0.25F );
                translateAnimation3.setFillEnabled(true);
                translateAnimation3.setFillBefore(false);
                translateAnimation3.setStartOffset(550L);
                translateAnimation3.setDuration(300L);
                TranslateAnimation translateAnimation4 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, -0.25F,
                        TranslateAnimation.RELATIVE_TO_SELF, 0);
                translateAnimation4.setFillEnabled(true);
                translateAnimation4.setFillBefore(false);
                translateAnimation4.setStartOffset(850L);
                translateAnimation4.setDuration(200L);
                TranslateAnimation translateAnimation5 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, -0.08F);
                translateAnimation5.setFillEnabled(true);
                translateAnimation5.setFillBefore(false);
                translateAnimation5.setStartOffset(1050L);
                translateAnimation5.setDuration(200L);
                TranslateAnimation translateAnimation6 = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, 0,
                        TranslateAnimation.RELATIVE_TO_SELF, -0.08F,
                        TranslateAnimation.RELATIVE_TO_SELF, 0);
                translateAnimation6.setFillEnabled(true);
                translateAnimation6.setFillBefore(false);
                translateAnimation6.setStartOffset(1250L);
                translateAnimation6.setDuration(150L);
                animationSet.addAnimation(translateAnimation1);
                animationSet.addAnimation(translateAnimation2);
                animationSet.addAnimation(translateAnimation3);
                animationSet.addAnimation(translateAnimation4);
                animationSet.addAnimation(translateAnimation5);
                animationSet.addAnimation(translateAnimation6);
                tvBounce2.startAnimation(animationSet);
//                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.setanim_bounce2);
//                tvBounce2.startAnimation(animation);
            }
        });
    }

}
