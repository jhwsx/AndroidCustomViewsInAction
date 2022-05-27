package com.example.chapter02.part1_view_animation_tag;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * translate 标签动画文件放在 res/anim/ 下面
 * android:fromXDelta="0" // 起始点 X 轴上的位置
 * android:fromYDelta="0" // 起始点 Y 轴上的位置
 * android:toXDelta="50" // 终点 X 轴上的位置
 * android:toYDelta="50" // 终点 Y 轴上的位置
 *
 * @author wangzhichao
 * @since 20-3-24
 */
public class ViewAnimationTagTranslateViewGroup extends ConstraintLayout {

    public ViewAnimationTagTranslateViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_tag_translate_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.translateanim_delta_50);
                tv.startAnimation(animation);
            }
        });
        final TextView tvDelta50Percent = findViewById(R.id.tv_delta_50_percent);
        findViewById(R.id.btn_delta_50_percent).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.translateanim_delta_50_percent);
                tvDelta50Percent.startAnimation(animation);
            }
        });
        final TextView tvDelta50PercentP = findViewById(R.id.tv_delta_50_percent_p);
        findViewById(R.id.btn_start_delta_50_percent_p).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.translateanim_delta_50_percent_p);
                tvDelta50PercentP.startAnimation(animation);
            }
        });
    }

}
