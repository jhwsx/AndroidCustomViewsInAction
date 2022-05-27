package com.example.chapter01.part2_path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.ArrayList
import kotlin.math.cos
import kotlin.math.sin

/**
 * 蜘蛛网图
 *
 * @author wangzhichao
 * @since 20-3-8
 */
class K_SpiderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var path = Path()
    private val list: MutableList<Point> = ArrayList()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.translate((width / 2).toFloat(), (height / 2).toFloat())
        // 纬线数目
        val latitudeCount = 6
        // 经线数目
        val longtitudeCount = 6
        // 1,绘制网的纬线框
        paint.color = Color.GREEN
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        for (i in 1..latitudeCount) {
            path.reset()
            for (j in 0 until longtitudeCount) {
                val point = getPoint(i, j)
                if (j == 0) {
                    path.moveTo(point.x.toFloat(), point.y.toFloat())
                } else {
                    path.lineTo(point.x.toFloat(), point.y.toFloat())
                }
            }
            path.close()
            canvas.drawPath(path, paint)
        }
        // 2,绘制网的经线
        for (i in 0 until longtitudeCount) {
            val point = getPoint(latitudeCount, i)
            canvas.drawLine(0f, 0f, point.x.toFloat(), point.y.toFloat(), paint)
        }
        // 3,绘制点连成的区域
        list.clear()
        list.add(getPoint(1, 0))
        list.add(getPoint(5, 1))
        list.add(getPoint(1, 2))
        list.add(getPoint(6, 3))
        list.add(getPoint(4, 4))
        list.add(getPoint(5, 5))
        path.reset()
        for ((index, point) in list.withIndex()) {
            if (index == 0) {
                path.moveTo(point.x.toFloat(), point.y.toFloat())
            } else {
                path.lineTo(point.x.toFloat(), point.y.toFloat())
            }
        }
        path.close()
        paint.style = Paint.Style.FILL
        paint.color = Color.parseColor("#44ff0000")
        canvas.drawPath(path, paint)
        // 4,绘制点
        paint.color = Color.BLUE
        paint.strokeWidth = 20f
        paint.strokeCap = Paint.Cap.ROUND
        for (point in list) {
            canvas.drawPoint(point.x.toFloat(), point.y.toFloat(), paint)
        }
    }

    /**
     * latitudePosition：纬线位置 [0,6]
     * longitudePosition：经线位置 [0,5]
     */
    private fun getPoint(latitudePosition: Int, longitudePosition: Int): Point {
        val radius = latitudePosition * netLineSpace
        val x = (radius * cos(Math.toRadians((longitudePosition * 60).toDouble()))).toInt()
        val y = (radius * sin(Math.toRadians((longitudePosition * 60).toDouble()))).toInt()
        return Point(x, y)
    }

    companion object {
        private const val netLineSpace = 50
    }
}