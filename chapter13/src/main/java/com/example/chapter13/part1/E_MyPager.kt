package com.example.chapter13.part1

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller
import androidx.core.view.children
import kotlin.math.abs

/**
 *
 * @author wangzhichao
 * @since 2022/8/7
 */
class E_MyPager(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private val velocityTracker = VelocityTracker.obtain()
    private val viewConfiguration = ViewConfiguration.get(context)
    private val overScroller = OverScroller(context)
    // 开始滑动页面的阈值
    private val pagingSlop = viewConfiguration.scaledPagingTouchSlop
    // 最小的快滑速度
    private val minVelocity = viewConfiguration.scaledMinimumFlingVelocity
    // 最大的快滑速度
    private val maxVelocity = viewConfiguration.scaledMaximumFlingVelocity
    private var downX = 0f
    private var downY = 0f
    private var isScrolling = false
    private var downScrollX = 0f
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)
        var intercepted = false
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                isScrolling = false
                // 存储初始状态，供自己的 onTouchEvent 使用
                downX = event.x
                downY = event.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_MOVE -> if (!isScrolling) {
                if (abs(event.x - downX) > pagingSlop) {
                    isScrolling = true
                    // 请求父 View 不要拦截
                    parent.requestDisallowInterceptTouchEvent(true)
                    // 拦截子 View
                    intercepted = true
                }
            }
        }
        return intercepted
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = (downX - event.x + downScrollX).toInt()
                    .coerceAtLeast(0)  // 限制右滑的边界
                    .coerceAtMost(width) // 限制左滑的边界
                scrollTo(dx, 0)
            }
            MotionEvent.ACTION_UP -> {
                // 处理松手停靠
                // 第二个参数表示速度的上限
                velocityTracker.computeCurrentVelocity(1000, maxVelocity.toFloat())
                val xVelocity = velocityTracker.xVelocity
                val targetPageIndex = if (abs(xVelocity) < minVelocity) {
                    // 松手时慢慢滑动
                    if (scrollX > width / 2) 1 else 0
                } else {
                    // 松手时快速滑动
                    if (xVelocity > 0) 0 else 1
                }
                val targetScrollX = targetPageIndex * width
//                scrollTo(targetScrollX, 0)
                val dx = targetScrollX - scrollX
                overScroller.startScroll(
                    scrollX, 0, // 从哪里开始
                    dx, 0 // 滑动多少距离
                )
                postInvalidateOnAnimation()
            }
        }
        return true
    }

    override fun computeScroll() {
        if (overScroller.computeScrollOffset()) {
            scrollTo(overScroller.currX, overScroller.currY)
            postInvalidateOnAnimation()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        val childTop = 0
        var childRight = width
        val childBottom = height
        for (child in children) {
            child.layout(childLeft, childTop, childRight, childBottom)
            childLeft += width
            childRight += width
        }
    }
}