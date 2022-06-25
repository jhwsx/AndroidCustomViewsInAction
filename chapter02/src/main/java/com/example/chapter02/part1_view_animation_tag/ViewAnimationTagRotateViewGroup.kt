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
 * rotate 标签动画文件放在 res/anim/ 下面
 *     android:fromDegrees="0"
 *     android:toDegrees="-650" // - 代表的是逆时针方向
 *
 * 在 rotate 中,
 * android:pivotY 和 android:pivotX 属性用于指定旋转中心点的坐标.
 * 而在 scale 中, 指定的是动画的开始位置.
 * @author wangzhichao
 * @since 20-3-25
 */
public class ViewAnimationTagRotateViewGroup extends ConstraintLayout {

    public ViewAnimationTagRotateViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_tag_rotate_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim);
                tv.startAnimation(animation);
            }
        });
        final TextView tvPivot50 = findViewById(R.id.tv_pivot_50);
        findViewById(R.id.btn_start_pivot_50).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim_pivot_50);
                tvPivot50.startAnimation(animation);
            }
        });
        final TextView tvPivot50Percent = findViewById(R.id.tv_pivot_50_percent);
        findViewById(R.id.btn_start_pivot_50_percent).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim_pivot_50_percent);
                tvPivot50Percent.startAnimation(animation);
            }
        });
        final TextView tvPivot50PercentP = findViewById(R.id.tv_pivot_50_percent_p);
        findViewById(R.id.btn_start_pivot_50_percent_p).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(v.getContext(), R.anim.rotateanim_pivot_50_percent_p);
                tvPivot50PercentP.startAnimation(animation);
            }
        });
    }

}
