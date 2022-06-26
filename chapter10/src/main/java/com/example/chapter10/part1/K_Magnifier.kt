package com.example.chapter10.part1

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Magnifier
import androidx.annotation.RequiresApi
import com.example.chapter10.R
import com.example.common.dp

/**
 * @author wangzhichao
 * @since 2022/6/26
 */
@SuppressLint("ClickableViewAccessibility")
@RequiresApi(Build.VERSION_CODES.Q)
class K_Magnifier @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_magnifier, this)
        val iv: ImageView = findViewById(R.id.iv)
        val magnifier = Magnifier.Builder(iv)
            .setSize(2 * RADIUS, 2 * RADIUS)
            .setDefaultSourceToMagnifierOffset(0, 0)
            .setCornerRadius(RADIUS.toFloat())
            .setInitialZoom(FACTOR)
            .build()
        iv.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                    val position = IntArray(2)
                    v.getLocationOnScreen(position)
                    magnifier.show(
                        event.rawX - position[0],
                        event.rawY - position[1]
                    )
                }
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                    magnifier.dismiss()
                }
            }
            true
        }
    }

    companion object {
        // 放大倍数
        private const val FACTOR = 3f
        private val RADIUS = 40.dp.toInt()
    }
}