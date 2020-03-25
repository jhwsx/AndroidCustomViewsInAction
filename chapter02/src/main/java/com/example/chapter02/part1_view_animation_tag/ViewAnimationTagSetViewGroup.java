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
 *
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
        });
    }

}
