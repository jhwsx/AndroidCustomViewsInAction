package com.example.chapter06.part3_paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.common.dp

/**
 * 向日葵效果
 * @author wangzhichao
 * @since 2022/5/22
 */
class D_SunflowerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    private val rectF = RectF()
    private val radius = 100.dp
    private val segment = (Math.PI * 2 * radius / 24).toFloat()
    private val pathDashPathEffect = PathDashPathEffect(stampPath,
        segment,
        0f,
        PathDashPathEffect.Style.MORPH)

    // 外发光效果
    private val blurMaskFilter = BlurMaskFilter(10.dp, BlurMaskFilter.Blur.SOLID)
    // 辐射状渐变着色器
    private var radialGradient: RadialGradient? = null
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 1, 绘制向日葵花瓣
        paint.style = Paint.Style.STROKE
        path.reset()
        path.addCircle(width / 2f, height / 2f, radius, Path.Direction.CCW)
        paint.color = Color.parseColor("#F79709")
        paint.pathEffect = pathDashPathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null
        // 2，绘制向日葵花盘
        paint.style = Paint.Style.FILL
        paint.maskFilter = blurMaskFilter
        if (radialGradient == null) {
            radialGradient = RadialGradient(width / 2f, height / 2f, radius, Color.parseColor("#FFEEEE11"), Color.parseColor("#CCEEEE11"), Shader.TileMode.CLAMP)
        }
        paint.shader = radialGradient
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)
        paint.shader = null
        paint.maskFilter = null
    }

    private val stampPath: Path
        get() {
            val path = Path()
            rectF.set(0f, -50f, segment, 50f)
            path.addArc(rectF, 0f, 180f)
            return path
        }
}