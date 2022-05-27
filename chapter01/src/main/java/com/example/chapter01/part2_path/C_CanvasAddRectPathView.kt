package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * 演示 Path 的 addRect 方法
 * public void addRect(RectF rect, Direction dir)
 * public void addRect(float left, float top, float right, float bottom, Direction dir)
 *
 * @author wangzhichao
 * @since 20-3-8
 */
class C_CanvasAddRectPathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()
    private val path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.textSize = 40f

        textPaint.textSize = 40f
        textPaint.strokeWidth = 3f
        textPaint.style = Paint.Style.STROKE
        textPaint.color = 0x44ff0000
        textPaint.textAlign = Paint.Align.LEFT

        canvas.drawText("逆时针绘制矩形", 100f, 100f, textPaint)
        rectF.set(100f, 200f, 400f, 400f)
        path.addRect(rectF, Path.Direction.CCW)
        canvas.drawPath(path, paint)

        canvas.drawText("顺时针绘制矩形", 500f, 100f, textPaint)
        rectF.set(500f, 200f, 800f, 400f)
        path.reset()
        path.addRect(rectF, Path.Direction.CW)
        canvas.drawPath(path, paint)

        // 生成的方向决定了依据路径进行排版的文字方向
        val text = "好好学习，天天向上"
        textPaint.textAlign = Paint.Align.LEFT
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        rectF.set(100f, 500f, 400f, 700f)
        path.reset()
        path.addRect(rectF, Path.Direction.CCW)
        canvas.drawPath(path, paint)
        // 沿着绘制方向是 x 轴的正方向，垂直于 x 轴向下的方向是 y 轴的正方向
        // vOffset 为 +，代表向 y 轴正方向偏移
        canvas.drawTextOnPath(text, path, 0f, 18f, textPaint)
        rectF.set(500f, 500f, 800f, 700f)
        path.reset()
        path.addRect(rectF, Path.Direction.CW)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 0f, 0f, textPaint)

        // The paint's Align setting determines where along the path to start the text.
        textPaint.textAlign = Paint.Align.RIGHT
        rectF.set(100f, 800f, 400f, 1000f)
        path.reset()
        path.addRect(rectF, Path.Direction.CCW)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 0f, 18f, textPaint)
        rectF.set(500f, 800f, 800f, 1000f)
        path.reset()
        path.addRect(rectF, Path.Direction.CW)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 0f, 0f, textPaint)
    }
}