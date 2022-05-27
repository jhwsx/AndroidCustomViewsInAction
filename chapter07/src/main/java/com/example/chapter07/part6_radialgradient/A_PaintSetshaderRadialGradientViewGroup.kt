package com.example.chapter07.part6_radialgradient

import android.content.Context
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.widget.*
import com.example.chapter07.R

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
class A_PaintSetshaderRadialGradientViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    companion object {
        private val TILE_MODES = arrayOf("CLAMP", "REPEAT", "MIRROR")
    }

    init {
        inflate(context, R.layout.layout_paint_setshader_radialgradient_viewgroup, this)
        val cbMultiColor = findViewById<CheckBox>(R.id.cb_multi_color)
        val cbDrawRect = findViewById<CheckBox>(R.id.cb_draw_rect)
        val cbSmallRect = findViewById<CheckBox>(R.id.cb_small_rect)
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = ArrayAdapter(context!!,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            TILE_MODES)
        val view = findViewById<A_PaintSetshaderRadialGradientView>(R.id.view)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
                var tileMode = Shader.TileMode.CLAMP
                when (position) {
                    0 -> tileMode = Shader.TileMode.CLAMP
                    1 -> tileMode = Shader.TileMode.REPEAT
                    2 -> tileMode = Shader.TileMode.MIRROR
                }
                view.tileMode = tileMode
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        cbMultiColor.setOnCheckedChangeListener { _, isChecked ->
            view.multiColor = isChecked
        }
        cbDrawRect.setOnCheckedChangeListener { _, isChecked ->
            view.drawRect = isChecked
            if (!isChecked) {
                cbSmallRect.isChecked = false
            }
            cbSmallRect.visibility = if (isChecked) VISIBLE else INVISIBLE
        }
        cbSmallRect.setOnCheckedChangeListener { _, isChecked ->
            view.smallRect = isChecked
        }
    }
}