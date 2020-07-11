package com.example.chapter02.part1_view_animation_tag;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * alpha 标签
 * 没有中心的概念
 * <p>
 * android:fromAlpha="1.0" // 起始是完全不透明 opaque
 * android:toAlpha="0.0" // 结束是完全透明 transparent
 *
 * @author wangzhichao
 * @since 20-3-25
 */
public class ViewAnimationTagAlphaViewGroup extends ConstraintLayout {
    public ViewAnimationTagAlphaViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_tag_alpha_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.alphaanim);
                tv.startAnimation(animation);
            }
        });
    }
}
