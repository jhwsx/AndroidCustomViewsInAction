package com.example.chapter01.part1_basic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.jvm.JvmOverloads

/**
 * Canvas 的 画多线 方法演示
 *
 * @author wangzhichao
 * @since 20-3-6
 */
class G_CanvasDrawLinesView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var paint = Paint()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.isAntiAlias = true
        paint.strokeWidth = 10f
        paint.color = Color.RED
        val pts = floatArrayOf(
            100f, 100f, 100f, 200f,
            100f, 200f, 200f, 200f,
            200f, 200f, 200f, 300f,) // 假如有一组不能凑够 4 个值，那么就不能绘制出对应的线
        canvas.drawLines(pts, paint)
        val pts2 = floatArrayOf(
            100f, 400f, 100f, 500f,
            100f, 500f, 200f, 500f,
            200f, 500f, 200f, 600f,)
        val offset = 4
        val count = 4
        /*
         * 参数二 offset：开始绘制前要跳过的数组中的值的个数;
         * 参数三 count：在 offset 作用之后，设置处理数组中的几个值，这个数要设置成 4 的倍数才会有线绘制出来。
         */canvas.drawLines(pts2, offset, count, paint)
    }
}