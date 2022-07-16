package com.example.chapter10.part2.section3

import android.content.Context
import com.example.chapter10.R
import android.text.TextPaint
import android.text.StaticLayout
import android.widget.Toast
import android.graphics.*
import android.os.Environment
import android.text.Layout
import android.util.AttributeSet
import android.view.*
import com.example.common.dp
import java.io.File
import java.lang.StringBuilder

/**
 * @author wangzhichao
 * @date 2019/10/23
 */
class B_SDCardBitmapDrawableBitmapView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    var sb = StringBuilder()
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
        // 从 Drawable 读取是存在缩放的
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
        sb.append("drawableBmp_width:").append(bitmap.width)
            .append("\t").append("drawableBmp_height:").append(bitmap.height)
            .append("\t").append("内存：").append(bitmap.byteCount)
        sb.append("\n\n")
        // 直接从文件中读取是不存在缩放的，原本是多大，读进来就是多大尺寸
        val path = File( context.getExternalFilesDir(null), "scenery.png")
        val bmp = BitmapFactory.decodeFile(path.absolutePath)
        if (bmp == null) {
            sb.append("sd card 应用私有目录上没有 scenery.png")
        } else {
            sb.append("fileBmp_width:").append(bmp.width)
                .append("\t").append("fileBmp_height:").append(bmp.height)
                .append("\t").append("内存：").append(bmp.byteCount)
            sb.append("\n\n")
            sb.append("----------结论--------------\n")
            sb.append("1,从 Drawable 读取是存在缩放的;2,直接从文件中读取是不存在缩放的，原本是多大，读进来就是多大尺寸")
        }

    }
}