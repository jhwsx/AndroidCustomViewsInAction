package com.example.chapter01.part3_text

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.SpannableString
import android.util.AttributeSet
import android.view.View

/**
 * Canvas drawText() 绘制文本
 * // 指定String类型的文本，以及起始点坐标
 * public void drawText(@NonNull String text, float x, float y, Paint paint)
 * // 指定String类型的文本，起始点坐标，以及从文本中哪一个索引的字符开始绘制，到哪一个索引的字符为止，是左闭右开区间
 * public void drawText(@NonNull String text, int start, int end, float x, float y, Paint paint)
 * // 指定 CharSequence 类型的文本， 起始点坐标，以及从文本中哪一个索引的字符开始绘制，到哪一个索引的字符为止，是左闭右开区间
 * public void drawText(@NonNull CharSequence text, int start, int end, float x, float y, Paint paint)
 * // 指定字符数组类型的文本, 开始绘制的字符索引 是 index，count 是从指定索引开始绘制几个字符
 * public void drawText(@NonNull char[] text, int index, int count, float x, float y, Paint paint)
 *
 * @author wangzhichao
 * @since 20-3-11
 */
class D_CanvasDrawTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    init {
        paint.color = Color.RED
        paint.strokeWidth = 5f
        paint.style = Paint.Style.FILL
        paint.textSize = 60f
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val text = "床前明月光"
        // 床前明月光
        canvas.drawText(text, 16f, 100f, paint)
        // 明月，start = 2， end = 4
        canvas.drawText(text, 2, 4, 16f, 200f, paint)
        //
        val ss = SpannableString(text)
        canvas.drawText(ss, 0, ss.length, 16f, 300f, paint)
        val chars = text.toCharArray()
        canvas.drawText(chars, 0, 2, 16f, 400f, paint)
    }
}