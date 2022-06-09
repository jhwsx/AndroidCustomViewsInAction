package com.example.chapter09.part2

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.chapter09.R
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * saveLayerAlpha函数新建的画布是有透明度的.
 * @author wzc
 * @date 2019/10/4
 */
class E_SaveLayerAlphaUseExample(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
    }
    private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.dog)
    private val avatar = ImageUtils.decodeSampleBitmapFromResource(context.resources, R.drawable.avatar, 100.dp.toInt())
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        val layerId = canvas.saveLayerAlpha(0f, 0f, 200.dp, 200.dp, 100)
        // 新建画布上画的东西都是有透明度的。
        canvas.drawColor(Color.BLUE)
        canvas.drawBitmap(avatar, 0f, 0f, paint)
        canvas.restoreToCount(layerId)
    }
}