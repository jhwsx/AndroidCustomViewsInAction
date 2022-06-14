package com.example.chapter09.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.opengl.ETC1.getWidth
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlin.math.ceil

/**
 *
 * @author wangzhichao
 * @since 2022/6/13
 */
class E_OnDrawLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {
    private var pattern = Pattern()

    init {
        // 增加下面任意一行，都可以让 LinearLayout 调用 onDraw 方法。
//        setWillNotDraw(false)
        setBackgroundColor(Color.GRAY)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        pattern.draw(canvas)
    }

    private inner class Pattern(
        val spots: Array<Spot> = arrayOf(
            Spot(0.24f, 0.3f, 0.026f),
            Spot(0.69f, 0.25f, 0.067f),
            Spot(0.32f, 0.6f, 0.067f),
            Spot(0.62f, 0.78f, 0.083f),
        ),
    ) {
        private val patternPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#A0E91E63")
        }
        private val PATTERN_RATIO = 5f / 6

        fun draw(canvas: Canvas) {
            val repetition = ceil((width.toFloat() / height).toDouble()).toInt()
            for (i in 0 until spots.size * repetition) {
                val spot = spots[i % spots.size]
                if (repetition > 1) {
                    canvas.drawCircle(i / spots.size * height * PATTERN_RATIO + spot.relativeX * height,
                        spot.relativeY * height,
                        spot.relativeSize * height,
                        patternPaint)
                } else {
                    canvas.drawCircle(spot.relativeX * width,
                        spot.relativeY * height,
                        spot.relativeSize * height,
                        patternPaint)
                }

            }
        }
    }

    data class Spot(
        val relativeX: Float,
        val relativeY: Float,
        val relativeSize: Float,
    )
}