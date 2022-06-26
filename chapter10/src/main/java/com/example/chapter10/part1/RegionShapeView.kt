package com.example.chapter10.part1

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/10/12
 */
class RegionShapeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable: ShapeDrawable
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.draw(canvas)
    }

    init {
        val vRect = Rect(50.dp.toInt(), 0, 90.dp.toInt(), 150.dp.toInt())
        val hRect = Rect(0, 50.dp.toInt(), 250.dp.toInt(), 100.dp.toInt())
        // 构建两个区域
        val vRegion = Region(vRect)
        val hRegion = Region(hRect)

        // 取两个区域的相交之外的区域
        vRegion.op(hRegion, Region.Op.XOR)
        val regionShape = RegionShape(vRegion)
        drawable = ShapeDrawable(regionShape)
        drawable.bounds = Rect(0,
            0,
            250.dp.toInt(),
            150.dp.toInt())
        drawable.paint.color = Color.YELLOW
    }
}