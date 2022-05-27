package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.ColorInt
import com.example.chapter01.part5_canvas.CanvasRestoreToCountView
import com.example.chapter01.part5_canvas.CanvasScaleView
import com.example.chapter01.part5_canvas.ClipAnimView
import com.example.chapter01.part5_canvas.ClipPathAnimView

/**
 * Canvas 的缩放操作
 * // 没有指定缩放中心，就是原点
 * public void scale(float sx, float sy)
 * public final void scale(float sx, float sy, float px, float py)
 *
 *
 * 参考文章：https://blog.csdn.net/tianjian4592/article/details/45234419
 * 关于 scale 指定缩放中心的文章, 查看: https://blog.csdn.net/u011451706/article/details/52473382
 *
 * @author wangzhichao
 * @since 20-3-17
 */
class CanvasScaleView : View {
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val redPaint = getPaint(Color.RED, Paint.Style.STROKE, 3)
        val greenPaint = getPaint(Color.GREEN, Paint.Style.STROKE, 3)
        val rect = Rect(0, 0, width, height)
        // 绘制一个空心矩形
        canvas.drawRect(rect, redPaint)
        val rect1 = Rect(10, 100, 300, 500)
        canvas.drawRect(rect1, redPaint)
        // 缩放 0.5f
//        canvas.scale(0.5f, 0.5f);
//        canvas.scale(0.5f, 0.5f, getWidth() / 2, getHeight() / 2);
        // 上面的一行等价于下面的三行，好好体会一下 scale 的 px ， py：The x-coord for the pivot point (unchanged by the scale)
        val dx = width / 2
        val dy = height / 2
        Log.d(TAG, "onDraw: 第一次 平移: dx =$dx, dy = $dy")
        canvas.translate(dx.toFloat(), dy.toFloat())
        canvas.scale(0.5f, 0.5f)
        val dx1 = -width / 2
        val dy1 = -height / 2
        Log.d(TAG, "onDraw: 第二次 平移: dx1 =$dx1, dy1 = $dy1")
        canvas.translate(dx1.toFloat(), dy1.toFloat())

        // 再绘制一个实现矩形
        canvas.drawRect(rect, greenPaint)
        canvas.drawRect(rect1, greenPaint)
    }

    fun getPaint(@ColorInt color: Int, style: Paint.Style?, strokeWidth: Int): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.style = style
        paint.color = color
        paint.strokeWidth = strokeWidth.toFloat()
        return paint
    }

    companion object {
        private const val TAG = "CanvasScaleView"
    }
}