package com.example.chapter13.part1

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.MotionEvent.actionToString
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.customview.widget.ViewDragHelper
import androidx.customview.widget.ViewDragHelper.*
import com.example.common.dp

/**
 *
 * @author wangzhichao
 * @since 2022/8/11
 */
class G_ViewDragHelperGridView(context: Context, attrs: AttributeSet?) :
    ViewGroup(context, attrs) {

    private val dragHelper = ViewDragHelper.create(this, ViewDragHelperCallback())

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
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
            child.layout(childLeft, childTop, childLeft + childWidthSize, childTop + childHeightSize)
        }
    }

    private inner class ViewDragHelperCallback : ViewDragHelper.Callback() {
        var currentState = STATE_IDLE
        var capturedLeft = 0f
        var capturedTop = 0f
        var originalElevation = 0f
        var releasedChild: View? = null
        // 尝试抓住 View。如果返回 true，那么这个 child 就可以被拖动了。
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return currentState == STATE_IDLE
        }
        // 钳住，夹住 View 水平方向上的位置
        // left 是手指的偏移值
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            // 返回 left，表示没有钳制，手指的水平位置移动多少，child 的 水平位置就移动多少
            return left
        }
        // 钳住，夹住 View 竖直方向上的位置
        // top 是手指的偏移值
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }
        // 当 View 被抓住的时候回调，当 View 被拖起的时候回调
        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            super.onViewCaptured(capturedChild, activePointerId)
            originalElevation = capturedChild.elevation
            capturedChild.elevation = elevation + 1.dp
            // 记录被拖起 View 的初始位置
            capturedLeft = capturedChild.left.toFloat()
            capturedTop = capturedChild.top.toFloat()
        }
        // 当 View 的位置改变时回调，在这里可以做自动重排
        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {
            super.onViewPositionChanged(changedView, left, top, dx, dy)
        }
        // 当 View 被释放的时候调用
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            this.releasedChild = releasedChild
            dragHelper.settleCapturedViewAt(capturedLeft.toInt(), capturedTop.toInt())
            postInvalidateOnAnimation()
        }

        override fun onViewDragStateChanged(state: Int) {
            super.onViewDragStateChanged(state)
            currentState = state
            when (state) {
                STATE_IDLE -> {
                    releasedChild?.elevation = originalElevation
                }
                STATE_DRAGGING -> {}
                STATE_SETTLING -> {}
            }
        }
    }

    override fun computeScroll() {
        super.computeScroll()
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    companion object {
        private const val COLUMNS = 2
        private const val ROWS = 3
        private const val TAG = "ViewDragHelperGridView"
    }
}