package com.example.chapter02.part5_frame_animation;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.chapter02.R;

/**
 * 帧动画 xml 实现
 *
 * xml 资源放在 drawable 下面,
 * 根节点是 animation-list,
 * oneshot 属性为 false, 表示无限循环; 为 true, 表示就播放一次.
 * 播放需要 AnimationDrawable 类的支持
 *
 * @author wangzhichao
 * @date 7/14/20
 */
public class FrameAnimXmlViewGroup extends LinearLayout {
    private static final String TAG = "FrameAnimXmlViewGroup";
    public FrameAnimXmlViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.frame_anim_xml_viewgroup, this);
        // android:src 方式设置的, 要对应 getDrawable(), 否则取出的是 null
        ImageView iv1 = findViewById(R.id.iv_1);
        final AnimationDrawable animationDrawable = (AnimationDrawable) iv1.getDrawable();
        // android:background 方式设置的, 要对应 getBackground(),否则取出的是 null
        ImageView iv2 = findViewById(R.id.iv_2);
        final AnimationDrawable animationDrawable2 = (AnimationDrawable) iv2.getBackground();
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
        // AnimationDrawable 的一些 api
        Log.d(TAG, "FrameAnimXmlViewGroup: 第 0 帧的时长=" + animationDrawable.getDuration(0));
        Log.d(TAG, "FrameAnimXmlViewGroup: 第 0 帧的drawable=" + animationDrawable.getFrame(0));
        Log.d(TAG, "FrameAnimXmlViewGroup: 总共多少帧=" + animationDrawable.getNumberOfFrames());
        Log.d(TAG, "FrameAnimXmlViewGroup: isOneShot=" + animationDrawable.isOneShot());

    }
}
