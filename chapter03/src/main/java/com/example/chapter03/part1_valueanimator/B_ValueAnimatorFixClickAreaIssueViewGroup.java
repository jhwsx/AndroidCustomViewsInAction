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
 * 使用 ValueAnimator 来解决 TweenAnimation 的点击区域问题
 * <p>
 * 注意: ValueAnimator 只对值进行动画计算, 而不是针对控件的.
 * 我们可以监听值的运算过程,然后根据这些值来对控件进行动画操作.
 *
 * 控件不仅显示在新的位置, 而且在新的位置上是可以响应点击事件的, 在原来的位置上不可以响应点击事件.
 *
 * @author wangzhichao
 * @date 7/15/20
 */
public class B_ValueAnimatorFixClickAreaIssueViewGroup extends LinearLayout {

    private static final String TAG = "ValueAnimatorFixClickAr";
    private final TextView tv;

    public B_ValueAnimatorFixClickAreaIssueViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.b_value_animator_fix_click_area_issue_viewgroup, this);
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
        Log.d(TAG, "doAnimation: width=" + width + ", height=" + height);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, width);
        valueAnimator.setDuration(2000L);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                int currValue = (int) animation.getAnimatedValue();
                // fraction 会从0.0变化到1.0，currValue会从0变化到width。
                Log.d(TAG, "onAnimationUpdate: fraction="+fraction+",currValue=" + currValue);
                // TODO: 2021/5/8 这两种方式有什么区别呢？
                // 方式一：
//                tv.setTranslationX(currValue);
//                tv.setTranslationY(2 * currValue);
                // 方式二：
                tv.layout(left + currValue, top + 2 * currValue, left + currValue + width, top + 2 * currValue + height);
                Log.d(TAG, "onAnimationUpdate: left=" + tv.getLeft() + ", top=" + tv.getTop());
            }
        });
        valueAnimator.start();
    }
}
