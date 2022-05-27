package com.example.chapter07.part5_lineargradient

import android.content.Context
import android.util.AttributeSet
import android.widget.CheckBox
import android.widget.FrameLayout
import com.example.chapter07.R

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
class A_PaintSetshaderLinearGradientViewGroup(context: Context?, attrs: AttributeSet?) : FrameLayout(
    context!!, attrs) {
    private val pslv1: A_PaintSetshaderLinearGradientView
    private val pslv2: A_PaintSetshaderLinearGradientView
    private val pslv3: A_PaintSetshaderLinearGradientView
    private val pslv4: A_PaintSetshaderLinearGradientView

    init {
        inflate(context, R.layout.layout_paint_setshader_lineargradient_viewgroup, this)
        val cb = findViewById<CheckBox>(R.id.cb)
        pslv1 = findViewById(R.id.pslv1)
        pslv2 = findViewById(R.id.pslv2)
        pslv3 = findViewById(R.id.pslv3)
        pslv4 = findViewById(R.id.pslv4)
        pslv4.setDrawText(true)
        cb.setOnCheckedChangeListener { buttonView, isChecked ->
            pslv1.setSmallRect(isChecked)
            pslv2.setSmallRect(isChecked)
            pslv3.setSmallRect(isChecked)
        }
    }
}