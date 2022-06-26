package com.example.chapter10.part1

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.chapter10.R

/**
 * @author wangzhichao
 * @date 2019/10/11
 */
class PathShapeViewGroup(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_path_shape_viewgroup, this)
        val pathShapeView = findViewById<PathShapeView>(R.id.pathshapeview)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val tv = findViewById<TextView>(R.id.tv)
        tv.text = button1.text
        button1.setOnClickListener {
            pathShapeView.updateStdWidthAndStdHeight(100, 100)
            tv.text = button1.text
        }
        button2.setOnClickListener {
            pathShapeView.updateStdWidthAndStdHeight(100, 200)
            tv.text = button2.text
        }
        button3.setOnClickListener {
            pathShapeView.updateStdWidthAndStdHeight(200, 100)
            tv.text = button3.text
        }
    }
}