package com.example.chapter12

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import android.view.MotionEvent

/**
 * @author wangzhichao
 * @date 2019/09/19
 */
class MyViewPager : ViewPager {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    private var noScroll = false
    fun setNoScroll(noScroll: Boolean) {
        this.noScroll = noScroll
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return if (noScroll) {
            false
        } else {
            super.onInterceptTouchEvent(ev)
        }
    }
}