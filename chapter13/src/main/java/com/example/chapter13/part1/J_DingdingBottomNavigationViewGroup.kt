package com.example.chapter13.part1

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.chapter13.R

/**
 *
 * @author wangzhichao
 * @since 2022/8/12
 */
class J_DingdingBottomNavigationViewGroup(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
        init {
            inflate(context, R.layout.practice_dingding_bottomnavigationviewgroup, this)
            findViewById<J_DingdingBottomNavigationView>(R.id.bike).setOnClickListener {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
            }
            findViewById<J_DingdingBottomNavigationView>(R.id.plane).setOnClickListener {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
            }
        }
}