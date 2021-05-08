package com.example.chapter03.part1_valueanimator;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter03.R;

/**
 * @author wangzhichao
 * @date 7/15/20
 */
public class C_ValueAnimatorVarArgsViewGroup extends LinearLayout {
    private static final String TAG = "C_ValueAnimatorVarArgsV";
    private final TextView tv;

    public C_ValueAnimatorVarArgsViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.c_value_animator_varargs_viewgroup, this);
        Button btnStartAnim = findViewById(R.id.btn_start_anim);
        tv = findViewById(R.id.tv);
        btnStartAnim.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimation();
            }
        });
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void doAnimation() {
        final int width = tv.getWidth();
        final int height = tv.getHeight();
        final int top = tv.getTop();
        final int left = tv.getLeft();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 400f, 50f, 200f);
        valueAnimator.setDuration(2000L);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                float currValue = (float) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: fraction=" + fraction + ",currValue=" + currValue);
                tv.setTranslationX(currValue);
                tv.setTranslationY(2 * currValue);
//                tv.layout(left + currValue, top + 2 * currValue, left + currValue + width, top + 2 * currValue + height);
                Log.d(TAG, "onAnimationUpdate: left=" + tv.getLeft() + ", top=" + tv.getTop());
            }
        });
        valueAnimator.start();
    }
}
