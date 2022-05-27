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
 * 比较 Path 的 reset() rewind() 方法
 * reset()
 * Clear any lines and curves from the path, making it empty.
 * This does NOT change the fill-type setting.
 *
 * rewind()
 * Rewinds the path: clears any lines and curves from the path but
 * keeps the internal data structure for faster reuse.
 *
 * @author wangzhichao
 * @since 20-3-8
 */
class J_CanvasResetRewindPathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val path2 = Path()
    init {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        path.reset()
        path.rewind()
        path2.reset()
        path2.rewind()

        path.addRect(100f, 100f, 500f, 500f, Path.Direction.CCW)
        path.fillType = Path.FillType.EVEN_ODD
        path.reset() // 不会清掉 fillType，只会清掉之前添加的图形
        path.addCircle(300f, 300f, 150f, Path.Direction.CCW)
        path.addCircle(300f, 300f, 100f, Path.Direction.CCW)
        canvas.drawPath(path, paint)

        path2.addRect(100f, 600f, 500f, 1000f, Path.Direction.CCW)
        path2.fillType = Path.FillType.EVEN_ODD
        path2.rewind() // 会清除填充类型和之前添加的图形
        path2.addCircle(300f, 800f, 150f, Path.Direction.CCW)
        path2.addCircle(300f, 800f, 100f, Path.Direction.CCW)
        canvas.drawPath(path2, paint)
    }
}