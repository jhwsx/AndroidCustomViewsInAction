package com.example.chapter01.part2_path

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.AccelerateInterpolator
import kotlin.jvm.JvmOverloads
import java.util.ArrayList
import kotlin.math.sqrt

class L_YoutubePlayView @JvmOverloads constructor(context: Context?, attrs: AttributeSet? = null) :
    View(context, attrs) {
    private val path1 = Path()
    private val path2 = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 存放左边部分起始点的集合
    private val leftStartPointList: MutableList<PointF> = ArrayList()

    // 存放左边部分终止点的集合
    private val leftEndPointList: MutableList<PointF> = ArrayList()

    // 存放左边部分进度点的集合
    private val leftProgressPointList: MutableList<PointF> = ArrayList()

    // 存放右边部分起始点的集合
    private val rightStartPointList: MutableList<PointF> = ArrayList()

    // 存放右边部分终止点的集合
    private val rightEndPointList: MutableList<PointF> = ArrayList()

    // 存放右边部分进度点的集合
    private val rightProgressPointList: MutableList<PointF> = ArrayList()

    // 集合数据是否填充的标记
    private var isListDataFilled = false

    init {
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        initPointList()
    }

    // 提前把点集合创建好，不在 onDraw 方法里面创建
    private fun initPointList() {
        for (i in 0..3) {
            leftStartPointList.add(PointF())
            leftEndPointList.add(PointF())
            rightStartPointList.add(PointF())
            rightEndPointList.add(PointF())
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension((HEIGHT * sqrt(3.0) / 2).toInt(), HEIGHT)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        fillListData(width, height)
        path1.reset()
        path2.reset()
        path1.moveTo(leftProgressPointList[0].x, leftProgressPointList[0].y)
        path1.lineTo(leftProgressPointList[1].x, leftProgressPointList[1].y)
        path1.lineTo(leftProgressPointList[2].x, leftProgressPointList[2].y)
        path1.lineTo(leftProgressPointList[3].x, leftProgressPointList[3].y)
        path1.close()
        canvas.drawPath(path1, paint)
        path2.moveTo(rightProgressPointList[0].x, rightProgressPointList[0].y)
        path2.lineTo(rightProgressPointList[1].x, rightProgressPointList[1].y)
        path2.lineTo(rightProgressPointList[2].x, rightProgressPointList[2].y)
        path2.lineTo(rightProgressPointList[3].x, rightProgressPointList[3].y)
        path2.close()
        canvas.drawPath(path2, paint)
    }

    private fun fillListData(width: Int, height: Int) {
        if (isListDataFilled) {
            return
        }
        isListDataFilled = true
        // 左半部分三角形的四个点
        leftStartPointList[0].set(0f, 0f)
        leftStartPointList[1].set(width / 2f, height / 4f)
        leftStartPointList[2].set(width / 2f, height * 3 / 4f)
        leftStartPointList[3].set(0f, height.toFloat())
        // 这里不能使用 addAll 方法，因为 pointList11 的点是复用的点。
        // 初始化左半部分中间状态的四个点
        for (pointF in leftStartPointList) {
            leftProgressPointList.add(PointF(pointF.x, pointF.y))
        }
        // 左边竖线的四个点
        leftEndPointList[0].set(0f, 0f)
        leftEndPointList[1].set(width / 3f, 0f)
        leftEndPointList[2].set(width / 3f, height.toFloat())
        leftEndPointList[3].set(0f, height.toFloat())

        // 右半部分三角形的四个点
        rightStartPointList[0].set(width.toFloat(), height / 2f)
        rightStartPointList[1].set(width / 2f, height / 4f)
        rightStartPointList[2].set(width / 2f, height * 3 / 4f)
        rightStartPointList[3].set(width.toFloat(), height / 2f)
        // 初始化右半部分中间状态的四个点
        for (pointF in rightStartPointList) {
            rightProgressPointList.add(PointF(pointF.x, pointF.y))
        }
        // 右边竖线的四个点
        rightEndPointList[0].set(width.toFloat(), 0f)
        rightEndPointList[1].set(width * 2 / 3f, 0f)
        rightEndPointList[2].set(width * 2 / 3f, height.toFloat())
        rightEndPointList[3].set(width.toFloat(), height.toFloat())

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (state == STATE_STOP) {
            play()
        } else if (state == STATE_PLAYING) {
            stop()
        }
        return super.onTouchEvent(event)
    }

    private var state = STATE_STOP
    private var animPlaying = false
    fun play() {
        if (animPlaying) return
        if (state == STATE_PLAYING) return
        state = STATE_PLAYING
        val va = ValueAnimator.ofFloat(0f, 1f)
        va.addUpdateListener { animation ->
            val percent = animation.animatedValue as Float
            for (i in 0..3) {
                leftProgressPointList[i].set(
                    leftStartPointList[i].x + percent * (leftEndPointList[i].x - leftStartPointList[i].x),
                    leftStartPointList[i].y + percent * (leftEndPointList[i].y - leftStartPointList[i].y))
                rightProgressPointList[i].set(
                    rightStartPointList[i].x + percent * (rightEndPointList[i].x - rightStartPointList[i].x),
                    rightStartPointList[i].y + percent * (rightEndPointList[i].y - rightStartPointList[i].y))
            }
            invalidate()
        }
        va.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                animPlaying = false
            }

            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                animPlaying = true
            }
        })
        va.duration = 500
        va.interpolator = AccelerateInterpolator()
        va.start()
    }

    fun stop() {
        if (animPlaying) return
        if (state == STATE_STOP) return
        state = STATE_STOP
        val va = ValueAnimator.ofFloat(0f, 1f)
        va.addUpdateListener { animation ->
            val percent = animation.animatedValue as Float
            // 从 12 -> 11, 22 -> 21
            for (i in 0..3) {
                leftProgressPointList[i].set(leftEndPointList[i].x + percent * (leftStartPointList[i].x - leftEndPointList[i].x),
                    leftEndPointList[i].y + percent * (leftStartPointList[i].y - leftEndPointList[i].y))
                rightProgressPointList[i].set(rightEndPointList[i].x + percent * (rightStartPointList[i].x - rightEndPointList[i].x),
                    rightEndPointList[i].y + percent * (rightStartPointList[i].y - rightEndPointList[i].y))
            }
            invalidate()
        }
        va.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                animPlaying = false
            }

            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                animPlaying = true
            }
        })
        va.duration = 500
        va.interpolator = AccelerateInterpolator()
        va.start()
    }

    companion object {
        private const val TAG = "YoutubePlayView"
        private const val STATE_STOP = 0
        private const val STATE_PLAYING = 1
        private const val HEIGHT = 100
    }
}