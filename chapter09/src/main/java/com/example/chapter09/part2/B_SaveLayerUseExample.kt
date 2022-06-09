package com.example.chapter09.part2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter09.R
import com.example.common.dp

/**
 * saveLayer函数会新建一个全新的画布，其后的所有动作都只对新建画布有效，对原画布没有影响。
 * @author wzc
 * @date 2019/10/4
 */
class B_SaveLayerUseExample(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        val layerId =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
        paint.color = Color.RED
        canvas.skew(1f, 0f)
        canvas.drawRect(0f, 0f, 150.dp, 150.dp, paint)
        paint.color = Color.YELLOW
        canvas.drawText("我在新画布上，你看我扭成啥了", 0f, 50f, paint)
        canvas.restoreToCount(layerId)

        paint.color = Color.GREEN
        canvas.drawText("我在原画布上，我不会歪斜。", 50f, 100f, paint)
    }


}