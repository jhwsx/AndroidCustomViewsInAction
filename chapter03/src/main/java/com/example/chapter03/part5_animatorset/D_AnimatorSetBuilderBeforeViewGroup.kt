package com.example.chapter03.part5_animatorset

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.chapter03.R

/**
 * before 函数传入的动画，在 play 函数传入的动画结束之后播放。
 *
 * @author wangzhichao
 * @since 2022/6/16
 */
class D_AnimatorSetBuilderBeforeViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {
    private val button: Button
    private val tv1: TextView
    private val tv2: TextView

    init {
        inflate(context, R.layout.layout_animatorset_builder_before, this)
        button = findViewById(R.id.button)
        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        button.setOnClickListener {
            val tv1BgAnimator = ObjectAnimator.ofInt(tv1, "backgroundColor",
                0x44ff0000, 0xff00ff00.toInt(), 0xff0000ff.toInt()).apply {
                duration = 1000L
            }
            val tv1TranslationX = ObjectAnimator.ofFloat(tv1, "translationX", 0f, 300f, 0f).apply {
                duration = 1000L
            }
            val tv2TranslationX = ObjectAnimator.ofFloat(tv2, "translationX", 0f, 400f, 0f).apply {
                duration = 1000L
            }
            val animatorSet = AnimatorSet()
            animatorSet.play(tv1BgAnimator)
                // 这两个 before 还是同时播放的。
                .before(tv1TranslationX).before(tv2TranslationX)
            animatorSet.start()
        }
    }
}