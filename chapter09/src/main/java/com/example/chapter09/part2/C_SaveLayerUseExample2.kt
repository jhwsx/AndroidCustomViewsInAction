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
 * @date 2019/10/4
 */
class C_SaveLayerUseExample2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        // 新建图层
        val layerId = canvas.saveLayer(0f, 0f, 150.dp, 150.dp, paint)
        // 灰色区域就是新建画布的大小
        canvas.drawColor(Color.GRAY)
        // 即便后面绘制了更大的红色矩形，也只能显示出新建画布的区域
        paint.alpha = 100
        canvas.drawRect(0f, 0f, 200.dp, 200.dp, paint)
        canvas.restoreToCount(layerId)
    }
}