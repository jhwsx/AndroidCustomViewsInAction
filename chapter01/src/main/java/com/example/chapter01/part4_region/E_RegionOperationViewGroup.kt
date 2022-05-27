package com.example.chapter01.part4_region

import android.content.Context
import android.graphics.Region
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.example.chapter01.R
import java.util.*

/**
 * @author wangzhichao
 * @since 20-3-16
 */
class E_RegionOperationViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) :
    LinearLayout(context, attrs) {
    // *     DIFFERENCE(0), // 差集
    // *     INTERSECT(1), // 交集
    // *     UNION(2), // 并集
    // *     XOR(3), // 异或
    // *     REVERSE_DIFFERENCE(4),
    // *     REPLACE(5); // 替换
    private val items: MutableList<Region.Op> = mutableListOf()
    private val itemStrs: MutableList<String> = ArrayList()

    init {
        Collections.addAll(items,
            Region.Op.DIFFERENCE,
            Region.Op.INTERSECT,
            Region.Op.UNION,
            Region.Op.XOR,
            Region.Op.REVERSE_DIFFERENCE,
            Region.Op.REPLACE)
    }

    init {
        inflate(context, R.layout.view_region_operation_viewgroup, this)
        for (i in items.indices) {
            val s = items[i].toString()
            Log.d("wzc", " s = $s")
            itemStrs.add(s)
        }
        val spinner = findViewById<Spinner>(R.id.spinner)
        spinner.adapter = ArrayAdapter(context,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            itemStrs)
        val regionOperationView = findViewById<E_RegionOperationView>(R.id.view)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                regionOperationView.currOp = items[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}