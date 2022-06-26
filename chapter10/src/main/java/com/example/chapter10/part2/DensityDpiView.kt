package com.example.chapter10.part2

import android.content.Context
import android.text.TextPaint
import android.text.StaticLayout
import android.util.DisplayMetrics
import android.graphics.*
import android.text.Layout
import android.util.AttributeSet
import android.view.*
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/10/22
 */
class DensityDpiView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val info: String
    private val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val staticLayout =
            StaticLayout(info, paint, width, Layout.Alignment.ALIGN_CENTER, 1f, 0f, false)
        staticLayout.draw(canvas)
    }

    init {
        val displayMetrics = DisplayMetrics()
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthInch = displayMetrics.widthPixels / displayMetrics.xdpi
        val heightInch = displayMetrics.heightPixels / displayMetrics.ydpi
        val screenInch =
            Math.sqrt(Math.pow(widthInch.toDouble(), 2.0) + Math.pow(heightInch.toDouble(), 2.0))
                .toFloat()
        info = """
            density: ${displayMetrics.density}
            densityDpi: ${displayMetrics.densityDpi}
            heightPixels: ${displayMetrics.heightPixels}
            widthPixels: ${displayMetrics.widthPixels}
            xdpi: ${displayMetrics.xdpi}
            ydpi: ${displayMetrics.ydpi}
            widthInch: $widthInch
            heightInch: $heightInch
            screenInch: $screenInch
            """.trimIndent()
        paint.color = Color.GREEN
        paint.textSize = 24.dp
        paint.style = Paint.Style.FILL
    }
}