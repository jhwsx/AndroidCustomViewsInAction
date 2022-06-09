package com.example.chapter06.part3_paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * 设置路径样式
 *
 * @author wangzhichao
 * @date 2019/09/17
 */
class D_SetPathEffectView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val cornerPathEffect1 = CornerPathEffect(40f)
    private val cornerPathEffect2 = CornerPathEffect(80f)

    // 虚线路径效果
    // intervals 参数：必须是大于 2 的偶数个元素，按照「画线长度、空白长度、画线长度、空白长度」……的顺序排列
    private val dashPathEffect = DashPathEffect(floatArrayOf(20f, 10f, 40f, 20f), 0f)

    // 离散路径效果
    // segmentLength 参数：将原来的路径切成多长的线段
    // deviation 参数：被切成的每个小线段的可偏移距离
    private val discretePathEffect = DiscretePathEffect(5f, 10f)

    // 使用 Path 来绘制虚线的路径效果
    // shape 参数：印章路径
    // advance 参数：表示两个印章之间的距离
    // phase 参数：偏移距离
    private val pathDashPathEffect1 = PathDashPathEffect(stampPath,
        40f,
        0f,
        PathDashPathEffect.Style.MORPH)
    private val pathDashPathEffect2 = PathDashPathEffect(stampPath,
        40f,
        0f,
        PathDashPathEffect.Style.TRANSLATE)
    private val pathDashPathEffect3 = PathDashPathEffect(stampPath,
        40f,
        0f,
        PathDashPathEffect.Style.ROTATE)

    // 先对目标 Path 使用一个 PathEffect，然后再对这个改变后的 Path 使用另一个 PathEffect。
    // 先变虚，再偏离
    private val composePathEffect1 = ComposePathEffect(dashPathEffect, discretePathEffect)

    // 先偏移，在变虚
    private val composePathEffect2 = ComposePathEffect(discretePathEffect, dashPathEffect)

    // 分别按照两种 PathEffect 分别对目标进行绘制。
    private val sumPathEffect = SumPathEffect(cornerPathEffect2, dashPathEffect)
    // 草坪效果
    private val grassEffect = SumPathEffect(
        SumPathEffect(
            DiscretePathEffect(2f, 100f),
            DiscretePathEffect(5f, 100f)
        ),
        SumPathEffect(
            DiscretePathEffect(7f, 100f),
            DiscretePathEffect(9f, 100f),
        )
    )

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 把拐角变成圆角。
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        path.reset()
        path.moveTo(100f, 400f)
        path.lineTo(200f, 100f)
        path.lineTo(300f, 400f)
        paint.color = Color.GREEN
        canvas.drawPath(path, paint)
        paint.color = Color.BLUE
        paint.pathEffect = cornerPathEffect1
        canvas.drawPath(path, paint)
        paint.color = Color.RED
        paint.pathEffect = cornerPathEffect2
        canvas.drawPath(path, paint)

        updatePath(50f)
        paint.color = Color.GREEN
        paint.pathEffect = cornerPathEffect1 // 圆形拐角效果
        canvas.drawPath(path, paint)

        // 虚线效果
        updatePath(100f)
        paint.color = Color.GREEN
        paint.pathEffect = dashPathEffect // 虚线效果
        canvas.drawPath(path, paint)

        // 离散路径效果
        updatePath(150f)
        paint.color = Color.GREEN
        paint.pathEffect = discretePathEffect
        canvas.drawPath(path, paint)

        updatePath(200f)
        paint.color = Color.GREEN
        paint.pathEffect = pathDashPathEffect1 // 通过对印章进行变形来过渡
        canvas.drawPath(path, paint)

        updatePath(300f)
        paint.color = Color.GREEN
        paint.pathEffect = pathDashPathEffect2 // 通过对印章进行位移来过渡
        canvas.drawPath(path, paint)

        updatePath(400f)
        paint.color = Color.GREEN
        paint.pathEffect = pathDashPathEffect3 // 通过对印章进行旋转来过渡
        canvas.drawPath(path, paint)

        updatePath(500f)
        paint.color = Color.GREEN
        paint.pathEffect = composePathEffect1
        canvas.drawPath(path, paint)

        updatePath(550f)
        paint.color = Color.GREEN
        paint.pathEffect = composePathEffect2
        canvas.drawPath(path, paint)

        updatePath(600f)
        paint.color = Color.GREEN
        paint.pathEffect = sumPathEffect
        canvas.drawPath(path, paint)

        path.reset()
        path.moveTo(0f, height.toFloat())
        path.lineTo(width.toFloat(), height.toFloat())
        paint.pathEffect = grassEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null
    }

    private fun updatePath(offset: Float) {
        path.reset()
        path.moveTo(50f, offset + 600f)
        path.lineTo(width * 0.1f, offset + 500f)
        path.lineTo(width * 0.3f, offset + 650f)
        path.lineTo(width * 0.4f, offset + 550f)
        path.lineTo(width * 0.6f, offset + 600f)
        path.lineTo(width * 0.65f, offset + 500f)
        path.lineTo(width * 0.8f, offset + 650f)
        path.lineTo(width * 1f - 50f, offset + 600f)
    }

    private val stampPath: Path
        get() {
            val result = Path()
            result.moveTo(0f, 20f)
            result.lineTo(10f, 0f)
            result.lineTo(20f, 20f)
            result.close()
            result.addCircle(0f, 0f, 3f, Path.Direction.CCW)
            return result
        }
}