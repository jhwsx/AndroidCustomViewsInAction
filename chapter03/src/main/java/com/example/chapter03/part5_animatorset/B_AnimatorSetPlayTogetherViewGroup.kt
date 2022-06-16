package com.example.chapter03.part5_animatorset

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.chapter03.R

/**
 *
 * @author wangzhichao
 * @since 2022/6/16
 */
class B_AnimatorSetPlayTogetherViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {
    private val button: Button
    private val tv1: TextView
    private val tv2: TextView

    init {
        inflate(context, R.layout.layout_animatorset_playtogether, this)
        button = findViewById(R.id.button)
        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        button.setOnClickListener {
            val tv1BgAnimator = ObjectAnimator.ofInt(tv1, "backgroundColor",
                0x44ff0000, 0xff00ff00.toInt(), 0xff0000ff.toInt()).apply {
//                    repeatCount = ValueAnimator.INFINITE
            }
            val tv1TranslationX = ObjectAnimator.ofFloat(tv1, "translationX", 0f, 300f, 0f)
            val tv2TranslationX = ObjectAnimator.ofFloat(tv2, "translationX", 0f, 400f, 0f)
            AnimatorSet().apply {
                playTogether(tv1BgAnimator, tv1TranslationX, tv2TranslationX)
                duration = 1000L
                start()
            }
        }
    }
}