package com.example.chapter13.part1

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent.actionToString
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

/**
 *
 * @author wangzhichao
 * @since 2022/8/8
 */

class F_OnDragListenerGridView(context: Context, attrs: AttributeSet?) :
    ViewGroup(context, attrs) {
    private var draggedView: View? = null
    private val myDragListener = MyDragListener()
    private val orderedChildren: MutableList<View> = ArrayList()
    override fun onFinishInflate() {
        super.onFinishInflate()
        for (child in children) {
            orderedChildren.add(child) // 初始化位置
            child.setOnLongClickListener { v ->
                draggedView = v
                // 让 View 跟着手指走
                // data：只有在手松开才能拿到的数据
                // localState：随时都可以拿到的数据
                v.startDrag(null, DragShadowBuilder(v), v, 0)
                false
            }
            // 监听拖拽，进行重排。
            // 每个子 View 被拖拽的过程中，其他子 View 都会收到拖拽的回调。
            child.setOnDragListener(myDragListener)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val childWidthSize = widthSize / COLUMNS
        val childHeightSize = heightSize / ROWS
        measureChildren(MeasureSpec.makeMeasureSpec(childWidthSize, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(childHeightSize, MeasureSpec.EXACTLY))
        setMeasuredDimension(widthSize, heightSize)
    }
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft: Int
        var childTop: Int
        val childWidthSize = width / COLUMNS
        val childHeightSize = height / ROWS
        for ((index, child) in children.withIndex()) {
            Log.d(TAG, "onLayout: child=$child")
            child.layout(0, 0, childWidthSize, childHeightSize)
            childLeft = index % 2 * childWidthSize
            childTop = index / 2 * childHeightSize
            child.translationX = childLeft.toFloat()
            child.translationY = childTop.toFloat()
        }
    }

    private inner class MyDragListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            Log.d(TAG, "onDrag: v=$v")
            when(event.action) {
                // 当拖拽开始时的事件
                DragEvent.ACTION_DRAG_STARTED -> if (event.localState === v) {
                    v.visibility = View.INVISIBLE
                }
                // 拖拽点已经进入了某个 View（包括自己） 的区域内
                DragEvent.ACTION_DRAG_ENTERED -> if (event.localState !== v) {
                    sort(v)
                }
                DragEvent.ACTION_DRAG_EXITED -> {

                }
                DragEvent.ACTION_DRAG_ENDED -> if (event.localState === v) {
                    v.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DROP -> {}
            }
            return true
        }

    }
    // TODO 理解这个算法
    private fun sort(targetView: View) {
        // 正在拖拽的子 View 的索引
        var draggedIndex = -1
        // 拖拽点进入区域的那个子 View 的索引
        var targetIndex = -1
        for ((index, child) in orderedChildren.withIndex()) {
            if (targetView === child) {
                targetIndex = index
            } else if (draggedView === child) {
                draggedIndex = index
            }
        }
        orderedChildren.removeAt(draggedIndex)
        orderedChildren.add(targetIndex, draggedView!!)
        var childLeft: Int
        var childTop: Int
        val childWidthSize = width / COLUMNS
        val childHeightSize = height / ROWS
        for ((index, child) in orderedChildren.withIndex()) {
            childLeft = index % 2 * childWidthSize
            childTop = index / 2 * childHeightSize
            child.animate()
                .translationX(childLeft.toFloat())
                .translationY(childTop.toFloat())
                .setDuration(200)
        }
    }

    // TODO 再理解一下这个方法
    override fun onDragEvent(event: DragEvent?): Boolean {
        return super.onDragEvent(event)
    }
    init {
        isChildrenDrawingOrderEnabled = true
    }
    companion object {
        private const val COLUMNS = 2
        private const val ROWS = 3
        private const val TAG = "OnDragListenerGridView"
    }
}