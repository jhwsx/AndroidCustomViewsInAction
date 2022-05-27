package com.example.chapter02.part5_frame_animation;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.chapter02.R;

/**
 * 帧动画代码实现
 *
 * @author wangzhichao
 * @date 7/14/20
 */
public class FrameAnimCodeViewGroup extends LinearLayout {

    private static final String TAG = "FrameAnimCodeViewGroup";

    public FrameAnimCodeViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.frame_anim_code_viewgroup, this);
        // android:src 方式设置的, 要对应 getDrawable(), 否则取出的是 null
        ImageView iv1 = findViewById(R.id.iv_1);
        final AnimationDrawable animationDrawable = createAnimationDrawable();
        iv1.setImageDrawable(animationDrawable);
        // android:background 方式设置的, 要对应 getBackground(),否则取出的是 null
        ImageView iv2 = findViewById(R.id.iv_2);
        final AnimationDrawable animationDrawable2 = createAnimationDrawable();
        iv2.setBackground(animationDrawable2);
        final CheckBox cbOneShot = findViewById(R.id.cb_oneshot);
        findViewById(R.id.start).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.setOneShot(cbOneShot.isChecked());
                animationDrawable.start();
                animationDrawable2.setOneShot(cbOneShot.isChecked());
                animationDrawable2.start();
                Log.d(TAG, "onClick: isRunning=" + animationDrawable.isRunning());
            }
        });
        findViewById(R.id.stop).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.stop();
                animationDrawable2.stop();
            }
        });
    }

    private AnimationDrawable createAnimationDrawable() {
        AnimationDrawable animationDrawable = new AnimationDrawable();
        for (int i = 1; i <= 14; i++) {
            int drawableId = getResources().getIdentifier("list_icon_gif_playing" + i,
                    "drawable", getContext().getPackageName());
            Drawable frame = getResources().getDrawable(drawableId);
            animationDrawable.addFrame(frame, 60);
        }
        animationDrawable.setOneShot(false);
        return animationDrawable;
    }
}
