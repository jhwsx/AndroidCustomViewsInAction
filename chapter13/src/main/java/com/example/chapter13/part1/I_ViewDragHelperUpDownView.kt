package com.example.chapter13.part1

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.example.chapter13.R
import kotlin.math.abs

/**
 *
 * @author wangzhichao
 * @since 2022/8/11
 */
class I_ViewDragHelperUpDownView(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private lateinit var view: View
    private val viewConfiguration = ViewConfiguration.get(context)
    private val minVelocity = viewConfiguration.scaledMinimumFlingVelocity
    private val dragHelper = ViewDragHelper.create(this, DragCallback())
    override fun onFinishInflate() {
        super.onFinishInflate()
        view = findViewById(R.id.view)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    private inner class DragCallback : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return child === view
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            super.onViewReleased(releasedChild, xvel, yvel)
            if (abs(yvel) < minVelocity) {
                // 快速定位
                if (releasedChild.top + releasedChild.bottom < height) {
                    // 顶部
                    dragHelper.settleCapturedViewAt(0, 0)
                } else {
                    // 底部
                    dragHelper.settleCapturedViewAt(0, height - releasedChild.height)
                }
            } else {
                // 惯性定位
                if (yvel < 0) {
                    // 顶部
                    dragHelper.settleCapturedViewAt(0, 0)
                } else {
                    // 底部
                    dragHelper.settleCapturedViewAt(0, height - releasedChild.height)
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
}