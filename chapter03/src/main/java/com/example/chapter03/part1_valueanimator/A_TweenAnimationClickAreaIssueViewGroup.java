package com.example.chapter03.part1_valueanimator;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter03.R;

/**
 * 补间动画的单击区域问题
 * <p>
 * 补间动画虽然可以对控件做动画,但是并没有改变控件内部的属性值.
 * <p>
 * 为什么补间动画会有点击区域问题而属性动画就没有?
 * https://juejin.im/post/5d137218e51d4555fd20a36d
 * https://www.wanandroid.com/wenda/show/8644
 *
 * @author wangzhichao
 * @date 7/15/20
 */
public class A_TweenAnimationClickAreaIssueViewGroup extends LinearLayout {
    public A_TweenAnimationClickAreaIssueViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.a_tween_animation_click_area_issue_viewgroup, this);
        Button btnStartAnim = findViewById(R.id.btn_start_anim);
        final TextView tv = findViewById(R.id.tv);
        btnStartAnim.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TranslateAnimation translateAnimation = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 1f,
                        TranslateAnimation.RELATIVE_TO_SELF, 0F, TranslateAnimation.RELATIVE_TO_SELF, 2f);
                translateAnimation.setDuration(2000L);
                translateAnimation.setFillAfter(true);
                translateAnimation.setInterpolator(new LinearInterpolator());
                tv.startAnimation(translateAnimation);
            }
        });
        tv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
