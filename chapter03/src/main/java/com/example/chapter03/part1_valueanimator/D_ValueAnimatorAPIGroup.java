package com.example.chapter03.part1_valueanimator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chapter03.R;

/**
 * 演示 ValueAnimator 常用的函数以及监听器
 * 注意：监听器是添加到集合里面的。
 * @author wangzhichao
 * @date 7/27/20
 */
public class D_ValueAnimatorAPIGroup extends LinearLayout {

    private static final String TAG = "D_ValueAnimatorAPIGroup";

    private ValueAnimator valueAnimator;
    private int top;
    private final TextView tv;
    private ValueAnimator createdValueAnimator;
    private ValueAnimator clonedValueAnimator;

    public D_ValueAnimatorAPIGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.d_value_animator_api_group, this);
        final RadioButton rbRestart = findViewById(R.id.rb_restart);
        tv = findViewById(R.id.tv);
        final EditText etRepeatCount = findViewById(R.id.et_repeat_count);
        Button btnStart = findViewById(R.id.btn_start);
        Button btnCancel = findViewById(R.id.btn_cancel);
        Button btnPause = findViewById(R.id.btn_pause);
        Button btnResume = findViewById(R.id.btn_resume);
        Button btnReverse = findViewById(R.id.btn_reverse);
        Button btnStartClone = findViewById(R.id.btn_start_clone);
        Button btnCancelClone = findViewById(R.id.btn_cancel_clone);
        post(new Runnable() {
            @Override
            public void run() {
                top = tv.getTop();
            }
        });
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueAnimator == null) {
                    valueAnimator = ValueAnimator.ofInt(0, 400);
                }
                valueAnimator.setDuration(2000L);
                int repeatCount = 0;
                try {
                    repeatCount = Integer.parseInt(etRepeatCount.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                valueAnimator.setRepeatCount(repeatCount);
                valueAnimator.setRepeatMode(rbRestart.isChecked() ? ValueAnimator.RESTART : ValueAnimator.REVERSE);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fraction = animation.getAnimatedFraction();
                        int currValue = (int) animation.getAnimatedValue();
//                        Log.d(TAG, "onAnimationUpdate: fraction="+fraction+",currValue=" + currValue);
                        tv.layout(tv.getLeft(), currValue + top, tv.getRight(),
                                currValue + top + tv.getHeight());
                    }
                });
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.d(TAG, "onAnimationStart: ");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.d(TAG, "onAnimationEnd: ");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Log.d(TAG, "onAnimationCancel: ");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        Log.d(TAG, "onAnimationRepeat: ");
                    }
                });
                valueAnimator.addPauseListener(new Animator.AnimatorPauseListener() {
                    @Override
                    public void onAnimationPause(Animator animation) {
                        Log.d(TAG, "onAnimationPause: ");
                    }

                    @Override
                    public void onAnimationResume(Animator animation) {
                        Log.d(TAG, "onAnimationResume: ");
                    }
                });
                valueAnimator.start();
            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAnim();
            }
        });
        btnPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueAnimator != null) {
                    valueAnimator.pause();
                }
            }
        });
        btnResume.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueAnimator != null) {
                    valueAnimator.resume();
                }
            }
        });
        btnReverse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valueAnimator == null) {
                    valueAnimator = ValueAnimator.ofInt(0, 400);
                }
                valueAnimator.setDuration(2000L);
                int repeatCount = 0;
                try {
                    repeatCount = Integer.parseInt(etRepeatCount.getText().toString());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                valueAnimator.setRepeatCount(repeatCount);
                valueAnimator.setRepeatMode(rbRestart.isChecked() ? ValueAnimator.RESTART : ValueAnimator.REVERSE);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fraction = animation.getAnimatedFraction();
                        int currValue = (int) animation.getAnimatedValue();
//                        Log.d(TAG, "onAnimationUpdate: fraction="+fraction+",currValue=" + currValue);
                        tv.layout(tv.getLeft(), currValue + top, tv.getRight(),
                                currValue + top + tv.getHeight());
                    }
                });
                valueAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        Log.d(TAG, "onAnimationStart: ");
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Log.d(TAG, "onAnimationEnd: ");
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        Log.d(TAG, "onAnimationCancel: ");
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        Log.d(TAG, "onAnimationRepeat: ");
                    }
                });
                valueAnimator.addPauseListener(new Animator.AnimatorPauseListener() {
                    @Override
                    public void onAnimationPause(Animator animation) {
                        Log.d(TAG, "onAnimationPause: ");
                    }

                    @Override
                    public void onAnimationResume(Animator animation) {
                        Log.d(TAG, "onAnimationResume: ");
                    }
                });
                valueAnimator.reverse();
            }
        });

        btnStartClone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                createdValueAnimator = create();
                clonedValueAnimator = createdValueAnimator.clone();
                clonedValueAnimator.start();
            }
        });
        btnCancelClone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 这种不可以停止动画
//                if (createdValueAnimator != null) {
//                    createdValueAnimator.removeAllUpdateListeners();
//                    createdValueAnimator.cancel();
//                }
                // 这种可以停止动画
                if (clonedValueAnimator != null) {
                    clonedValueAnimator.removeAllUpdateListeners();
                    clonedValueAnimator.cancel();
                }
            }
        });
    }


    private ValueAnimator create() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 400);
        valueAnimator.setDuration(2000L);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currValue = (int) animation.getAnimatedValue();
                tv.layout(tv.getLeft(), currValue + top, tv.getRight(),
                        currValue + top + tv.getHeight());
            }
        });
        return valueAnimator;
    }

    private void cancelAnim() {
        if (valueAnimator != null) {
            // 移除所有的 AnimatorUpdateListener
            valueAnimator.removeAllUpdateListeners();
            // 移除指定的 AnimatorUpdateListener
            // valueAnimator.removeUpdateListener(ValueAnimator.AnimatorUpdateListener listener);
            // 移除所有的 AnimatorListener 和 AnimatorPauseListener
            valueAnimator.removeAllListeners();
            // 移除指定的 AnimatorListener
            // valueAnimator.removeListener(Animator.AnimatorListener listener);
            // 移除指定的 AnimatorPauseListener
            // valueAnimator.removePauseListener(Animator.AnimatorPauseListener listener);
            tv.layout(tv.getLeft(), top, tv.getRight(), top + tv.getHeight());
            valueAnimator.cancel();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow: ");
        // 注意: 当不需要动画的时候,一定要移除动画,否则动画还在继续,从而导致 View 无法释放,进一步导致整个 Activity 无法释放,最终引起内存泄漏.
        cancelAnim();
    }
}
