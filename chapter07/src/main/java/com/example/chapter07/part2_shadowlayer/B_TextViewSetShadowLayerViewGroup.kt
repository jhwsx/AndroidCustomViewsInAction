package com.example.chapter07.part2_shadowlayer

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.example.chapter07.R

/**
 * 给TextVIew及其派生类的文字添加阴影
 * @author wangzhichao
 * @date 2019/09/20
 */
class B_TextViewSetShadowLayerViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_textview_setshadowlayer_viewgroup, this)
        val textView = findViewById<TextView>(R.id.text)
        textView.setShadowLayer(4f, 4f, 4f, Color.RED)
    }
}