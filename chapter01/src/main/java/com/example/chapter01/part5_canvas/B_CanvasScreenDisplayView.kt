package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.example.chapter01.part5_canvas.CanvasRestoreToCountView
import com.example.chapter01.part5_canvas.CanvasScaleView
import com.example.chapter01.part5_canvas.ClipAnimView
import com.example.chapter01.part5_canvas.ClipPathAnimView

/**
 * 屏幕显示与 Canvas 的关系
 * 1，每次调用 drawXXX 函数来绘图时，都会产生一个全新的 Canvas 透明图层；
 * 2，如果在调用 drawXXX 函数前，调用平移、旋转等函数对 Canvas 进行操作，
 * 那么这个操作是不可逆的。每次产生的画布的最新位置都是执行这些操作后的位置。
 * 3，在 Canvas 图层与屏幕合成时,超出屏幕范围的图像是不会显示出来的。
 *
 * @author wangzhichao
 * @since 20-3-17
 */
class B_CanvasScreenDisplayView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val rect = Rect()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val greenPaint = getPaint(Color.GREEN)
        val redPaint = getPaint(Color.RED)
        // 在平移之前绘制一个矩形
        rect.set(0, 0, width / 2, 200)
        // 在调用 canvas.drawRect(rect, greenPaint) 时，产生一个 Canvas 透明图层，此时坐标原点是（0,0）；
        // 在 Canvas 上画好之后，覆盖到屏幕上显示出来。
        canvas.drawRect(rect, greenPaint)
        // 平移操作
        // 平移的是坐标系
        canvas.translate(100f, 100f)
        // 在平移之后再绘制一个矩形
        // 在调用 canvas.drawRect(rect, redPaint) 时，又会产生一个 Canvas 透明图层，
        // 此时画布坐标已经改变了，分别向右和向下移动了 100 个像素。
        canvas.drawRect(rect, redPaint)
    }

    private fun getPaint(@ColorInt color: Int): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.color = color
        paint.strokeWidth = 3f
        return paint
    }
}