package com.example.chapter13.part1

import android.content.ClipData
import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import com.example.chapter13.R
import com.example.common.dp
import com.google.android.flexbox.FlexboxLayout

/**
 *
 * @author wangzhichao
 * @since 2022/8/11
 */
class H_OnDragListenerCollectView(context: Context, attrs: AttributeSet?) :
    ConstraintLayout(context, attrs) {
    private lateinit var avatar: ImageView
    private lateinit var logo: ImageView
    private lateinit var flex: FlexboxLayout
    private val dragStarter = OnLongClickListener { v ->
        // ClipData 是可以跨进程传输的数据
        val clipData = ClipData.newPlainText("name", v.contentDescription)
        ViewCompat.startDragAndDrop(v, clipData, DragShadowBuilder(v), v, 0)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        avatar = findViewById(R.id.avatar)
        logo = findViewById(R.id.logo)
        flex = findViewById(R.id.flex)
        avatar.setOnLongClickListener(dragStarter)
        logo.setOnLongClickListener(dragStarter)
        flex.setOnDragListener(CollectListener())
    }

    private inner class CollectListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DROP -> if (v === flex) {
                    val clipData = event.clipData
                    val contentDescription = clipData.getItemAt(0).text
                    flex.addView(TextView(context).apply {
                        text = contentDescription
                        textSize = 12.dp
                    })
                }
            }
            return true
        }

    }
}