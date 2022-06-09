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
 * 演示 restoreToCount 的使用，其实就是方便一次调用多次 restore()。
 *
 * @author wangzhichao
 * @date 2019/10/09
 */
class B_RestoreToCountView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
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
        Log.d(TAG, "count: " + canvas.saveCount + ", id4 = " + id4);
        // 这行代码的作用是把画布恢复到生成 id3 之前的状态，也就是说调用完这段代码后画布大小是（50,50,250,250）。这样再去绘制灰色，就会覆盖在绿色矩形的位置上。
        // 如果不写这句代码，直接绘制灰色，那么灰色只会覆盖蓝色的小矩形。
        canvas.restoreToCount(id3)
        // 上面这行代码等价于下面两行代码
        //canvas.restore()
        //canvas.restore()
        canvas.drawColor(Color.GRAY)
        Log.d(TAG, "count: " + canvas.saveCount)
    }

    companion object {
        private const val TAG = "RestoreToCountView2"
    }
}