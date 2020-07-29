package com.example.chapter03.part1_valueanimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chapter03.R;

/**
 * @author wangzhichao
 * @date 7/29/20
 */
public class E_BounceLoadingViewGroup_My extends LinearLayout {
    private static final String TAG = "E_BounceLoadingViewGrou";
    private ValueAnimator valueAnimator;
    private int count = 0;

    public E_BounceLoadingViewGroup_My(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.e_bounce_loading_viewgroup_my, this);
        final ImageView ivAnimal = findViewById(R.id.iv_animal);
        ivAnimal.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (valueAnimator == null) {
                    valueAnimator = ValueAnimator.ofInt(0, -100, 0);
                }
                ivAnimal.setImageResource(R.drawable.pic_1);
                valueAnimator.removeAllUpdateListeners();
                valueAnimator.removeAllListeners();
                valueAnimator.setDuration(1000L);
                valueAnimator.setRepeatMode(ValueAnimator.RESTART);
                valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int currentValue = (int) animation.getAnimatedValue();
                        ivAnimal.setTranslationY(currentValue);
                    }
                });
                valueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        super.onAnimationRepeat(animation);
                        Log.d(TAG, "onAnimationRepeat: ");
                        count++;
                        switch (count % 3) {
                            case 0:
                                ivAnimal.setImageResource(R.drawable.pic_1);
                                break;
                            case 1:
                                ivAnimal.setImageResource(R.drawable.pic_2);
                                break;
                            case 2:
                                ivAnimal.setImageResource(R.drawable.pic_3);
                                break;
                        }
                    }
                });
                valueAnimator.start();
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (valueAnimator != null) {
            valueAnimator.removeAllListeners();
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.cancel();
        }
    }
}
