package com.example.chapter07.part5_lineargradient

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter07.R

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
class A_PaintSetshaderLinearGradientView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var linearGradient: LinearGradient? = null
    private var type = Type.DOUBLE_COLOR
    private var coordinateX0 = Coordinate.ZERO
    private var coordinateY0 = Coordinate.ZERO
    private var coordinateX1 = Coordinate.ZERO
    private var coordinateY1 = Coordinate.ZERO

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.A_PaintSetshaderLinearGradientView)
        val index = typedArray.getInt(R.styleable.A_PaintSetshaderLinearGradientView_pslv_type, -1)
        if (index >= 0) {
            type = mapType(index)
        }
        val x0Coor = typedArray.getInt(R.styleable.A_PaintSetshaderLinearGradientView_pslv_x0, -1)
        if (x0Coor >= 0) {
            coordinateX0 = mapCoordinate(x0Coor)
        }
        val y0Coor = typedArray.getInt(R.styleable.A_PaintSetshaderLinearGradientView_pslv_y0, -1)
        if (y0Coor >= 0) {
            coordinateY0 = mapCoordinate(y0Coor)
        }
        val x1Coor = typedArray.getInt(R.styleable.A_PaintSetshaderLinearGradientView_pslv_x1, -1)
        if (x1Coor >= 0) {
            coordinateX1 = mapCoordinate(x1Coor)
        }
        val y1Coor = typedArray.getInt(R.styleable.A_PaintSetshaderLinearGradientView_pslv_y1, -1)
        if (y1Coor >= 0) {
            coordinateY1 = mapCoordinate(y1Coor)
        }
        typedArray.recycle()
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (linearGradient == null) {
            linearGradient =
                if (Type.DOUBLE_COLOR == type) {
                    LinearGradient(mapPx(coordinateX0),
                        mapPx(coordinateY0),
                        mapPx(coordinateX1),
                        mapPx(coordinateY1),
                        Color.parseColor("#FFFF0000"),
                        Color.parseColor("#FF00FF00"),
                        Shader.TileMode.CLAMP)
                } else {
                    val colors = intArrayOf(
                        Color.parseColor("#FFFF0000"),
                        Color.parseColor("#FF00FF00"),
                        Color.parseColor("#FF0000FF"),
                        Color.parseColor("#FFFFFF00"),
                        Color.parseColor("#FF00FFFF"),
                    )
                    val positions = floatArrayOf(0f, 0.2f, 0.4f, 0.6f, 1f)
                    LinearGradient(mapPx(coordinateX0),
                        mapPx(coordinateY0),
                        mapPx(coordinateX1),
                        mapPx(coordinateY1),
                        colors,
                        positions,
                        Shader.TileMode.CLAMP)
                }
        }
        paint.shader = linearGradient
        if (drawText) {
            paint.textSize = 50f
            canvas.drawText("欢迎光临willwaywang6的空间", 0f, 200f, paint)
            return
        }
        if (smallRect) {
            canvas.drawRect((width / 3).toFloat(),
                (height / 3).toFloat(),
                (width * 2 / 3).toFloat(),
                (height * 2 / 3).toFloat(),
                paint)
        } else {
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        }
    }

    private fun mapType(index: Int): Type {
        return when (index) {
            0 -> Type.DOUBLE_COLOR
            1 -> Type.MULTI_COLOR
            else -> Type.MULTI_COLOR
        }
    }

    private fun mapCoordinate(coor: Int): Coordinate {
        return when (coor) {
            0 -> Coordinate.ZERO
            1 -> Coordinate.HALF_WIDTH
            2 -> Coordinate.WIDTH
            3 -> Coordinate.HALF_HEIGHT
            4 -> Coordinate.HEIGHT
            else -> Coordinate.ZERO
        }
    }

    private fun mapPx(coordinate: Coordinate): Float {
        return when (coordinate) {
            Coordinate.ZERO -> 0f
            Coordinate.HALF_WIDTH -> (width / 2).toFloat()
            Coordinate.WIDTH -> width.toFloat()
            Coordinate.HALF_HEIGHT -> (height / 2).toFloat()
            Coordinate.HEIGHT -> height.toFloat()
            else -> 0f
        }
    }

    private var smallRect = false
    fun setSmallRect(smallRect: Boolean) {
        this.smallRect = smallRect
        invalidate()
    }

    private var drawText = false
    fun setDrawText(drawText: Boolean) {
        this.drawText = drawText
        invalidate()
    }

    internal enum class Coordinate(private val coordinate: Int) {
        ZERO(0), HALF_WIDTH(1), WIDTH(2), HALF_HEIGHT(3), HEIGHT(4);
    }

    internal enum class Type(private val type: Int) {
        DOUBLE_COLOR(0), MULTI_COLOR(1);
    }
}