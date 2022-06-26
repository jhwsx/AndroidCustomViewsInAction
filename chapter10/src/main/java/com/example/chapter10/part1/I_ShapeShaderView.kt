package com.example.chapter10.part1

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import com.example.chapter10.R
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/10/12
 */
class I_ShapeShaderView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable: ShapeDrawable = ShapeDrawable(RectShape())

    init {
        drawable.setBounds(50.dp.toInt(), 50.dp.toInt(), 300.dp.toInt(), 300.dp.toInt())
        val bitmap =
            ImageUtils.decodeSampleBitmapFromResource(resources, R.drawable.avatar, 200.dp.toInt())
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        // Shader 是从 ShapeDrawable 所在区域的左上角开始绘制的。
        // 而对于 View 来说，Shader 是从控件区域的左上角开始绘制的。
        // 这是为什么呢？这是因为在 ShapeDrawable 的 draw 方法里面对画布进行了平移。所以，总得来说，Shader 还是从画布的左上角开始绘制的。
        drawable.paint.shader = bitmapShader
        drawable.paint.colorFilter = LightingColorFilter(0xffffff, 0x300000)
        // TODO: 下面这三个函数的效果怎么看不到？
        // 设置默认高度
//        drawable.intrinsicHeight = 500.dp.toInt()
        // 设置默认宽度
//        drawable.intrinsicWidth = 400.dp.toInt()
        // 设置边距
//        drawable.setPadding(Rect(8.dp.toInt(), 48.dp.toInt(), 8.dp.toInt(), 16.dp.toInt()))
    }

    // 当前的 View 控件是充满整个页面的。
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.draw(canvas)
    }


}