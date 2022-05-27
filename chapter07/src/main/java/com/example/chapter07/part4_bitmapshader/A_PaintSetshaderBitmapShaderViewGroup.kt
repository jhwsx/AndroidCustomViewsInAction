package com.example.chapter07.part4_bitmapshader

import android.content.Context
import android.graphics.Shader
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.*
import com.example.chapter07.R

/**
 * @author wangzhichao
 * @date 2019/09/21
 */
class A_PaintSetshaderBitmapShaderViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val spinnerTileX: Spinner
    private val spinnerTileY: Spinner
    private val tvTiltXDesc: TextView
    private val tvTiltYDesc: TextView
    private val v: A_PaintSetshaderBitmapShaderView
    private val cb: CheckBox
    private val spinnerItems = arrayOf("REPEAT", "MIRROR", "CLAMP")

    private fun mapPosition2TileMode(position: Int): Shader.TileMode {
        return when (position) {
            0 -> Shader.TileMode.REPEAT // 重复原图像来填充多余空间
            1 -> Shader.TileMode.MIRROR // 重复使用镜像模式的图像来填充多余空间
            2 -> Shader.TileMode.CLAMP // 用边缘色彩来填充多余空间
            // Android 31 新增了 Shader.TileMode.DECAL
            else -> Shader.TileMode.CLAMP
        }
    }

    private fun mapTileMode2String(tileMode: Shader.TileMode): String {
        return when (tileMode) {
            Shader.TileMode.REPEAT -> "重复原图像来填充多余空间"
            Shader.TileMode.MIRROR -> "重复使用镜像模式的图像来填充多余空间"
            Shader.TileMode.CLAMP -> "用边缘色彩来填充多余空间"
            else -> "用边缘色彩来填充多余空间"
        }
    }

    companion object {
        private val TAG = A_PaintSetshaderBitmapShaderViewGroup::class.java.simpleName
    }

    init {
        inflate(context, R.layout.layout_paint_setshader_bitmapshader_viewgroup, this)
        spinnerTileX = findViewById(R.id.spinnerTileX)
        spinnerTileY = findViewById(R.id.spinnerTileY)
        tvTiltXDesc = findViewById(R.id.tv_tiltx_desc)
        tvTiltYDesc = findViewById(R.id.tv_tilty_desc)
        tvTiltXDesc.text = mapTileMode2String(mapPosition2TileMode(0))
        tvTiltYDesc.text = mapTileMode2String(mapPosition2TileMode(0))
        cb = findViewById(R.id.cb)
        v = findViewById(R.id.view)
        val adapter = ArrayAdapter(
            context!!, android.R.layout.simple_list_item_1, android.R.id.text1, spinnerItems)
        spinnerTileX.adapter = adapter
        spinnerTileY.adapter = adapter
        spinnerTileX.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                Log.d(TAG, "spinnerTileX, onItemSelected: " + spinnerItems[position])
                v.setTileX(mapPosition2TileMode(position))
                tvTiltXDesc.text = mapTileMode2String(mapPosition2TileMode(position))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d(TAG, "spinnerTileX, onNothingSelected")
            }
        }
        spinnerTileY.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                Log.d(TAG, "spinnerTileY, onItemSelected: " + spinnerItems[position])
                v.setTileY(mapPosition2TileMode(position))
                tvTiltYDesc.text = mapTileMode2String(mapPosition2TileMode(position))
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d(TAG, "spinnerTileY, onNothingSelected")
            }
        }
        cb.setOnCheckedChangeListener { _, isChecked -> v.setSmallRect(isChecked) }
    }
}