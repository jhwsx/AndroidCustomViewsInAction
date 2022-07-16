package com.example.chapter10.part2.section3

import android.content.Context
import android.text.TextPaint
import android.text.StaticLayout
import android.util.DisplayMetrics
import android.graphics.*
import android.text.Layout
import android.util.AttributeSet
import android.view.*
import com.example.common.dp
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * @author wangzhichao
 * @date 2019/10/22
 */
class A_DensityDpiView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
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
        // dpi: dot per inch, 屏幕像素密度，指每英寸上的像素点数。
        // densityDpi: 以 dpi 形式表示的 density，即 density * 160 的值了。
        val widthInch = displayMetrics.widthPixels / displayMetrics.xdpi
        val heightInch = displayMetrics.heightPixels / displayMetrics.ydpi
        val screenInch =
            sqrt(widthInch.toDouble().pow(2.0) + heightInch.toDouble().pow(2.0))
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