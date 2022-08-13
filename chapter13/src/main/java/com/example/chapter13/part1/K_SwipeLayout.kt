package com.example.chapter13.part1

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.example.chapter13.R
import kotlin.math.abs

/**
 * https://www.cnblogs.com/shu94/p/12774125.html
 * @author wangzhichao
 * @since 2022/8/13
 */
class K_SwipeLayout(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val dragHelper = ViewDragHelper.create(this, DragCallback())
    private var downX: Float = 0f
    private var trackingPointerId = 0
    override fun onFinishInflate() {
        super.onFinishInflate()
        findViewById<View>(R.id.surface).setOnClickListener {
            Toast.makeText(context, "click surface", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.top).setOnClickListener {
            Toast.makeText(context, "click top", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.delete).setOnClickListener {
            Toast.makeText(context, "click delete", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        var intercepted = dragHelper.shouldInterceptTouchEvent(ev)
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                trackingPointerId = ev.getPointerId(0)
                downX = ev.x
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                val actionIndex = ev.actionIndex
                trackingPointerId = ev.getPointerId(actionIndex)
                downX = ev.getX(actionIndex)
            }
            MotionEvent.ACTION_MOVE -> {
                val pointerIndex = ev.findPointerIndex(trackingPointerId)
                val dx = ev.getX(pointerIndex) - downX
                if (abs(dx) > dragHelper.touchSlop) {
                    intercepted = true
                }
            }
        }
        return intercepted
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    private fun getSurfaceView() = getChildAt(0)

    private fun getBottomView() = getChildAt(1)

    private enum class State {
        CLOSE, OPEN
    }

    private inner class DragCallback : ViewDragHelper.Callback() {
        private lateinit var state: State
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            state = if (getSurfaceView().left == 0) {
                State.CLOSE
            } else {
                State.OPEN
            }
            return true
        }
        // left 是被拖拽的 child 的左边界将要在父 View 中的位置了。
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            Log.d(TAG, "clampViewPositionHorizontal: child=$child, left=$left, dx=$dx")
            val newLeft: Int = if (child === getSurfaceView()) {
                // 拖动的是 surface
                left.coerceAtMost(0).coerceAtLeast(-getBottomView().measuredWidth)
            } else {
                // 拖动的是 bottom
                left.coerceAtLeast(getSurfaceView().measuredWidth - getBottomView().measuredWidth)
            }
            return newLeft
        }

        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {
            if (changedView === getSurfaceView()) {
                // 正在拖拽的是 surface，让 bottom 跟随移动
                ViewCompat.offsetLeftAndRight(getBottomView(), dx)
            } else {
                // 正在拖拽的是 bottom，让 surface 跟随移动
                ViewCompat.offsetLeftAndRight(getSurfaceView(), dx)
            }
            invalidate()
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            val minVelocity = dragHelper.minVelocity
            if (abs(xvel) > minVelocity) {
                // 根据速度方向实现惯性定位
                if (xvel > 0) {
                    close()
                } else {
                    open()
                }
            } else {
                // 根据松手位置实现定位
                val fraction = if (state === State.CLOSE) 0.25f else 0.75f
                if (abs(getSurfaceView().left) > getBottomView().measuredWidth * fraction) {
                    open()
                } else {
                    close()
                }
            }
            postInvalidateOnAnimation()
        }

        override fun getViewHorizontalDragRange(child: View): Int {
            return dragHelper.touchSlop
        }
    }

    override fun computeScroll() {
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    private fun close() {
        dragHelper.smoothSlideViewTo(getSurfaceView(), 0, 0)
//        dragHelper.settleCapturedViewAt(0, 0)
    }

    private fun open() {
        dragHelper.smoothSlideViewTo(getSurfaceView(), -getBottomView().measuredWidth, 0)
//        dragHelper.settleCapturedViewAt(-getBottomView().measuredWidth, 0)
    }

    companion object {
        private const val TAG = "SwipeLayout"
    }
}