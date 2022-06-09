package com.example.chapter06.part3_paint

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.example.common.dp

/**
 * 这个方法是对于 setStrokeJoin() 的一个补充，它用于设置 MITER 型拐角的延长线的最大值。
 *
 * 为了避免意料之外的过长的尖角出现， MITER 型连接点有一个额外的规则：当尖角过长时，
 * 自动改用 BEVEL 的方式来渲染连接点。
 *
 * @author wangzhichao
 * @since 2022/5/31
 */
class C_SetStrokeMiterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.MITER
        strokeWidth = 3.dp
    }
    private val path = Path()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.strokeMiter = 10000f
        path.reset()
        path.moveTo(50.dp, height.toFloat() - 10.dp)
        path.rLineTo(5.dp, -height * 0.6f)
        path.rLineTo(5.dp, height * 0.6f)
        canvas.drawPath(path, paint)

        paint.strokeMiter = 100f
        path.reset()
        path.moveTo(100.dp, height.toFloat() - 10.dp)
        path.rLineTo(5.dp, -height * 0.6f)
        path.rLineTo(5.dp, height * 0.6f)
        canvas.drawPath(path, paint)

        paint.strokeMiter = 80f
        path.reset()
        path.moveTo(150.dp, height.toFloat() - 10.dp)
        path.rLineTo(5.dp, -height * 0.6f)
        path.rLineTo(5.dp, height * 0.6f)
        canvas.drawPath(path, paint)

        paint.strokeMiter = 50f
        path.reset()
        path.moveTo(200.dp, height.toFloat() - 10.dp)
        path.rLineTo(5.dp, -height * 0.6f)
        path.rLineTo(5.dp, height * 0.6f)
        canvas.drawPath(path, paint)

        paint.strokeMiter = 1f
        path.reset()
        path.moveTo(250.dp, height.toFloat() - 10.dp)
        path.rLineTo(5.dp, -height * 0.6f)
        path.rLineTo(5.dp, height * 0.6f)
        canvas.drawPath(path, paint)
    }
}