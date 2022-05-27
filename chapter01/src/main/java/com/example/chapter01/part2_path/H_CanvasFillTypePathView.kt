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
 * Path 的填充模式
 * Path.FillType.WINDING
 * Path.FillType.EVEN_ODD
 * Path.FillType.INVERSE_WINDING
 * Path.FillType.INVERSE_EVEN_ODD
 * 查看:
 * https://rengwuxian.com/ui-1-1/
 * EVEN_ODD：不关心方向，相交数为奇数，则为内部；相交数为偶数，则为外部；
 * WINDING: 关心方法，非0为内部，0为外部。
 * @author wangzhichao
 * @since 20-3-8
 */
class H_CanvasFillTypePathView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()
    init {
        paint.color = Color.RED
        paint.style = Paint.Style.FILL

        textPaint.strokeWidth = 5f
        textPaint.textSize = 30f
        textPaint.color = Color.GREEN
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText("L:CW,R:CW,WINDING", 100f, 50f, textPaint)
        path.reset()
        path.addCircle(200f, 200f, 120f, Path.Direction.CW)
        path.addCircle(400f, 200f, 120f, Path.Direction.CW)
        path.fillType = Path.FillType.WINDING
        canvas.drawPath(path, paint)

        canvas.drawText("L:CW,R:CW,EVEN_ODD", 600f, 50f, textPaint)
        path.reset()
        path.addCircle(700f, 200f, 120f, Path.Direction.CW)
        path.addCircle(900f, 200f, 120f, Path.Direction.CW)
        path.fillType = Path.FillType.EVEN_ODD
        canvas.drawPath(path, paint)

        canvas.drawText("L:CW,R:CCW,WINDING", 100f, 360f, textPaint)
        path.reset()
        path.addCircle(200f, 500f, 120f, Path.Direction.CW)
        path.addCircle(400f, 500f, 120f, Path.Direction.CCW)
        path.fillType = Path.FillType.WINDING
        canvas.drawPath(path, paint)

        canvas.drawText("L:CW,R:CCW,EVEN_ODD", 600f, 360f, textPaint)
        path.reset()
        path.addCircle(700f, 500f, 120f, Path.Direction.CW)
        path.addCircle(900f, 500f, 120f, Path.Direction.CCW)
        path.fillType = Path.FillType.EVEN_ODD
        canvas.drawPath(path, paint)
        canvas.drawPath(path, paint)

        canvas.drawText("O:CW,I:CW,WINDING", 100f, 660f, textPaint)
        path.reset()
        path.addCircle(300f, 800f, 120f, Path.Direction.CW)
        path.addCircle(300f, 800f, 100f, Path.Direction.CW)
        path.fillType = Path.FillType.WINDING
        canvas.drawPath(path, paint)

        canvas.drawText("O:CW,I:CW,EVEN_ODD", 600f, 660f, textPaint)
        path.reset()
        path.addCircle(800f, 800f, 120f, Path.Direction.CW)
        path.addCircle(800f, 800f, 100f, Path.Direction.CW)
        path.fillType = Path.FillType.EVEN_ODD
        canvas.drawPath(path, paint)

        canvas.drawText("O:CW,I:CCW,WINDING", 100f, 960f, textPaint)
        path.reset()
        path.addCircle(300f, 1100f, 120f, Path.Direction.CW)
        path.addCircle(300f, 1100f, 100f, Path.Direction.CCW)
        path.fillType = Path.FillType.WINDING
        canvas.drawPath(path, paint)

        canvas.drawText("O:CW,I:CCW,EVEN_ODD", 600f, 960f, textPaint)
        path.reset()
        path.addCircle(800f, 1100f, 120f, Path.Direction.CW)
        path.addCircle(800f, 1100f, 100f, Path.Direction.CCW)
        path.fillType = Path.FillType.EVEN_ODD
        canvas.drawPath(path, paint)
    }
}