package com.example.chapter03.part1_valueanimator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.example.chapter03.R;

/**
 * @author wangzhichao
 * @date 7/29/20
 */
public class LoadingImageView extends android.support.v7.widget.AppCompatImageView {
    private ValueAnimator valueAnimator;
    private static final String TAG = "LoadingImageView";
    /**
     * 图片的总数目
     */
    private int imageCount = 3;
    /**
     * 计数器
     */
    private int count = 0;

    private int top;

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        if (valueAnimator == null) {
            valueAnimator = ValueAnimator.ofInt(0, -100, 0);
        }
        valueAnimator.removeAllUpdateListeners();
        valueAnimator.removeAllListeners();
        valueAnimator.setDuration(1000L);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (int) animation.getAnimatedValue();
                setTop(top + currentValue);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                Log.d(TAG, "onAnimationRepeat: ");
                count++;
                switch (count % imageCount) {
                    case 0:
                        setImageResource(R.drawable.pic_1);
                        break;
                    case 1:
                        setImageResource(R.drawable.pic_2);
                        break;
                    case 2:
                        setImageResource(R.drawable.pic_3);
                        break;
                }
            }
        });
        valueAnimator.start();
    }
    // 每次控件被布局时都会调用这个方法
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d(TAG, "onLayout: ");
        this.top = top;
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
