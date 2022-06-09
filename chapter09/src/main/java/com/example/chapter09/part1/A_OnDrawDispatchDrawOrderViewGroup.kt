package com.example.chapter09.part1

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.example.chapter09.R
import com.example.chapter09.part1.A_OnDrawDispatchDrawOrderView
import com.example.chapter09.part1.A_OnDrawDispatchDrawOrderViewGroup

/**
 * onDraw 是绘制自身，dispatchDraw 是绘制子视图
 * onDraw 先调用，dispatchDraw 后调用
 * 在 ViewGroup 中，onDraw 不是每次都调用的（当它有背景的时候才会调用 onDraw），dispatchDraw 是每次都调用的
 * 在 View 中，onDraw，dispatchDraw 每次都会调用
 *
 * @author wangzhichao
 * @date 2019/09/30
 */
class A_OnDrawDispatchDrawOrderViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    var onDrawCalled = false

    init {
        inflate(context, R.layout.layout_ondrawdispatchdraworderviewgroup, this)
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas) {
        Log.d(TAG, "onDraw")
        super.onDraw(canvas)
        onDrawCalled = true
    }

    override fun dispatchDraw(canvas: Canvas) {
        Log.d(TAG, "dispatchDraw")
        super.dispatchDraw(canvas)
    }

    companion object {
        val TAG = A_OnDrawDispatchDrawOrderViewGroup::class.java.simpleName
    }

}