package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.common.dp

/**
 * 获取实际 Path
 *
 * @author wangzhichao
 * @since 2022/5/31
 */
class O_GetFillPathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val srcPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dstPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
    }
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        textSize = 18.dp
    }
    private val src = Path()
    private val dst = Path()
    private var drawFillPathOnly: Boolean = false
    private val dashPathEffect = DashPathEffect(floatArrayOf(15.dp, 5.dp, 10.dp, 8.dp), 0f)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText("点击屏幕在路径和实际路径之间切换", width / 2f, 80f, textPaint)
        srcPaint.style = Paint.Style.STROKE
        srcPaint.strokeWidth = 1f
        src.reset()
        src.moveTo(10.dp, 150.dp)
        src.rLineTo(100.dp, (-90).dp)
        src.rLineTo(100.dp, 100.dp)
        src.rLineTo(100.dp, (-80).dp)
        if (!drawFillPathOnly) {
            canvas.drawPath(src, srcPaint)
        }

        dstPaint.getFillPath(src, dst)
        canvas.drawPath(dst, dstPaint)

        srcPaint.style = Paint.Style.STROKE
        srcPaint.strokeWidth = 10.dp
        src.reset()
        src.moveTo(10.dp, 250.dp)
        src.rLineTo(100.dp, (-90).dp)
        src.rLineTo(100.dp, 100.dp)
        src.rLineTo(100.dp, (-80).dp)
        if (!drawFillPathOnly) {
            canvas.drawPath(src, srcPaint)
        }

        srcPaint.getFillPath(src, dst)
        canvas.drawPath(dst, dstPaint)

        srcPaint.style = Paint.Style.STROKE
        srcPaint.strokeWidth = 10.dp
        srcPaint.pathEffect = dashPathEffect
        src.reset()
        src.moveTo(10.dp, 350.dp)
        src.rLineTo(100.dp, (-90).dp)
        src.rLineTo(100.dp, 100.dp)
        src.rLineTo(100.dp, (-80).dp)
        if (!drawFillPathOnly) {
            canvas.drawPath(src, srcPaint)
        }

        srcPaint.getFillPath(src, dst)
        srcPaint.pathEffect = null
        canvas.drawPath(dst, dstPaint)

        srcPaint.style = Paint.Style.FILL_AND_STROKE
        srcPaint.strokeWidth = 10.dp
        src.reset()
        src.addCircle(width / 2f, 500.dp, 50.dp, Path.Direction.CCW)
        if (!drawFillPathOnly) {
            canvas.drawPath(src, srcPaint)
        }

        srcPaint.getFillPath(src, dst)
        canvas.drawPath(dst, dstPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        drawFillPathOnly = !drawFillPathOnly
        invalidate()
        return super.onTouchEvent(event)
    }
}