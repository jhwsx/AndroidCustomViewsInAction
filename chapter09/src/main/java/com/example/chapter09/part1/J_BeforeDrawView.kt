package com.example.chapter09.part1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import com.example.common.dp

/**
 * 在 super.draw() 前面插入代码
 * @author wangzhichao
 * @since 2022/6/14
 */
class J_BeforeDrawView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    override fun draw(canvas: Canvas) {
        // 如果使用 android:background 的方式来设置绿色背景，相当于是把它的背景替换掉，从而会导致下面的那条横线消失。
        // 而缺少了 EditText 下面的横线，就觉得有点像 TextView 了，这不太好。
        canvas.drawColor(Color.parseColor("#66BB6A")); // 涂上绿色
        super.draw(canvas)
    }
}