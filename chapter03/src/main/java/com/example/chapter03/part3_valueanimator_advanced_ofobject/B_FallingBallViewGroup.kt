package com.example.chapter03.part3_valueanimator_advanced_ofobject;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chapter03.R;

/**
 * 抛物动画
 *
 * @author wangzhichao
 * @since 2021/5/11
 */
public class L_FallingBallViewGroup extends LinearLayout {

    private ValueAnimator valueAnimator;
    int left = 0;
    int top = 0;
    public L_FallingBallViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.l_falling_ball_viewgroup, this);
        Button btnStartAnim = findViewById(R.id.btn_start_anim);
        final ImageView ball = findViewById(R.id.iv);

        ball.post(new Runnable() {
            @Override
            public void run() {
                left = ball.getLeft();
                top = ball.getTop();
            }
        });
        btnStartAnim.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                valueAnimator = ValueAnimator.ofObject(new FallingBallEvaluator(), new Point(0, 0), new Point(500, 500));
                valueAnimator.setDuration(2000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Point currValue = (Point) animation.getAnimatedValue();
                        ball.layout(
                                left + currValue.x,
                                top + currValue.y,
                                left + ball.getWidth() + currValue.x,
                                top + ball.getHeight() + currValue.y);
                    }
                });
                valueAnimator.start();
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }
}
