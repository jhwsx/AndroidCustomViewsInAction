package com.example.chapter02.part1_view_animation_tag

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R

/**
 * rotate 标签动画文件放在 res/anim/ 下面
 * android:fromDegrees="0"
 * android:toDegrees="-650" // - 代表的是逆时针方向
 *
 * 在 rotate 中,
 * android:pivotY 和 android:pivotX 属性用于指定旋转中心点的坐标.
 * 而在 scale 中, 指定的是动画的开始位置.
 * @author wangzhichao
 * @since 20-3-25
 */
class ViewAnimationTagRotateViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.view_animation_tag_rotate_viewgroup, this)
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_start).setOnClickListener { v ->
            val animation = AnimationUtils.loadAnimation(v.context, R.anim.rotateanim)
            tv.startAnimation(animation)
        }
        val tvPivot50 = findViewById<TextView>(R.id.tv_pivot_50)
        findViewById<View>(R.id.btn_start_pivot_50).setOnClickListener { v ->
            val animation = AnimationUtils.loadAnimation(v.context, R.anim.rotateanim_pivot_50)
            tvPivot50.startAnimation(animation)
        }
        val tvPivot50Percent = findViewById<TextView>(R.id.tv_pivot_50_percent)
        findViewById<View>(R.id.btn_start_pivot_50_percent).setOnClickListener { v ->
            val animation =
                AnimationUtils.loadAnimation(v.context, R.anim.rotateanim_pivot_50_percent)
            tvPivot50Percent.startAnimation(animation)
        }
        val tvPivot50PercentP = findViewById<TextView>(R.id.tv_pivot_50_percent_p)
        findViewById<View>(R.id.btn_start_pivot_50_percent_p).setOnClickListener { v ->
            val animation =
                AnimationUtils.loadAnimation(v.context, R.anim.rotateanim_pivot_50_percent_p)
            tvPivot50PercentP.startAnimation(animation)
        }
    }
}