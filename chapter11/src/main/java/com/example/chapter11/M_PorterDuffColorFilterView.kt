package com.example.chapter11

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.common.ImageUtils
import com.example.common.dp

/**
 * PorterDuffColorFilter 作为一个 ColorFilter，只能指定一种颜色作为源，而不是一个 Bitmap。
 * @author wangzhichao
 * @since 2022/6/9
 */
class M_PorterDuffColorFilterView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val BITMAP_SIZE = 48.dp
    private val music = ImageUtils.decodeSampleBitmapFromResource(context.resources, R.drawable.music, BITMAP_SIZE.toInt())
    private val colorFilter1 = PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
    private val colorFilter2 = PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN)
    private val colorFilter3 = PorterDuffColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.colorFilter = null
        canvas.drawBitmap(music, 0f, 0f, paint)

        canvas.save()
        canvas.translate(BITMAP_SIZE, 0f)
        paint.colorFilter = colorFilter1
        canvas.drawBitmap(music, 0f, 0f, paint)
        paint.colorFilter = null
        canvas.restore()

        canvas.save()
        canvas.translate(BITMAP_SIZE * 2, 0f)
        paint.colorFilter = colorFilter2
        canvas.drawBitmap(music, 0f, 0f, paint)
        paint.colorFilter = null
        canvas.restore()

        canvas.save()
        canvas.translate(BITMAP_SIZE * 3, 0f)
        paint.colorFilter = colorFilter3
        canvas.drawBitmap(music, 0f, 0f, paint)
        paint.colorFilter = null
        canvas.restore()
    }
}