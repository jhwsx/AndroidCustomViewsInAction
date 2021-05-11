package com.example.chapter03.part2_custom_interpolator_evaluator;

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
 * 演示：自定义转换器
 *
 * 既可以通过重写插值器改变数值进度来改变数值位置，也可以通过改变 Evaluator 中数值进度所对
 * 应的具体数值来改变数值位置。
 *
 * @author wangzhichao
 * @date 7/15/20
 */
public class H_CustomEvaluatorViewGroup extends LinearLayout {
    private static final String TAG = "C_ValueAnimatorVarArgsV";
    private final TextView tv;

    public H_CustomEvaluatorViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.h_custom_evaluator_viewgroup, this);
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
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(2000L);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                int currValue = (int) animation.getAnimatedValue();
                Log.d(TAG, "onAnimationUpdate: fraction=" + fraction + ",currValue=" + currValue);
                tv.setTranslationX(currValue);
                tv.setTranslationY(2 * currValue);
//                tv.layout(left + currValue, top + 2 * currValue, left + currValue + width, top + 2 * currValue + height);
                Log.d(TAG, "onAnimationUpdate: left=" + tv.getLeft() + ", top=" + tv.getTop());
            }
        });
//        valueAnimator.setEvaluator(new IntEvaluator()); // ok
//        valueAnimator.setEvaluator(new FloatEvaluator()); // error: java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.Float
        valueAnimator.setEvaluator(new MyEvaluator());
        valueAnimator.start();
    }
}
