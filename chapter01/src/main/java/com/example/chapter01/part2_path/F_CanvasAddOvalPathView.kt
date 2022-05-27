package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * 演示 Path 的 addOval 方法
 * public void addOval(RectF oval, Direction dir)
 * public void addOval(float left, float top, float right, float bottom, Direction dir)
 *
 * @author wangzhichao
 * @since 20-3-8
 */
class F_CanvasAddOvalPathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
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


        rectF.set(100f, 100f, 400f, 300f)
        path.reset()
        path.addOval(rectF, Path.Direction.CCW)
        canvas.drawPath(path, paint)

        rectF.set(500f, 100f, 800f, 300f)
        path.reset()
        path.addOval(rectF, Path.Direction.CW)
        canvas.drawPath(path, paint)

        val text = "好好学习，天天向上"
        // 生成的方向决定了依据路径进行排版的文字方向
        rectF.set(100f, 500f, 400f, 700f)
        path.reset()
        path.addOval(rectF, Path.Direction.CCW)
        canvas.drawPath(path, paint)
        // 起点是在 3 点钟位置.
        canvas.drawTextOnPath(text, path, 0f, 18f, textPaint)

        rectF.set(500f, 500f, 800f, 700f)
        path.reset()
        path.addOval(rectF, Path.Direction.CW)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 50f, 9f, textPaint)
    }
}