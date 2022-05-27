package com.example.chapter06.part2_text

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.common.dp

/**
 * 获取绘制字符串所占区域的高度，宽度和最小矩形。
 * @author wzc
 * @date 2019/9/10
 */
class B_TextAreaView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private val fm: Paint.FontMetrics

    // 最小矩形,左右两端若有空格是不包括在内的。
    private val minRect = Rect()
    private val area = RectF()
    private val actualMinRect = RectF()
    private val fontMetrics = Paint.FontMetrics()
    init {
        textPaint.textSize = 48.dp
        fm = textPaint.fontMetrics
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val startX = 10f
        var baseLine = 200f
        val text = "    willwaywang6"
        canvas.drawText(text, startX, baseLine, textPaint)

        // 绘制文字所占矩形区域的框
        paint.style = Paint.Style.STROKE
        val top = baseLine + fm.top
        val bottom = baseLine + fm.bottom
        val height = bottom - top
        Log.d(TAG, "所占区域的高度：$height")
        val width = textPaint.measureText(text)
        Log.d(TAG, "所占区域的宽度：$width")
        val areaLeft = 10f
        val areaRight = areaLeft + width
        val areaBottom = top + height
        area.set(areaLeft, top, areaRight, areaBottom)
        paint.color = Color.RED
        canvas.drawRect(area, paint)

        // 注意：获取指定字符串所对应的的最小矩形，以（0,0）点为隐含的文字原点
        textPaint.getTextBounds(text, 0, text.length, minRect)
        Log.e(TAG, "最小矩形：" + minRect.toShortString())
        paint.color = Color.BLUE
        canvas.drawRect(minRect, paint)
        // 转换对应于实际的文字原点的最小矩形
        actualMinRect.left = startX + minRect.left.toFloat()
        actualMinRect.top = baseLine + minRect.top
        actualMinRect.right = startX + minRect.right.toFloat()
        actualMinRect.bottom = baseLine + minRect.bottom
        Log.e(TAG, "实际的最小矩形：" + actualMinRect.toShortString())
        paint.color = Color.GREEN
        canvas.drawRect(actualMinRect, paint)

        // 给文字添加最小背景
        textPaint.textSize = 24.dp
        canvas.drawText("给文字添加最小背景:", startX, 350f, textPaint)
        val greetings = "How's it going?"
        baseLine = 500f
        textPaint.textSize = 48.dp
        textPaint.getTextBounds(greetings, 0, greetings.length, minRect)
        actualMinRect.left = startX + minRect.left.toFloat()
        actualMinRect.top = baseLine + minRect.top
        actualMinRect.right = startX + minRect.right.toFloat()
        actualMinRect.bottom = baseLine + minRect.bottom
        paint.color = Color.parseColor("#44ff0000")
        paint.style = Paint.Style.FILL
        canvas.drawRect(actualMinRect, paint)
        canvas.drawText(greetings, startX, baseLine, textPaint)

        // 文字靠边
        val leftTop = "leftTop"
        val rightBottom = "rightBottom"
        textPaint.textSize = 36.dp
        area.set(50f, 600f, getWidth() - 50f, 800f)
        canvas.drawRect(area, paint)
        textPaint.getFontMetrics(fontMetrics)
        textPaint.getTextBounds(leftTop, 0, leftTop.length, minRect)
        canvas.drawText(leftTop, area.left - minRect.left, area.top - fontMetrics.top,textPaint)

        textPaint.getTextBounds(rightBottom, 0, rightBottom.length, minRect)
        canvas.drawText(rightBottom, area.right - minRect.width() - minRect.left, area.bottom - fontMetrics.bottom, textPaint)

        area.set(50f, 900f, getWidth() - 50f, 1100f)
        canvas.drawRect(area, paint)
        textPaint.getFontMetrics(fontMetrics)
        textPaint.getTextBounds(leftTop, 0, leftTop.length, minRect)
        canvas.drawText(leftTop, area.left - minRect.left, area.top - minRect.top,textPaint)
        textPaint.getTextBounds(rightBottom, 0, rightBottom.length, minRect)
        canvas.drawText(rightBottom, area.right - minRect.width() - minRect.left, area.bottom - minRect.bottom, textPaint)
    }

    companion object {
        private const val TAG = "TextAreaView"
    }
}