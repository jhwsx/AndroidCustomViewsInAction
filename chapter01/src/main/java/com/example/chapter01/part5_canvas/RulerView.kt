package com.example.chapter01.part5_canvas

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.example.chapter01.part5_canvas.CanvasRestoreToCountView
import com.example.chapter01.part5_canvas.CanvasScaleView
import com.example.chapter01.part5_canvas.ClipAnimView
import com.example.chapter01.part5_canvas.ClipPathAnimView

/**
 * 使用 Canvas.translate 来绘制一把简易的尺子。
 *
 * @author wangzhichao
 * @since 20-3-17
 */
class RulerView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val RULER_HEIGHT_DP = 50
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.BLACK
        paint.strokeWidth = 2f
        paint.style = Paint.Style.STROKE
        paint.textSize = 40f
        paint.textAlign = Paint.Align.CENTER
        // 绘制尺子的轮廓
        val rect = Rect(10, 10, width - 10, (10 + dp2px(RULER_HEIGHT_DP.toFloat())).toInt())
        canvas.drawRect(rect, paint)
        // 绘制刻度线
        val width = rect.width() - 20
        val count = 40
        val scale = width / count
        val startY = rect.bottom - 20
        for (i in 0..count) {
            if (i % 10 == 0) {
                // 画长线
                canvas.drawLine(20f, startY.toFloat(), 20f, startY - dp2px(15f), paint)
                val value = i / 10
                canvas.drawText(value.toString(), 20f, startY - dp2px(20f), paint)
            } else if (i % 5 == 0) {
                // 画中等长度的线
                canvas.drawLine(20f, startY.toFloat(), 20f, startY - dp2px(10f), paint)
            } else {
                // 画短线
                canvas.drawLine(20f, startY.toFloat(), 20f, startY - dp2px(5f), paint)
            }
            canvas.translate(scale.toFloat(), 0f)
        }
    }

    private fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
            Resources.getSystem().displayMetrics)
    }
}