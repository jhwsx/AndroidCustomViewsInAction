package com.example.chapter07.part5_lineargradient

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.chapter07.R

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
class B_ShimmerTextViewGroup(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private val text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer fringilla finibus neque, feugiat varius nisi consectetur ut. Quisque metus sapien, tincidunt sed eros vel, tincidunt lacinia nisl. In erat elit, dictum vel felis non, luctus scelerisque risus. Suspendisse potenti. Aliquam ultricies lorem id risus tristique lobortis. Etiam ut tempus augue. Mauris cursus libero at leo pharetra cursus. Etiam tortor libero, tempor ac imperdiet ut, hendrerit at augue."
    private val text2 = "Lorem ipsum dolor sit amet"
    init {
        inflate(context, R.layout.layout_shimmer_text_viewgroup, this)
        findViewById<B_ShimmerTextView>(R.id.stv).text = text
    }
}