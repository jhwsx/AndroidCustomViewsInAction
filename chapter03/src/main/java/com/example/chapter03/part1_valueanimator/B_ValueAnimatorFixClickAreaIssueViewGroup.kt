package com.example.chapter03.part1_valueanimator

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.chapter03.R

/**
 * 使用 ValueAnimator 来解决 TweenAnimation 的点击区域问题
 *
 *
 * 注意: ValueAnimator 只对值进行动画计算, 而不是针对控件的.
 * 我们可以监听值的运算过程,然后根据这些值来对控件进行动画操作.
 *
 * 控件不仅显示在新的位置, 而且在新的位置上是可以响应点击事件的, 在原来的位置上不可以响应点击事件.
 *
 * @author wangzhichao
 * @date 7/15/20
 */
class B_ValueAnimatorFixClickAreaIssueViewGroup(context: Context, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView

    init {
        inflate(context, R.layout.layout_value_animator_fix_click_area_issue_viewgroup, this)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        tv = findViewById(R.id.tv)
        btnStartAnim.setOnClickListener { doAnimation() }
        tv.setOnClickListener {
            Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show()
        }
    }

    private fun doAnimation() {
        val width = tv.width
        val height = tv.height
        val top = tv.top
        val left = tv.left
        Log.d(TAG, "doAnimation: width=$width, height=$height")
        ValueAnimator.ofInt(0, width).apply {
            duration = 2000L
            interpolator = LinearInterpolator()
            addUpdateListener { animator ->
                val fraction = animator.animatedFraction
                val currValue = animator.animatedValue as Int
                // fraction 会从0.0变化到1.0，currValue会从0变化到width。
                Log.d(TAG, "onAnimationUpdate: fraction=$fraction,currValue=$currValue")
                // 这两种方式有什么区别呢？
                // x = left + translationX, y = top + translationY
                // 方式一：改变的是 translationX 和 translationY，而不改变 left，top，从而改变 x，y。
                // 方式二：改变的是 left 和 top，而不改变 translationX 和 translationY，从而改变 x 和 y。
                // 方式一：
                tv.translationX = currValue.toFloat()
                tv.translationY = 2f * currValue
                // 方式二：
//                tv.layout(left + currValue,
//                    top + 2 * currValue,
//                    left + currValue + width,
//                    top + 2 * currValue + height)
                Log.d(TAG, "onAnimationUpdate: left=" + tv.left + ", top=" + tv.top)
            }
            start()
        }
    }

    companion object {
        private const val TAG = "ValueAnimatorFixClickAr"
    }
}