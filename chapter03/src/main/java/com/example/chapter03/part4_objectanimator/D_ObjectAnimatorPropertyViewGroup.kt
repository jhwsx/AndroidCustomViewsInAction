package com.example.chapter03.part4_objectanimator

import android.animation.ObjectAnimator
import android.animation.PointFEvaluator
import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Property
import android.widget.Button
import android.widget.LinearLayout
import com.example.chapter03.R

/**
 * 传入 Property 的方式。
 * TODO 还会用到反射吗？
 * @author wangzhichao
 * @since 2022/6/17
 */
class D_ObjectAnimatorPropertyViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {
    private val myButton: Button

    init {
        inflate(context, R.layout.layout_object_animator_property_viewgroup, this)
        myButton = findViewById<Button>(R.id.mybutton)
        myButton.setOnClickListener {
            ObjectAnimator.ofObject(myButton, SIZE_PROPERTY, PointFEvaluator(), PointF(500f, 500f))
                .apply {
                    duration = 5000L
                    start()
                }
        }
    }

    private val SIZE_PROPERTY = object : Property<Button, PointF>(PointF::class.java,"layoutParams") {

        override fun set(obj: Button, value: PointF) {
            obj.layoutParams.apply {
                width = value.x.toInt()
                height = value.y.toInt()
            }
            obj.requestLayout()
        }

        override fun get(obj: Button): PointF {
            return PointF(obj.width.toFloat(), obj.height.toFloat())
        }

    }
}