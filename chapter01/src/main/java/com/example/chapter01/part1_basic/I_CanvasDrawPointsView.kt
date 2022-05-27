package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Canvas 的 画多点方法演示
 *
 * @author wangzhichao
 * @since 20-3-6
 */
class I_CanvasDrawPointsView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var paint = Paint()
    var pts = FloatArray(24)
    var pts2 = FloatArray(24)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.isAntiAlias = true
        paint.strokeWidth = 10f
        paint.color = Color.RED

        // 2 个值确定一个点，总共是 12 个点
        for (i in 0..11) {
            pts[i * 2] = (200 + Math.cos(Math.toRadians((i * 30).toDouble())) * 100).toFloat()
            pts[i * 2 + 1] = (200 + Math.sin(Math.toRadians((i * 30).toDouble())) * 100).toFloat()
        }
        canvas.drawPoints(pts, paint)
        for (i in 0..11) {
            pts2[i * 2] = (200 + Math.cos(Math.toRadians((i * 30).toDouble())) * 100).toFloat()
            pts2[i * 2 + 1] = (600 + Math.sin(Math.toRadians((i * 30).toDouble())) * 100).toFloat()
        }
        // 跳过 4 个数值，即跳过 2 个点，只绘制 12 个数值，即绘制 6 个点。
        canvas.drawPoints(pts2, 4, 12, paint)
    }
}