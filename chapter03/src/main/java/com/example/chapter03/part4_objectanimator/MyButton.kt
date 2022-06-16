package com.example.chapter03.part4_objectanimator

import android.content.Context
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

/**
 *
 * @author wangzhichao
 * @since 2022/6/16
 */
class MyButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : AppCompatButton(context, attrs) {

    var size: PointF
        get() {
            return PointF(width.toFloat(), height.toFloat())
        }
        set(value) {
            layoutParams.apply {
                width = value.x.toInt()
                height = value.y.toInt()
            }
            requestLayout()
        }
}