package com.example.chapter09.part3

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @author wangzhichao
 * @date 2019/10/09
 */
class ALL_SAVE_FLAG_View(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.RED)
        canvas.save()
        // 这行代码和上面的一行是等价的
//        canvas.save(Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(0, 0, 300, 300)
        canvas.drawColor(Color.BLUE)
        canvas.skew(1.414f, 0f)
        canvas.drawRect(100f, 0f, 200f, 100f, paint)
        canvas.restore()
        canvas.drawColor(Color.YELLOW)
    }

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        paint.color = Color.GREEN
    }
}