package com.example.chapter01.part6_widget_usemethod

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.chapter01.part6_widget_usemethod.CustomView

/**
 * 通过 xml 引入控件
 *
 * @author wangzhichao
 * @since 20-3-24
 */
class CustomView : View {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : super(context) {
        Log.d(TAG, "context constructor")
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        Log.d(TAG, "context attrs constructor")
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
        Log.d(TAG, "context attrs defStyleAttr constructor")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.color = Color.RED
        canvas.drawRect(0f, 0f, 200f, 100f, paint)
    }

    companion object {
        private val TAG = CustomView::class.java.simpleName
    }
}