package com.example.chapter13.part1

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.example.chapter13.R
import kotlin.math.abs

/**
 *
 * @author wangzhichao
 * @since 2022/8/12
 */
class J_DingdingBottomNavigationView(context: Context, attrs: AttributeSet?) :
    ViewGroup(context, attrs) {
    private val dragHelper = ViewDragHelper.create(this, DragCallback())
    private lateinit var dragLayout: LinearLayout
    private var dragTopMin = 0
    private var dragTopMax = 0
    private val viewConfiguration = ViewConfiguration.get(context)
    private val minFlingVelocity = viewConfiguration.scaledMinimumFlingVelocity

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        dragLayout = findViewById(R.id.ll)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        measureChild(getChildAt(0), widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val child = getChildAt(0)
        val childLeft = 0
        val childTop = height - child.measuredHeight / 3
        child.layout(childLeft, childTop, childLeft + child.measuredWidth, childTop + child.measuredHeight)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        dragTopMin = height - dragLayout.measuredHeight
        dragTopMax = height - dragLayout.measuredHeight / 3
    }

    private inner class DragCallback : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return child === dragLayout
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top.coerceAtLeast(dragTopMin).coerceAtMost(dragTopMax)
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            if (abs(yvel) < minFlingVelocity) {
                // 依据释放位置决定最终位置
                if ((releasedChild.top + releasedChild.bottom) / 2 > height - releasedChild.height / 2) {
                    collapse()
                } else {
                    expand()
                }
            } else {
                // 依据惯性滑动速度方向决定最终位置
                if (yvel > 0) {
                    collapse()
                } else {
                    expand()
                }
            }
            postInvalidateOnAnimation()
        }
    }

    override fun computeScroll() {
        super.computeScroll()
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    fun collapse() {
        dragHelper.settleCapturedViewAt(0, dragTopMax)
    }

    fun expand() {
        dragHelper.settleCapturedViewAt(0, dragTopMin)
    }
    companion object {
        private const val TAG = "DingdingBottomNavi"
    }
}