package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.example.chapter01.R
import com.example.common.ImageUtils
import com.example.common.dp

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
class E_CanvasSkewView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val redPaint = getPaint(Color.RED)
    private val greenPaint = getPaint(Color.GREEN)
    private val rect = RectF()
    private val bitmap = ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, 100.dp.toInt())
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect.set(0F, 0F, width.toFloat(), height.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 绘制一个空心矩形
        canvas.drawRect(rect, redPaint)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        canvas.save()
        // 在 x 方向上倾斜 60 度，则 tan60 = 1.732f
//        canvas.skew(1.732f, 0f);
        // 在 y 方向上倾斜 60 度，则 tan60 = 1.732f
        canvas.skew(0f, 1.732f)

        // 再绘制一个实现矩形
        canvas.drawRect(rect, greenPaint)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        canvas.restore()
    }

    private fun getPaint(@ColorInt color: Int): Paint {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = color
        return paint
    }
}