package com.example.chapter13.part1

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.OverScroller
import android.widget.Scroller
import androidx.core.animation.doOnEnd
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.example.chapter13.R
import com.example.common.ImageUtils
import com.example.common.dp
import java.lang.Float.max
import kotlin.math.min

/**
 * PhotoView
 * @author wangzhichao
 * @since 2022/7/30
 */
class A_PhotoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val bitmap =
        ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, IMAGE_SIZE)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * 图片的绘制起始 X 位置
     */
    private var originalOffsetX = 0f

    /**
     * 图片的绘制起始 Y 位置
     */
    private var originalOffsetY = 0f

    /**
     * 图片的额外偏移 x 值
     */
    private var extraOffsetX = 0f

    /**
     * 图片的额外偏移 y 值
     */
    private var extraOffsetY = 0f

    /**
     * 小的放大比例
     */
    private var smallScale = 0f

    /**
     * 大的放大比例
     */
    private var bigScale = 0f

    private var big = false

    /**
     * 大的放大比例下 x 方向上移动的上限
     */
    private var scrollXLimit = 0f

    /**
     * 大的放大比例下 y 方向上移动的上限
     */
    private var scrollYLimit = 0f

    private var currentScale = 0f
        set(value) {
            field = value
            invalidate()
        }
    private lateinit var scaleAnimator: ObjectAnimator

    /**
     * 手势侦测器
     */
    private val gestureDetector = GestureDetectorCompat(context, MySimpleOnGestureListener())/*.apply {
        setOnDoubleTapListener(this@A_PhotoView)
        // 内部会判断 this
        // if (listener instanceof OnDoubleTapListener) {
        //    setOnDoubleTapListener((OnDoubleTapListener) listener);
        // }
    }*/

    // 从速度 0 启动开始滚动，最后再到速度 0
