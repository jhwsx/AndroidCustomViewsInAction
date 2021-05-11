package com.example.chapter03.part2_custom_interpolator_evaluator;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter03.R;

/**
 * 演示：Argb转换器
 *
 * @author wangzhichao
 * @date 7/15/20
 */
public class J_ArgbEvaluatorViewGroup extends LinearLayout {
    private static final String TAG = "J_ArgbEvaluator";
    private final TextView tv;

    public J_ArgbEvaluatorViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.j_argb_evaluator_viewgroup, this);
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

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0xff00ff00, 0xff0000ff);
        valueAnimator.setDuration(3000L);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                int currValue = (int) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: fraction=" + fraction + ",currValue=" + currValue);
                tv.setBackgroundColor(currValue);
            }
        });
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.start();
    }
}
