package com.example.chapter01.part3_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Canvas drawPosText() 逐个指定文字位置
 * 这个方法已经废弃了
 *
 * public void drawPosText(@NonNull String text, @NonNull @Size(multiple = 2) float[] pos,
 * @NonNull Paint paint)
 * @since 20-3-11
 */
class E_CanvasDrawPosTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    init {
        paint.color = Color.RED
        paint.strokeWidth = 5f
        paint.style = Paint.Style.FILL
        paint.textSize = 60f
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val text = "床前明月光"
        val pos = floatArrayOf(
            16f, 100f,
            48f, 200f,
            80f, 300f,
            112f, 400f,
            144f, 500f,)
        canvas.drawPosText(text, pos, paint)
    }
}