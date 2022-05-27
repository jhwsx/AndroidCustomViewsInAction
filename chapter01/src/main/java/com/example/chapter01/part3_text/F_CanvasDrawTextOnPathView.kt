package com.example.chapter01.part3_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * Canvas 绘制文字：沿路径绘制
 * // 参数一：String text 要绘制的文本，
 * // 参数二：Path path 作为文字沿着为基线的路径(这个是重点)
 * // 参数三：float hOffset 沿着路径添加到文本起始位置的距离, 沿路径方向是正方向
 * // 参数四：float vOffset 用来定位文本的在路径之上（-）或路径之下（+）的距离，也就是在基线之下为正，在基线之上为负。
 * public void drawTextOnPath(@NonNull String text, @NonNull Path path, float hOffset,
 * float vOffset, @NonNull Paint paint)
 * // 这个方法可以指定文本中的起始点以及绘制字符的个数。
 * public void drawTextOnPath(@NonNull char[] text, int index, int count, @NonNull Path path,
 * float hOffset, float vOffset, @NonNull Paint paint)
 *
 * @author wangzhichao
 * @since 20-3-11
 */
class F_CanvasDrawTextOnPathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    init {
        paint.strokeWidth = 5f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE

        textPaint.textSize = 60f
        textPaint.strokeWidth = 5f
        textPaint.color = Color.GREEN
        textPaint.style = Paint.Style.FILL
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val text = "床前明月光，疑是地上霜。"

        path.reset()
        path.addCircle(300f, 300f, 200f, Path.Direction.CCW)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 0f, 0f, textPaint)

        path.reset()
        path.addCircle(800f, 300f, 200f, Path.Direction.CCW)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 80f, 30f, textPaint)

        path.reset()
        path.addCircle(300f, 800f, 200f, Path.Direction.CW)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 0f, 0f, textPaint)

        path.reset()
        path.addCircle(800f, 800f, 200f, Path.Direction.CW)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 80f, 30f, textPaint)

        path.reset()
        path.moveTo(100f, 1300f)
        path.lineTo(200f, 1100f)
        path.lineTo(300f, 1400f)
        path.lineTo(500f, 1100f)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath(text, path, 0f, 0f, textPaint)
        // drawTextOnPath() 使用的 Path ，拐弯处全用圆角，别用尖角。
        path.reset()
        path.moveTo(600f, 1300f)
        path.lineTo(700f, 1100f)
        path.lineTo(800f, 1400f)
        path.lineTo(1000f, 1100f)
        canvas.drawPath(path, paint)
        canvas.drawTextOnPath("willwaywang6, keep moving", path, 0f, 0f, textPaint)
    }
}