package com.example.chapter13.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.example.common.dp

/**
 * 独立型多点触控
 * @author wangzhichao
 * @since 2022/8/5
 */
class D_IndependentMultiTouchView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 5.dp
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
        strokeJoin = Paint.Join.ROUND
    }

    private val paths = SparseArray<Path>()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (index in 0 until paths.size()) {
            val path = paths.valueAt(index)
            canvas.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked.also {
            Log.d(TAG, "onTouchEvent: action=${MotionEvent.actionToString(it)}")
        }) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val path = PathPool.obtainPath()
                val actionIndex = event.actionIndex
                path.moveTo(event.getX(actionIndex), event.getY(actionIndex))
                val pointerId = event.getPointerId(actionIndex)
                paths.append(pointerId, path)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                for (index in 0 until event.pointerCount) {
//                for (index in 0 until paths.size()) { // 用这个测试会报越界的错误。
//                    Log.d(TAG, "onTouchEvent: index=$index")
                    val pointerId = event.getPointerId(index)
                    val path = paths.get(pointerId)
                    path.lineTo(event.getX(index), event.getY(index))
                }
                invalidate()
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                val actionIndex = event.actionIndex
                val pointerId = event.getPointerId(actionIndex)
                PathPool.releasePath(paths.get(pointerId))
                paths.remove(pointerId)
                invalidate()
            }
        }
        return true
    }

    object PathPool {
        private val pathPool = mutableListOf<Path>()

        fun obtainPath(): Path {
            synchronized(pathPool) {
                val size = pathPool.size
                if (size > 0) {
                    val path = pathPool.removeAt(size - 1)
                    path.reset()
                    return path
                }
            }
            return Path()
        }

        fun releasePath(path: Path) {
            path.reset()
            synchronized(pathPool) {
                if (pathPool.size < 20) {
                    pathPool.add(path)
                }
            }
        }
    }

    companion object {
        private const val TAG = "D_IndependentMultiTouch"
    }
}