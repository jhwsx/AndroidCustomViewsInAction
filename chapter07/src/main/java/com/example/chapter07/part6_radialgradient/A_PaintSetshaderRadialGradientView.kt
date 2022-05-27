package com.example.chapter07.part6_radialgradient

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
class A_PaintSetshaderRadialGradientView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val radius = 200f
    private var radialGradient: RadialGradient? = null

    var multiColor = false
        set(value) {
            field = value
            invalidate()
        }

    var drawRect = false
        set(value) {
            field = value
            invalidate()
        }

    var tileMode = Shader.TileMode.REPEAT
        set(value) {
            field = value
            invalidate()
        }

    var smallRect = false
        set(value) {
            field = value
            invalidate()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        Log.d(TAG, "onSizeChanged")
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        radialGradient = if (!multiColor) {
            RadialGradient(width / 2f, height / 2f, radius,
                Color.parseColor("#FFFF0000"),
                Color.parseColor("#FF00FF00"), tileMode)
        } else {
            val colors = intArrayOf(
                Color.parseColor("#FFFF0000"),
                Color.parseColor("#FF00FF00"),
                Color.parseColor("#FF0000FF"),
                Color.parseColor("#FF00FFFF"),
            )
            val stops = floatArrayOf(0f, 0.2f, 0.5f, 1f)
            RadialGradient(width / 2f, height / 2f, radius, colors, stops, tileMode)
        }
        paint.shader = radialGradient
        Log.d(TAG, "onDraw")
        if (drawRect) {
            if (smallRect) {
                canvas.drawRect((width / 4).toFloat(),
                    (height / 4).toFloat(),
                    (width / 2).toFloat(),
                    (height / 2).toFloat(),
                    paint)
            } else {
                canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
            }
        } else {
            canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)
        }
    }

    companion object {
        private const val TAG = "RadialGradientView"
    }
}