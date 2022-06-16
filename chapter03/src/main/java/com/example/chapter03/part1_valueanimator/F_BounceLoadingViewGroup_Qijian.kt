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
public class F_BounceLoadingViewGroup_Qijian extends LinearLayout {

    public F_BounceLoadingViewGroup_Qijian(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.f_bounce_loading_viewgroup_qijian, this);
    }
}
