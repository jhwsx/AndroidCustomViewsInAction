package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Canvas.drawPath 方法演示直线路径
 *
 * @author wangzhichao
 * @since 20-3-8
 */
class A_CanvasLinePathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // 1, 创建 Path 对象
    private val path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.strokeWidth = 5f
        // 注意这里的 setStyle 对绘制路径有影响：若为 STROKE, 则只是描边；若为 FILL 或 FILL_AND_STROKE, 则会填充。
        // 即便是不封闭的路径,也会有影响.
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED

        // 2, 指定起始点,如果没有设置起始点，那么起始点就是（0, 0）
        path.moveTo(10f, 10f)
        // 3, 指定第一条直线的终点；也是第二条直线的起点
        path.lineTo(10f, 100f)
        // 4, 指定第二条直线的终点
        path.lineTo(300f, 100f)
        // 5, 闭环: 这会在第二条直线的终点和第一条线的起点创建一根连线
        path.close()
        // 6, 绘制路径
        canvas.drawPath(path, paint)

        path.reset()
        path.moveTo(350f, 10f)
        path.lineTo(350f, 100f)
        path.lineTo(600f, 100f)
        canvas.drawPath(path, paint)

        paint.style = Paint.Style.FILL
        path.reset()
        path.moveTo(10f, 120f)
        path.lineTo(10f, 220f)
        path.lineTo(300f, 220f)
        path.close()
        canvas.drawPath(path, paint)

        path.reset()
        path.moveTo(350f, 120f)
        path.lineTo(350f, 220f)
        path.lineTo(600f, 220f)
        canvas.drawPath(path, paint)
    }
}