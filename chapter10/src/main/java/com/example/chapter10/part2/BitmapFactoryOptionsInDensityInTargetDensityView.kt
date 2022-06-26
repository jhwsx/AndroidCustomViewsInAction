package com.example.chapter10.part2

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
class BitmapFactoryOptionsInDensityInTargetDensityView(context: Context, attrs: AttributeSet?) :
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
        paint.textSize = 14.dp
        paint.color = Color.GREEN
        // 从 Drawable 读取是存在缩放的
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.scenery)
        sb.append("从 Drawable 里读取：drawableBmp_width:").append(bitmap.width)
            .append("\t").append("drawableBmp_height:").append(bitmap.height)
            .append("\t").append("内存：").append(bitmap.byteCount)
        sb.append("\n\n")
        val options = BitmapFactory.Options()
        options.inDensity = 1
        options.inTargetDensity = 2
        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.scenery, options)
        sb.append("从 Drawable 里读取，设置 inDensity=1，inTargetDensity=2：drawableBmp_width:")
            .append(bitmap1.width)
            .append("\t").append("drawableBmp_height:").append(bitmap1.height)
            .append("\t").append("内存：").append(bitmap1.byteCount)
        sb.append("\n\n")
        // 直接从文件中读取是不存在缩放的，原本是多大，读进来就是多大尺寸
        val externalStorageDirectory = Environment.getExternalStorageDirectory()
        val path = externalStorageDirectory.absoluteFile.toString() + File.separator + "scenery.png"
        val bmp = BitmapFactory.decodeFile(path)
        if (bmp == null) {
            Toast.makeText(context, "sd card 上没有 scenery.png", Toast.LENGTH_SHORT).show()
        } else {
            sb.append("fileBmp_width:").append(bmp!!.width)
                .append("\t").append("fileBmp_height:").append(bmp.height)
                .append("\t").append("内存：").append(bmp.byteCount)
            sb.append("\n\n")
            sb.append("----------结论--------------\n")
            sb.append("通过手动设置 inTargetDensity(真实显示的屏幕分辩率)和 inDensity（文件所在资源文件夹的分辩率），得出缩放比例" +
                    "scale = inTargetDensity/inDensity")
        }
    }
}