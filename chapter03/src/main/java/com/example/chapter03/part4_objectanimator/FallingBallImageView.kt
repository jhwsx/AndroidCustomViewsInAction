package com.example.chapter03.part4_objectanimator

import android.content.Context
import android.graphics.Point
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 *
 * @author wangzhichao
 * @since 2022/6/15
 */
class FallingBallImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : AppCompatImageView(context, attrs) {

    var fallingPos: Point
        get() {
            return Point(0, 0)
        }
        set(value) {
//            layout(value.x, value.y, value.x + width, value.y + height)
            translationX = value.x.toFloat()
            translationY = value.y.toFloat()
        }
}