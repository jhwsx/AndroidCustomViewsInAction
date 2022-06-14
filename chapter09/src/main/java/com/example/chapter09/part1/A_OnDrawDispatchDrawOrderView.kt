package com.example.chapter09.part1

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.chapter09.part1.A_OnDrawDispatchDrawOrderView
import com.example.chapter09.part1.A_OnDrawDispatchDrawOrderViewGroup

/**
 * 参考：
 * HenCoder Android 开发进阶：自定义 View 1-5 绘制顺序 https://rengwuxian.com/ui-1-5/
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