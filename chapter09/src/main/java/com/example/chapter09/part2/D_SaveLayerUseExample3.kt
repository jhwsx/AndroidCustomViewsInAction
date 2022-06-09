package com.example.chapter09.part2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter09.R
import com.example.common.dp

/**
 * saveLayer函数指定的矩形大小就是新建的画布大小.
 * @author wzc
 * @date 2019/10/5
 */
class D_SaveLayerUseExample3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }
    private val rect = RectF(50.dp, 50.dp, 200.dp, 200.dp)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 这里的作用是裁剪画布，虽然裁剪了，但是坐标系没有变化。
        canvas.clipRect(rect)
        canvas.drawColor(Color.GREEN)
        val layerId = canvas.saveLayer(0f, 0f, 100.dp, 100.dp, paint)
        // 画的颜色部分不在裁剪后的画布上，所以只可以看到一部分。
        canvas.drawColor(Color.BLUE)
        canvas.restoreToCount(layerId)
        val layerId2 = canvas.saveLayer(50.dp, 50.dp, 150.dp,  150.dp, paint)
        // 画的颜色在裁剪后的画布上，所以可以看见。
        canvas.drawARGB(100, 255, 0, 0)
        canvas.restoreToCount(layerId2)
    }

}