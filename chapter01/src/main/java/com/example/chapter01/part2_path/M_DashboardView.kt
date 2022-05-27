package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.util.toHalf
import com.example.common.dp
import kotlin.math.cos
import kotlin.math.sin

/**
 * 仪表盘
 * @author wangzhichao
 * @since 2022/5/22
 */
private const val OPEN_ANGLE = 120f
private const val FULL_ANGLE = 360f
private val SCALE_WIDTH = 2f.dp
private val SCALE_LENGTH = 10f.dp
private const val SCALE_NUM = 20
private val POINTER_LENGTH = 120f.dp
private val POINTER_TAIL_LENGTH = 30f.dp
class M_DashboardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val scalePath = Path()
    private val rectF = RectF()
    private val radius = 150f.dp
    private lateinit var pathDashPathEffect: PathDashPathEffect
    private val path = Path()
    private var centerX: Float = 0f
    private var centerY: Float = 0f

    init {
        paint.strokeWidth = 3f.dp
        scalePath.addRect(0f, 0f, SCALE_WIDTH, SCALE_LENGTH, Path.Direction.CCW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width / 2f
        centerY = height / 2f
        rectF.set(centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius)
        path.reset()
        path.addArc(rectF, 90f + OPEN_ANGLE / 2f, FULL_ANGLE - OPEN_ANGLE)
        val pathMeasure = PathMeasure(path, false)
        pathDashPathEffect = PathDashPathEffect(scalePath,
            (pathMeasure.length - SCALE_WIDTH) / SCALE_NUM,
            0f,
            PathDashPathEffect.Style.ROTATE)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style = Paint.Style.STROKE
        // 绘制圆弧
        canvas.drawPath(path, paint)
        // 绘制刻度
        paint.pathEffect = pathDashPathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null
        // 绘制指针
        val indicator = 5
        val angle =
            90f + OPEN_ANGLE / 2f + (indicator * 1.0 / SCALE_NUM) * (FULL_ANGLE - OPEN_ANGLE)
        val stopX = centerX + (POINTER_LENGTH * cos(Math.toRadians(angle))).toFloat()
        val stopY = centerY + (POINTER_LENGTH * sin(Math.toRadians(angle))).toFloat()
        val startX = centerX - (POINTER_TAIL_LENGTH * cos(Math.toRadians(angle))).toFloat()
        val startY = centerY - (POINTER_TAIL_LENGTH * sin(Math.toRadians(angle))).toFloat()
        canvas.drawLine(startX, startY, stopX, stopY, paint)
        // 绘制针座
        paint.style = Paint.Style.FILL
        canvas.drawCircle(centerX, centerY, 6f.dp, paint)
    }
}