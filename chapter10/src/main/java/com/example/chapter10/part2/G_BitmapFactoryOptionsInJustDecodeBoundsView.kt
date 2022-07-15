package com.example.chapter10.part2

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.chapter10.R

/**
 * @author wangzhichao
 * @date 2019/10/21
 */
class G_BitmapFactoryOptionsInJustDecodeBoundsView(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {
    init {
        val options = BitmapFactory.Options()
        // 以 in 开头的代表的就是设置某某参数，以 out 开头的代表的就是获取某某参数。
        // 设置为true，表示只解析图片信息，不获取图片，不分配内存。可以看到 bitmap 是 null，而 outXXXX 都是有值的。
        options.inJustDecodeBounds = true
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.book, options)
        Log.d("wzc", "bitmap = $bitmap") // null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.d("wzc", "realWidth: " + options.outWidth + ", realHeight: " +
                    options.outHeight + ", mimeType: " + options.outMimeType + ", config: " +
                    // outConfig 和 outColorSpace 是 Android O 以上的 api。
                    options.outConfig.toString() + ", colorSpace: " + options.outColorSpace)
        } else {
            Log.d("wzc", "realWidth: " + options.outWidth + ", realHeight: " +
                    options.outHeight + ", mimeType: " + options.outMimeType)
        }
    }
}