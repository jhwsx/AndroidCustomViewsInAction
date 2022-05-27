package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.common.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * 饼图
 * @author wangzhichao
 * @since 2022/5/23
 */
private data class Section(val angle: Float, val color: Int)

private val sections = listOf(
    Section(60f, Color.RED),
    Section(100f, Color.BLUE),
    Section(120f, Color.YELLOW),
    Section(80f, Color.GREEN),
)
private val radius = 100f.dp
private const val offsetIndex = 2
private val offsetDistance = radius / 10
class N_PieView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF = RectF()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(width / 2f - radius,
            height / 2f - radius,
            width / 2f + radius,
            height / 2f + radius)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var startAngle = 0f
        for ((index, section) in sections.withIndex()) {
            if (index == offsetIndex) {
                canvas.save()
                // 扇区对角线的角度
                val diagonalAngle = startAngle + section.angle / 2.0
                val dx = offsetDistance * cos(Math.toRadians(diagonalAngle))
                val dy = offsetDistance * sin(Math.toRadians(diagonalAngle))
                canvas.translate(dx.toFloat(), dy.toFloat())
            }
            // 绘制扇区
            paint.color = section.color
            canvas.drawArc(rectF, startAngle, section.angle, true, paint)
            startAngle += section.angle
            if (index == offsetIndex) {
                canvas.restore()
            }
        }
    }
}