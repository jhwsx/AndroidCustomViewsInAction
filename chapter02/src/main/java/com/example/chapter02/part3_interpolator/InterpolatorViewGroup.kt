package com.example.chapter02.part3_interpolator

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter02.R
import java.util.ArrayList
import java.util.LinkedHashMap

/**
 * 插值器
 * AccelerateDecelerateInterpolator: 加速减速插值器
 * AccelerateInterpolator 加速插值器
 * LinearInterpolator 线性插值器
 * DecelerateInterpolator 减速插值器
 * BounceInterpolator 弹跳插值器, 模拟了物体自由落体后回弹的效果
 * AnticipateInterpolator 初值偏移插值器
 * OvershootInterpolator 结束偏移插值器
 * AnticipateOvershootInterpolator
 * CycleInterpolator 循环插值器
 * @author wangzhichao
 * @since 20-4-9
 */
class InterpolatorViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    private var interpolator: Interpolator? = AccelerateDecelerateInterpolator()

    init {
        inflate(context, R.layout.interpolator_viewgroup, this)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val map = LinkedHashMap<String, Interpolator>()
        map[resources.getString(R.string.title_interpolator_accelerate_decelerate)] =
            AccelerateDecelerateInterpolator()
        map[resources.getString(R.string.title_interpolator_accelerate)] = AccelerateInterpolator()
        map[resources.getString(R.string.title_interpolator_decelerate)] = DecelerateInterpolator()
        map[resources.getString(R.string.title_interpolator_linear)] = LinearInterpolator()
        map[resources.getString(R.string.title_interpolator_bounce)] = BounceInterpolator()
        map[resources.getString(R.string.title_interpolator_anticipate_tension_2)] =
            AnticipateInterpolator()
        map[resources.getString(R.string.title_interpolator_anticipate_tension_point5)] =
            AnticipateInterpolator(0.5f)
        map[resources.getString(R.string.title_interpolator_anticipate_tension_4)] =
            AnticipateInterpolator(4f)
        map[resources.getString(R.string.title_interpolator_overshoot_tension_2)] =
            OvershootInterpolator()
        map[resources.getString(R.string.title_interpolator_overshoot_tension_point5)] =
            OvershootInterpolator(0.5f)
        map[resources.getString(R.string.title_interpolator_overshoot_tension_4)] =
            OvershootInterpolator(4f)
        map[resources.getString(R.string.title_interpolator_anticipate_overshoot_tension_2)] =
            AnticipateOvershootInterpolator()
        map[resources.getString(R.string.title_interpolator_anticipate_overshoot_tension_point5)] =
            AnticipateOvershootInterpolator(0.5f)
        map[resources.getString(R.string.title_interpolator_anticipate_overshoot_tension_4)] =
            AnticipateOvershootInterpolator(4f)
        map[resources.getString(R.string.title_interpolator_cycle_cycles_1)] = CycleInterpolator(1f)
        map[resources.getString(R.string.title_interpolator_cycle_cycles_point5)] =
            CycleInterpolator(0.5f)
        map[resources.getString(R.string.title_interpolator_cycle_cycles_4)] = CycleInterpolator(4f)
        val integers: Set<String> = map.keys
        val list: List<String> = ArrayList(integers)
        spinner.adapter = ArrayAdapter(getContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            list)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val key = list[position]
                interpolator = map[key]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        val tv = findViewById<TextView>(R.id.tv)
        findViewById<View>(R.id.btn_translate).setOnClickListener {
            val animation =
                AnimationUtils.loadAnimation(getContext(), R.anim.translateanim_delta_50_percent)
            animation.interpolator = interpolator
            tv.startAnimation(animation)
        }
        findViewById<View>(R.id.btn_rotate).setOnClickListener {
            val animation =
                AnimationUtils.loadAnimation(getContext(), R.anim.rotateanim_pivot_50_percent)
            animation.interpolator = interpolator
            tv.startAnimation(animation)
        }
        findViewById<View>(R.id.btn_scale).setOnClickListener {
            val animation =
                AnimationUtils.loadAnimation(getContext(), R.anim.scaleanim_pivot_50_percent)
            animation.interpolator = interpolator
            tv.startAnimation(animation)
        }
        findViewById<View>(R.id.btn_alpha).setOnClickListener {
            val animation = AnimationUtils.loadAnimation(getContext(), R.anim.alphaanim)
            animation.interpolator = interpolator
            tv.startAnimation(animation)
        }
        findViewById<View>(R.id.btn_reset).setOnClickListener { tv.clearAnimation() }
    }
}