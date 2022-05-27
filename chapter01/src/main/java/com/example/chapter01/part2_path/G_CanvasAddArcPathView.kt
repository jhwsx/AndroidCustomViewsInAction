package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * 演示 Path 的 addArc 方法
 * public void addArc(float left, float top, float right, float bottom, float startAngle,
 * float sweepAngle)
 * public void addArc(RectF oval, float startAngle, float sweepAngle)
 *
 * @author wangzhichao
 * @since 20-3-8
 */
class G_CanvasAddArcPathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val rectF = RectF()

    init {
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f

        textPaint.textSize = 40f
        textPaint.strokeWidth = 3f
        textPaint.style = Paint.Style.STROKE
        textPaint.color = 0x44ff0000
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val text = "好好学习，天天向上"
        rectF.set(100f, 100f, 400f, 300f)
        path.reset()
        path.addArc(rectF, 0f, 270f)
        paint.color = Color.GRAY
        canvas.drawRect(rectF, paint)
        paint.color = Color.GREEN
        canvas.drawTextOnPath(text, path, 0f, -10f, textPaint)
        canvas.drawPath(path, paint)

        rectF.set(500f, 100f, 800f, 300f)
        path.reset()
        path.addArc(rectF, 180f, 180f)
        paint.color = Color.GRAY
        canvas.drawRect(rectF, paint)
        paint.color = Color.GREEN
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 0f, -10f, textPaint)
    }
}