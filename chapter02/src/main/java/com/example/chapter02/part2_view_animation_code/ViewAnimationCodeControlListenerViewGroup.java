package com.example.chapter02.part2_view_animation_code;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.example.chapter02.R;

/**
 * 动画监听结束回调是不可靠的
 * 参考阿里巴巴 Android 开发手册
 * 在有强依赖 onAnimationEnd 回调的交互时,如动画播放完毕才能操作页
 * 面 , onAnimationEnd 可 能 会 因 各 种 异 常 没 被 回 调 ( 参 考 :
 * https://stackoverflow.com/questions/5474923/onanimationend-is-not-getting-called-onanimationstart-works-fine ), 建 议 加 上 超 时 保 护 或 通 过 postDelay 替 代
 * onAnimationEnd。
 *
 * @author wangzhichao
 * @since 20-3-26
 */
public class ViewAnimationCodeControlListenerViewGroup extends ConstraintLayout {
    private static final String TAG = "wzc";
    private ScaleAnimation scaleAnimation;

    public ViewAnimationCodeControlListenerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_animation_code_control_listener_viewgroup, this);
        final TextView tv = findViewById(R.id.tv);
        findViewById(R.id.btn_start).setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {
                if (scaleAnimation == null) {
                    scaleAnimation = new ScaleAnimation(0.0f, 1.4f, 0.0f, 1.4f);
                    scaleAnimation.setDuration(7000L);
                    scaleAnimation.setFillEnabled(false);
                    scaleAnimation.setFillBefore(false);
                    scaleAnimation.setFillAfter(true);
                    scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            Log.d(TAG, "onAnimationStart: ");
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            Log.d(TAG, "onAnimationEnd: ");
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            Log.d(TAG, "onAnimationRepeat: ");
                        }
                    });
                }
                tv.startAnimation(scaleAnimation);
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scaleAnimation != null) {
                    scaleAnimation.cancel();
                }
            }
        });
        findViewById(R.id.btn_reset).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scaleAnimation != null) {
                    scaleAnimation.reset();
                }
            }
        });
    }
}