//    private val scroller = Scroller(context)
    // 从一个初始速度启动开始滚动，最后再慢慢减到速度 0，适合做惯性滑动
    private val scroller = OverScroller(context)

    private val flingRunnable = FlingRunnable()

    private val scaleGestureDetector = ScaleGestureDetector(context, MyOnScaleGestureListener())

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(event)
        if (!scaleGestureDetector.isInProgress) {
            // 挂载手势侦测器，让手势侦测器来接管触摸事件的处理
            gestureDetector.onTouchEvent(event)
        }
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originalOffsetX = (w - bitmap.width) / 2f
        originalOffsetY = (h - bitmap.height) / 2f
        // 图片的宽高比大于控件的宽高比
        if (bitmap.width / bitmap.height.toFloat() > w / h.toFloat()) {
            smallScale = w / bitmap.width.toFloat()
            bigScale = h / bitmap.height.toFloat() * EXTRA_SCALE_FACTOR
        } else {
            smallScale = h / bitmap.height.toFloat()
            bigScale = w / bitmap.width.toFloat() * EXTRA_SCALE_FACTOR
        }
        scrollXLimit = (bigScale * bitmap.width - w) / 2
        scrollYLimit = (bigScale * bitmap.height - h) / 2
        scaleAnimator = ObjectAnimator.ofFloat(this, "currentScale", smallScale, bigScale)
        currentScale = smallScale
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 计算出当前的 scaleFraction
        val scaleFraction = (currentScale - smallScale) / (bigScale - smallScale)
        canvas.translate(extraOffsetX * scaleFraction, extraOffsetY * scaleFraction)
        canvas.scale(currentScale, currentScale, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originalOffsetX, originalOffsetY, paint)
    }

    inner class MySimpleOnGestureListener : GestureDetector.SimpleOnGestureListener() {
        // ---------OnGestureListener method start-------------
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        override fun onShowPress(e: MotionEvent?) {
        }

        // onClick 的替代
        // 返回值：是否消费了此点击事件。true、false 都不会影响流程，只是给系统使用的。
        // 当不支持长按的时候，按下抬起就会调用此方法。
        // 当支持长按的时候，只有快速的按下抬起才会调用此方法。
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return false
        }

        // 理解为 onMove
        override fun onScroll(
            firstDownEvent: MotionEvent?,
            currentMoveEvent: MotionEvent?,
            distanceX: Float, // 旧位置的值减去新位置的值
            distanceY: Float,
        ): Boolean {
            if (big) {
                // 只有在大的放大比例下，才允许随手指移动
                extraOffsetX -= distanceX
                extraOffsetY -= distanceY
                fixOffset()
                invalidate()
            }
            return false
        }

        private fun fixOffset() {
            // 处理移动超出边界的问题
            extraOffsetX = min(max(extraOffsetX, -scrollXLimit), scrollXLimit)
            extraOffsetY = min(max(extraOffsetY, -scrollYLimit), scrollYLimit)
        }

        // 长按，onLongClick
        override fun onLongPress(e: MotionEvent?) {
        }

        // 惯性滑动，手指在屏幕上快速滑动离开屏幕后会调用这个方法
        override fun onFling(
            firstDownEvent: MotionEvent?,
            currentMoveEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float,
        ): Boolean {
            if (big) {
                scroller.fling(
                    extraOffsetX.toInt(), extraOffsetY.toInt(),
                    velocityX.toInt(), velocityY.toInt(),
                    (-scrollXLimit).toInt(), scrollXLimit.toInt(),
                    (-scrollYLimit).toInt(), scrollYLimit.toInt()/*,
                OVER_SCROLL_VALUE, OVER_SCROLL_VALUE*/) // Scroller 没有这两个参数。
                ViewCompat.postOnAnimation(this@A_PhotoView, flingRunnable)
                // 在下一帧到来时，把任务推到主线程
//            postOnAnimation(this)
                // 立马把任务推到主线程
//            post(this)
            }
            return false
        }

        // ---------OnGestureListener method end-------------

        // ---------OnDoubleTapListener method start-------------
        // 严格的单击，和 onSingleTapUp 的区别：当双击时，不是双击中的一次点击。
        // 当支持双击时，用这个方法来判断单击，但是这个方法不是很及时（会等待一会儿看有没有双击的第二次点击）。
        // 当不支持双击时，用 onSingleTapUp 来判断单击，更加及时。
        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            return false
        }

        // 双击，在 40 ms -300 ms 之间的两次点击，算是双击。防手抖。
        override fun onDoubleTap(firstDownEvent: MotionEvent): Boolean {
            big = !big
            if (big) {
                // 这里的计算再理解理解吧
                extraOffsetX = (1 - bigScale / smallScale) * (firstDownEvent.x - width / 2)
                extraOffsetY = (1 - bigScale / smallScale) * (firstDownEvent.y - height / 2)
                fixOffset()
                scaleAnimator.start()
            } else {
                // 不要在这里一下子改好，会闪一下的
//            extraOffsetX = 0f
//            extraOffsetY = 0f
                scaleAnimator.reverse()
            }
            return false
        }

        // 会收到双击手势确认后的一系列事件，如 down，move，up，cancel。
        // 一般不用了。
        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            return false
        }
        // ---------OnDoubleTapListener method end-------------
    }

    inner class MyOnScaleGestureListener : ScaleGestureDetector.OnScaleGestureListener {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val tempCurrentScale = currentScale * detector.scaleFactor
            return if (tempCurrentScale < smallScale || tempCurrentScale > bigScale) {
                false
            } else {
                currentScale *= detector.scaleFactor
                true
            }
            // return true，则scaleFactor 是和上次手势的比值；return false，则 scaleFactor 是和初始手势的比值
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            extraOffsetX = (1 - bigScale / smallScale) * (detector.focusX - width / 2)
            extraOffsetY = (1 - bigScale / smallScale) * (detector.focusY - height / 2)
            return true // 一定要返回 true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {
        }
    }

    inner class FlingRunnable : Runnable {
        override fun run() {
            if (scroller.computeScrollOffset()) {
                extraOffsetX = scroller.currX.toFloat()
                extraOffsetY = scroller.currY.toFloat()
                invalidate() // 注意啊：这个 invalidate() 是必须要的，不是多余的。
                ViewCompat.postOnAnimation(this@A_PhotoView, this)
            }
        }
    }

    init {
        // 设置手势侦测器不支持长按。
        // gestureDetector.setIsLongpressEnabled(false)
    }

    companion object {
        private val IMAGE_SIZE = 300.dp.toInt()

        // 为支持双向滑动，让 bigScale 再大一些。
        private const val EXTRA_SCALE_FACTOR = 1.5f

        private val OVER_SCROLL_VALUE = 30.dp.toInt()
    }
}