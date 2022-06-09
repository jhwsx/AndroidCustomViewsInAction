package com.example.chapter09;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


/**
 * @author wangzhichao
 * @date 2019/09/19
 */
public class MyViewPager extends ViewPager {
    public MyViewPager(@NonNull Context context) {
        super(context);
    }

    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    private boolean noScroll;
    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (noScroll) {
            return false;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }
}
