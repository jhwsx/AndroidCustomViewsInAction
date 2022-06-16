package com.example.chapter03.part4_objectanimator

import android.animation.ObjectAnimator
import android.animation.PointFEvaluator
import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.chapter03.R

/**
 *
 * @author wangzhichao
 * @since 2022/6/16
 */
class C_ObjectAnimatorGetterViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    private val myButton: MyButton
    init {
        inflate(context, R.layout.layout_object_animator_getter_viewgroup, this)
        myButton  = findViewById<MyButton>(R.id.mybutton)
        myButton.setOnClickListener {
            ObjectAnimator.ofObject(myButton, "size", PointFEvaluator(), PointF(500f, 500f)).apply {
                duration = 5000L
                start()
            }
        }
    }
}