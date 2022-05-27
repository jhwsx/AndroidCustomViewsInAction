package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * 演示 Path 的 addRoundRect 方法
 * // 四个圆角一起设置的方法
 * public void addRoundRect(RectF rect, float rx, float ry, Direction dir)
 * public void addRoundRect(float left, float top, float right, float bottom, float rx, float ry,
 * Direction dir)
 * // 四个圆角可以分别设置的方法
 * public void addRoundRect(RectF rect, float[] radii, Direction dir)
 * public void addRoundRect(float left, float top, float right, float bottom, float[] radii,
 * Direction dir)
 *
 * @author wangzhichao
 * @since 20-3-8
 */
class D_CanvasAddRoundRectPathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val rectF = RectF()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f

        textPaint.textSize = 40f
        textPaint.strokeWidth = 3f
        textPaint.style = Paint.Style.STROKE
        textPaint.color = 0x44ff0000

        canvas.drawText("逆时针绘制矩形", 100f, 100f, textPaint)
        rectF.set(100f, 200f, 400f, 400f)
        path.reset()
        path.addRoundRect(rectF, 40f, 20f, Path.Direction.CCW)
        canvas.drawPath(path, paint)

        canvas.drawText("顺时针绘制矩形", 500f, 100f, textPaint)
        rectF.set(500f, 200f, 800f, 400f)
        val radii = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 100f, 20f)
        path.reset()
        path.addRoundRect(rectF, radii, Path.Direction.CW)
        canvas.drawPath(path, paint)

        val text = "好好学习，天天向上"
        // 生成的方向决定了依据路径进行排版的文字方向
        rectF.set(100f, 500f, 400f, 700f)
        path.reset()
        path.addRoundRect(rectF, 40f, 20f, Path.Direction.CCW)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 0f, 0f, textPaint)
        rectF.set(500f, 500f, 800f, 700f)
        path.reset()
        path.addRoundRect(rectF, radii, Path.Direction.CW)
        canvas.drawPath(path, paint)
        // 这个顺时针的带圆角的文字起点有些不同：
        // 如果有圆角，那么起点就是左下角；
        // 如果没有圆角，那么起点就是左上角。
        canvas.drawTextOnPath(text, path, 0f, 9f, textPaint)
    }
}