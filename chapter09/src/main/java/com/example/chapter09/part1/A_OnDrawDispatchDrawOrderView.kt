package com.example.chapter09.part1

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.chapter09.part1.A_OnDrawDispatchDrawOrderView
import com.example.chapter09.part1.A_OnDrawDispatchDrawOrderViewGroup

/**
 * @author wangzhichao
 * @date 2019/09/30
 */
class A_OnDrawDispatchDrawOrderView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    override fun onDraw(canvas: Canvas) {
        Log.d(TAG, "onDraw")
        super.onDraw(canvas)
    }

    override fun dispatchDraw(canvas: Canvas) {
        Log.d(TAG, "dispatchDraw")
        super.dispatchDraw(canvas)
    }

    companion object {
        val TAG = A_OnDrawDispatchDrawOrderView::class.java.simpleName
    }
}