package com.example.chapter01.part3_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.chapter01.R

/**
 * Paint 的 setTypeface 方法，设置字体样式
 * setTypeface(Typeface typeface) 传入 null 就是清除之前的字体样式了。
 *
 * @author wangzhichao
 * @since 20-3-11
 */
class G_TextPaintSetTypefaceView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    init {
        paint.color = Color.RED
        paint.textSize = 64f
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val text = "床前明月光, How is it going?"
        canvas.drawText(text, 16f, 100f, paint)
        // 使用系统自带的字体样式
        paint.typeface = Typeface.SANS_SERIF
        canvas.drawText(text, 16f, 200f, paint)
        paint.typeface = Typeface.defaultFromStyle(Typeface.BOLD_ITALIC)
        canvas.drawText(text, 16f, 300f, paint)
        paint.typeface = Typeface.create("仿宋", Typeface.NORMAL)
        canvas.drawText(text, 16f, 400f, paint)
        paint.typeface = Typeface.createFromAsset(context.assets, "fonts/ff.ttf")
        canvas.drawText(text, 16f, 500f, paint)
        paint.typeface =
            Typeface.createFromAsset(context.assets, "fonts/Modak-Regular.ttf")
        canvas.drawText(text, 16f, 600f, paint)
        paint.typeface =
            ResourcesCompat.getFont(context, R.font.xingcao)
        canvas.drawText(text, 16f, 700f, paint)
    }
}