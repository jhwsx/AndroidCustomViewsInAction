package com.example.chapter07.part4_bitmapshader

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.chapter07.R

/**
 * @author wangzhichao
 * @date 2019/09/21
 */
class C_CircleAvatarViewGroup(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.layout_circle_avatar_viewgroup, this)
    }
}