package com.example.chapter04.part2

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewPropertyAnimator
import android.widget.*
import androidx.appcompat.widget.AppCompatSpinner
import com.example.chapter04.R
import com.example.common.dp

/**
 * 演示 xxx 方法和 xxxBy 方法
 *
 * @author wangzhichao
 * @since 2022/6/22
 */
class B_ViewPropertyAnimatorXXXvsXXXBy @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {
    private val iv1: ImageView
    private val iv2: ImageView
    private val items = mutableListOf<String>()
    private val actions = mutableListOf<() -> Unit>()
    private var selectedAction: (() -> Unit)? = null
    private var initX1: Float = 0f
    private var initY1: Float = 0f
    private var initX2: Float = 0f
    private var initY2: Float = 0f
    private val spinner: AppCompatSpinner
    private val btnStartAnim: Button
    init {
        inflate(context, R.layout.layout_viewpropertyanimator_xxx_vs_xxxby_viewgroup, this)
        initItems()
        spinner = findViewById<AppCompatSpinner>(R.id.spinner)
        btnStartAnim = findViewById<Button>(R.id.btn_start_anim)
        iv1 = findViewById(R.id.iv1)
        iv2 = findViewById(R.id.iv2)
        spinner.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, items)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                selectedAction = actions[position]

                iv1.animate().reset(initX1, initY1)
                iv2.animate().reset(initX2, initY2)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        btnStartAnim.setOnClickListener { doAnimation() }
        post {
            initX1 = iv1.x
            initX2 = iv2.x
            initY1 = iv1.y
            initY2 = iv2.y
        }
    }

    /**
     * 重置方法
     */
    private fun ViewPropertyAnimator.reset(initX: Float, initY: Float) {
        alpha(1f)
        scaleY(1f)
        scaleX(1f)
        translationY(0f)
        translationX(0f)
        rotation(0f)
        rotationX(0f)
        rotationY(0f)
        x(initX)
        y(initY)
    }

    private fun initItems() {
        items.add("alpha vs alphaBy")
        actions.add {
            iv1.animate().alpha(0.3f).setDuration(300L)
            iv2.animate().alphaBy(0.3f).setDuration(300L)
        }
        items.add("scaleY vs scaleYBy")
        actions.add {
            iv1.animate().scaleY(1.2f).setDuration(300L)
            iv2.animate().scaleYBy(0.2f).setDuration(300L)
        }
        items.add("scaleX vs scaleXBy")
        actions.add {
            iv1.animate().scaleX(1.2f).setDuration(300L)
            iv2.animate().scaleXBy(0.2f).setDuration(300L)
        }
        items.add("translationY vs translationYBy")
        actions.add {
            iv1.animate().translationY(20.dp).setDuration(300L)
            iv2.animate().translationYBy(20.dp).setDuration(300L)
        }
        items.add("translationX vs translationXBy")
        actions.add {
            iv1.animate().translationX(20.dp).setDuration(300L)
            iv2.animate().translationXBy(20.dp).setDuration(300L)
        }
        items.add("rotation vs rotationBy")
        actions.add {
            iv1.animate().rotation(45f).setDuration(300L)
            iv2.animate().rotationBy(45f).setDuration(300L)
        }
        items.add("rotationX vs rotationXBy")
        actions.add {
            iv1.animate().rotationX(45f).setDuration(300L)
            iv2.animate().rotationXBy(45f).setDuration(300L)
        }
        items.add("rotationY vs rotationYBy")
        actions.add {
            iv1.animate().rotationY(45f).setDuration(300L)
            iv2.animate().rotationYBy(45f).setDuration(300L)
        }
        items.add("x vs xBy")
        actions.add {
            iv1.animate().x(100.dp).setDuration(300L)
            iv2.animate().xBy(20.dp).setDuration(300L)
        }
        items.add("y vs yBy")
        actions.add {
            iv1.animate().y(100.dp).setDuration(300L)
            iv2.animate().yBy(20.dp).setDuration(300L)
        }
    }

    private fun doAnimation() {
        selectedAction?.invoke()
    }
}