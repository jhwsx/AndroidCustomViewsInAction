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
 * Canvas 的扭曲操作,没有中心点的概念，就是原点
 * float sx:将画布在 X 轴方向上倾斜相应的角度,sx 为倾斜角度的正切值。
 * float sy:将画布在 Y 轴方向上倾斜 相应的角度,sy 为倾斜角度的正切值。
 * public void skew(float sx, float sy)
 *
 *
 * 参考文章：https://blog.csdn.net/tianjian4592/article/details/45234419
 *
 * @author wangzhichao
 * @since 20-3-17
 */
class CanvasSkewView : View {
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
        val rect1 = Rect(10, 10, 300, 500)
        canvas.drawRect(rect1, redPaint)
        // 在 x 方向上倾斜 60 度，则 tan60 = 1.732f
//        canvas.skew(1.732f, 0f);
        canvas.skew(0f, 1.732f)

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
}