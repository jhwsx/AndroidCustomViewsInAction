package com.example.chapter09.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.graphics.withSave
import com.example.chapter09.R
import com.example.common.dp

/**
 * 增加点缀的 ImageView
 * 在 super.onDraw() 方法之后
 *
 * @author wangzhichao
 * @since 2022/6/13
 */
class C_DecoratedImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : AppCompatImageView(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#FFC107")
        textSize = 16.dp
    }
    private val fontMetrics = Paint.FontMetrics()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (drawable != null) {
            canvas.withSave {
                val bounds: Rect = drawable.bounds
                paint.getFontMetrics(fontMetrics)
                drawText(resources.getString(R.string.image_size,
                    bounds.width(),
                    bounds.height()), 5.dp, -fontMetrics.top, paint)
            }
        }
    }
}