package com.example.chapter10.part2

import android.content.Context
import com.example.chapter10.R
import android.text.TextPaint
import android.text.StaticLayout
import android.graphics.*
import android.text.Layout
import android.util.AttributeSet
import android.view.*
import com.example.common.dp
import java.lang.StringBuilder

/**
 * @author wangzhichao
 * @date 2019/10/23
 */
class BitmapFactoryOptionsInScaledView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    private val sb = StringBuilder()
    var paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val staticLayout =
            StaticLayout(sb.toString(), paint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false)
        staticLayout.draw(canvas)
    }

    init {
        paint.textSize = 20.dp
        paint.color = Color.GREEN
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
        sb.append("inScaled 默认设置：").append("width: ").append(bitmap.width)
            .append("\t").append("height: ").append(bitmap.height)
            .append("\t").append("内存：").append(bitmap.byteCount)
        sb.append("\n")
        val options = BitmapFactory.Options()
        options.inScaled = true
        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.scenery, options)
        sb.append("inScaled = true：").append("width: ").append(bitmap1.width)
            .append("\t").append("height: ").append(bitmap1.height)
            .append("\t").append("内存：").append(bitmap1.byteCount)
        sb.append("\n")
        val options2 = BitmapFactory.Options()
        options2.inScaled = false
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.scenery, options2)
        sb.append("inScaled = false：").append("width: ").append(bitmap2.width)
            .append("\t").append("height: ").append(bitmap2.height)
            .append("\t").append("内存：").append(bitmap2.byteCount)
        sb.append("\n\n")
        sb.append("---------------结论--------------------")
        sb.append("\n")
        sb.append("inScaled 参数表示在需要缩放时，是否对当前文件进行缩放" +
                "1,inScaled 参数默认设置是 true；" +
                "2，当 inScaled 参数为 true 时，图片会被缩放；" +
                "3，当 inScaled 参数为 false 时，图片不会被缩放，也就是保持原本的大小")
    }
}