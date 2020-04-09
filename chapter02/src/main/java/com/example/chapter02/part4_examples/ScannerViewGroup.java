package com.example.chapter02.part4_examples;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * @author wangzhichao
 * @since 20-4-9
 */
public class ScannerViewGroup extends FrameLayout {
    public ScannerViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.scanner_viewgroup, this);
        final Animation animation1 = AnimationUtils.loadAnimation(context, R.anim.scale_alpha_anim);
        final Animation animation2 = AnimationUtils.loadAnimation(context, R.anim.scale_alpha_anim);
        final Animation animation3 = AnimationUtils.loadAnimation(context, R.anim.scale_alpha_anim);
        final Animation animation4 = AnimationUtils.loadAnimation(context, R.anim.scale_alpha_anim);
        final ImageView circle1 = findViewById(R.id.circle1);
        final ImageView circle2 = findViewById(R.id.circle2);
        final ImageView circle3 = findViewById(R.id.circle3);
        final ImageView circle4 = findViewById(R.id.circle4);
        TextView tvStartScan = findViewById(R.id.start_scan);
        tvStartScan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                circle1.startAnimation(animation1);

                animation2.setStartOffset(600);
                circle2.startAnimation(animation2);

                animation3.setStartOffset(1200);
                circle3.startAnimation(animation3);


                animation4.setStartOffset(1800);
                circle4.startAnimation(animation4);
            }
        });
    }
}
