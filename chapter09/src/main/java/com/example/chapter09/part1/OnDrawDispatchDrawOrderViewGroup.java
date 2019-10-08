package com.example.chapter09.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.chapter09.R;

/**
 * @author wangzhichao
 * @date 2019/09/30
 */
public class OnDrawDispatchDrawOrderViewGroup extends LinearLayout {
    public static final String TAG = OnDrawDispatchDrawOrderViewGroup.class.getSimpleName();
    public OnDrawDispatchDrawOrderViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_ondrawdispatchdraworderviewgroup, this);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.d(TAG, "dispatchDraw");
        super.dispatchDraw(canvas);
    }
}

/**
 * onDraw 是绘制自身，dispatchDraw 是绘制子视图
 * onDraw 先调用，dispatchDraw 后调用
 * 在 ViewGroup 中，onDraw 不是每次都调用的，dispatchDraw 是每次都调用的
 * 在 View 中，onDraw，dispatchDraw 每次都会调用
 */
