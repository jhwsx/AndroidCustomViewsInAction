package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.util.AttributeSet
import android.view.View
import com.example.common.dp
import com.example.common.sp

/**
 * PathMeasure
 *
 * @author wangzhichao
 * @since 2022/5/22
 */
class I_PathMeasureView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val radius = 100f.dp
    private lateinit var pathMeasure: PathMeasure
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        path.addCircle(width / 2f, height / 2f, radius, Path.Direction.CCW)
        pathMeasure = PathMeasure(path, false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style = Paint.Style.FILL
        canvas.drawPath(path, paint)
        paint.strokeWidth = 5f
        paint.textSize = 16f.sp
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("pathMeasure.length=${pathMeasure.length}",
            width / 2f,
            height / 2f + 150f.dp,
            paint)
        canvas.drawText("circle perimeter=${2 * Math.PI * radius}",
            width / 2f,
            height / 2f + 150f.dp + paint.fontSpacing,
            paint)
    }
}