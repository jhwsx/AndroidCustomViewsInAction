package com.example.chapter09.part4

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.chapter09.part4.A_RestoreToCountView
import com.example.chapter09.part4.B_RestoreToCountView2
import com.example.common.dp

/**
 * 演示：
 * 这个 saveCount 起始值是从1开始的，也就是说它比对应栈的索引要多 1。
 *
 * @author wangzhichao
 * @date 2019/10/09
 */
class A_RestoreToCountView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        Log.d(TAG, "count: " + canvas.saveCount + ", id0 = ?")
        val id1 = canvas.save()
        canvas.clipRect(0f, 0f, 300.dp, 300.dp)
        canvas.drawColor(Color.RED)
        Log.d(TAG, "count: " + canvas.saveCount + ", id1 = " + id1)

        val id2 =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), paint)
        canvas.clipRect(50.dp, 50.dp, 250.dp, 250.dp)
        canvas.drawColor(Color.GREEN)
        Log.d(TAG, "count: " + canvas.saveCount + ", id2 = " + id2)

        val id3 = canvas.saveLayerAlpha(0f,
            0f, width.toFloat(), height.toFloat(), 0xFF)
        canvas.clipRect(100.dp, 100.dp, 200.dp, 200.dp)
        canvas.drawColor(Color.YELLOW)
        Log.d(TAG, "count: " + canvas.saveCount + ", id3 = " + id3)

        val id4 = canvas.save();
        canvas.clipRect(130.dp, 130.dp, 170.dp, 170.dp)
        canvas.drawColor(Color.BLUE)
        Log.d(TAG, "count: " + canvas.saveCount + ", id4 = " + id4)
    }

    companion object {
        private const val TAG = "RestoreToCountView"
    }
}
/*
打印：
D/RestoreToCountView: count: 1, id0 = ?
D/RestoreToCountView: count: 2, id1 = 1
D/RestoreToCountView: count: 3, id2 = 2
D/RestoreToCountView: count: 4, id3 = 3
D/RestoreToCountView: count: 5, id4 = 4
 */