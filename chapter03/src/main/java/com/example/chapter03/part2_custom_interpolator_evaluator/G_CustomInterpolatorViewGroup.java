package com.example.chapter03.part2_custom_interpolator_evaluator;

import android.animation.FloatEvaluator;
import android.animation.IntEvaluator;
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
 * 演示：自定义插值器
 *
 * @author wangzhichao
 * @date 7/15/20
 */
public class G_CustomInterpolatorViewGroup extends LinearLayout {
    private static final String TAG = "C_ValueAnimatorVarArgsV";
    private final TextView tv;

    public G_CustomInterpolatorViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.g_custom_interpolator_viewgroup, this);
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
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 400f);
        valueAnimator.setDuration(2000L);
        valueAnimator.setInterpolator(new MyInterpolator());
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
