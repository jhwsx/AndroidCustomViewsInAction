package com.example.chapter03.part4_objectanimator

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.*
import com.example.chapter03.R
import java.util.ArrayList
import java.util.LinkedHashMap

/**
 * @author wangzhichao
 * @since 2021/5/13
 */
@SuppressLint("Recycle")
class A_ObjectAnimatorIntroViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val tv: TextView
    private val spinner: Spinner
    private var objectAnimator: ObjectAnimator? = null

    init {
        inflate(context, R.layout.m_object_animator_intro_viewgroup, this)
        tv = findViewById(R.id.tv)
        val btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        val map = LinkedHashMap<String, ObjectAnimator>()
        fillMap(map)
        val integers: Set<String> = map.keys
        val list: List<String> = ArrayList(integers)
        spinner = findViewById(R.id.spinner)
        spinner.adapter = ArrayAdapter(getContext(),
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            list)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val key = list[position]
                objectAnimator = map[key]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        btnStartAnim.setOnClickListener {
            objectAnimator!!.duration = 2000L
            objectAnimator!!.start()
        }
        tv.setOnClickListener {
            Toast.makeText(getContext(), "click me", Toast.LENGTH_SHORT).show()
        }
    }

    private fun fillMap(map: LinkedHashMap<String, ObjectAnimator>) {
        map["alpha"] = ObjectAnimator.ofFloat(tv, "alpha", 1f, 0f, 1f)
        map["rotationX"] = ObjectAnimator.ofFloat(tv, "rotationX", 0f, 270f, 0f)
        map["rotationY"] = ObjectAnimator.ofFloat(tv, "rotationY", 0f, 270f, 0f)
        map["rotation"] = ObjectAnimator.ofFloat(tv, "rotation", 0f, 270f, 0f)
        map["translationX"] = ObjectAnimator.ofFloat(tv, "translationX", 0f, 200f, -100f, 0f)
        map["translationY"] = ObjectAnimator.ofFloat(tv, "translationY", 0f, 200f, -100f, 0f)
        map["scaleX"] = ObjectAnimator.ofFloat(tv, "scaleX", 0f, 3f, 1f)
        map["scaleY"] = ObjectAnimator.ofFloat(tv, "scaleY", 0f, 3f, 1f)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (objectAnimator != null) {
            objectAnimator!!.cancel()
        }
    }
}