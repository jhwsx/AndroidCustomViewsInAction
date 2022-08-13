package com.example.chapter13.part1

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.example.chapter13.R
import kotlin.math.abs

/**
 *
 * @author wangzhichao
 * @since 2022/8/12
 */
class J_DingdingBottomDrawer(context: Context, attrs: AttributeSet?) :
    ViewGroup(context, attrs) {
    private val dragHelper = ViewDragHelper.create(this, DragCallback())
    private lateinit var dragLayout: ConstraintLayout

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
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthSize, heightSize)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val contentView = getChildAt(0)
        val bottomView = getChildAt(1)
        contentView.layout(0, 0, contentView.measuredWidth,
            contentView.measuredHeight - bottomView.measuredHeight / 3)
        bottomView.layout(0, measuredHeight - bottomView.measuredHeight / 3,
            bottomView.measuredWidth, measuredHeight + bottomView.measuredHeight * 2 / 3)
    }

    private inner class DragCallback : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return child === dragLayout
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top.coerceAtLeast(height - dragLayout.height).coerceAtMost(height - dragLayout.height / 3)
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            if (abs(yvel) < dragHelper.minVelocity) {
                Log.d(TAG, "onViewReleased: releasedChild.top=${releasedChild.top}, releasedChild.bottom=${releasedChild.bottom}, height=$height")
                // 依据释放位置决定最终位置
                if (releasedChild.top > height - dragLayout.height / 2) {
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

        override fun getViewVerticalDragRange(child: View): Int {
            return dragHelper.touchSlop
        }
    }

    override fun computeScroll() {
        super.computeScroll()
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    fun collapse() {
        dragHelper.smoothSlideViewTo(dragLayout, 0, height - dragLayout.height / 3)
    }

    fun expand() {
        dragHelper.smoothSlideViewTo(dragLayout, 0, height - dragLayout.height)
    }
    companion object {
        private const val TAG = "DingdingBottomNavi"
    }
}