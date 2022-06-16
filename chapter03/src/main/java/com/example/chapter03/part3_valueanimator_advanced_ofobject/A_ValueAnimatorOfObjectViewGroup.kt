package com.example.chapter03.part3_valueanimator_advanced_ofobject;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter03.R;
import com.example.chapter03.part2_custom_interpolator_evaluator.ReverseEvaluator;

/**
 * 演示：ValueAnimator 的 ofObject 方法，实现文本中的字母从 A 到 Z 变化。
 *
 * @author wangzhichao
 * @since 2021/5/11
 */
public class K_ValueAnimatorOfObjectViewGroup extends LinearLayout {
    private static final String TAG = "ValueAnimatorOfObject";
    private final TextView tv;

    public K_ValueAnimatorOfObjectViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.k_value_animator_of_object_viewgroup, this);
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
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new CharEvaluator(), 'A', 'Z');
        valueAnimator.setDuration(10000L);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                char currValue = (char) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: fraction=" + fraction + ",currValue=" + currValue);
                tv.setText(String.valueOf(currValue));
                Log.d(TAG, "onAnimationUpdate: left=" + tv.getLeft() + ", top=" + tv.getTop());
            }
        });
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.start();
    }
}
