package com.example.chapter02.part4_examples;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.chapter02.R;

/**
 * 镜头由远及近效果
 *
 * @author wangzhichao
 * @since 20-4-9
 */
public class CameraStretchViewGroup extends ConstraintLayout {
    public CameraStretchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.camera_stretch_viewgroup, this);
        final ImageView iv = findViewById(R.id.iv);
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation animation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5F);
                animation.setInterpolator(new BounceInterpolator());
                animation.setFillAfter(true);
                animation.setDuration(6000);
                iv.startAnimation(animation);
            }
        });
    }
}
