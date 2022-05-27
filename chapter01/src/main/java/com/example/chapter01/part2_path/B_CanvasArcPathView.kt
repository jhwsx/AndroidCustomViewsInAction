package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Canvas.drawPath 方法演示弧形路径
 *
 * @author wangzhichao
 * @since 20-3-8
 */
class B_CanvasArcPathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    // 1, 创建 Path 对象
    private val path = Path()
    private val rectF = RectF()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        paint.strokeWidth = 5f
        // 注意这里的 setStyle 对绘制路径有影响：若为 STROKE, 则只是描边；若为 FILL 或 FILL_AND_STROKE, 则会填充。
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED


        // 2, 指定起始点,如果没有设置起始点，那么起始点就是（0, 0）
        path.moveTo(10f, 10f)
        rectF.set(100f, 10f, 200f, 100f)
        // 3, 绘制圆弧:水平向右是 0 度，顺时针为正方向。
        // arc 的起点跟 Path 的最后一个点不同时，会在两者间连线；如果没有上一个点，则 arc 的起点就是 Path 的起点
        path.arcTo(rectF, 0f, 90f)
        // 4, 绘制路径
        canvas.drawPath(path, paint)
        paint.strokeWidth = 3f
        paint.color = 0x4400FF00
        canvas.drawOval(rectF, paint)
        paint.color = 0x440000FF
        canvas.drawRect(rectF, paint)

        // ---------------------演示强制将弧的起点起点作为 Path 的起始位置-------
        // 1, 创建 Path 对象
        path.reset()
        // 2, 指定起始点,如果没有设置起始点，那么起始点就是（0, 0）
        path.moveTo(10f, 210f)
        rectF.set(100f, 210f, 200f, 300f)
        // 3, 绘制圆弧:水平向右是 0 度，顺时针为正方向。
        // 最后一个参数 forceMove true 表示总是以 arc 作为一个新的轮廓。
        path.arcTo(rectF, 0f, 90f, true)
        // 4, 绘制路径
        paint.color = Color.RED
        paint.strokeWidth = 5f
        canvas.drawPath(path, paint)
        paint.strokeWidth = 3f
        paint.color = 0x4400FF00
        canvas.drawOval(rectF, paint)
        paint.color = 0x440000FF
        canvas.drawRect(rectF, paint)
    }
}