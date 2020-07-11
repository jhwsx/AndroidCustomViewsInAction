package com.example.chapter02.part1_view_animation_tag;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * set 标签动画文件放在 res/anim/ 下面
 * 容器类标签, 用于定义动画集
 *
 * 利用 AnimationSet 播放时整个动画播放完毕才会判断是否 fillAfter 的特性
 *
 * duration, repeatMode, fillBefore, fillAfter:
 *      These properties, when set on an AnimationSet object, will be pushed down to all child animations.
 * repeatCount, fillEnabled:
 *      These properties are ignored for AnimationSet.
 * startOffset, shareInterpolator:
 *      These properties apply to the AnimationSet itself.
 * 语法:
 * <?xml version="1.0" encoding="utf-8"?>
 * <set xmlns:android="http://schemas.android.com/apk/res/android"
 *     android:interpolator="@[package:]anim/interpolator_resource"
 *     android:shareInterpolator=["true" | "false"] >
 *     <alpha
 *         android:fromAlpha="float"
 *         android:toAlpha="float" />
 *     <scale
 *         android:fromXScale="float"
 *         android:toXScale="float"
 *         android:fromYScale="float"
 *         android:toYScale="float"
 *         android:pivotX="float"
 *         android:pivotY="float" />
 *     <translate
 *         android:fromXDelta="float"
 *         android:toXDelta="float"
 *         android:fromYDelta="float"
 *         android:toYDelta="float" />
 *     <rotate
 *         android:fromDegrees="float"
 *         android:toDegrees="float"
 *         android:pivotX="float"
 *         android:pivotY="float" />
 *     <set>
 *         ...
 *     </set>
 * </set>
 * @author wangzhichao
 * @since 20-3-24
 */
public class ViewAnimationTagSetViewGroup extends ConstraintLayout {

    public ViewAnimationTagSetViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_tag_set_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.setanim);
                tv.startAnimation(animation);
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
        });final TextView tv3 = findViewById(R.id.tv_3);
        findViewById(R.id.btn_start_3).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.setanim_3);
                tv3.startAnimation(animation);
            }
        });
    }

}
