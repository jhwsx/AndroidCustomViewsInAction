package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * Canvas 的 restoreToCount() 函数演示
 * Efficient way to pop any calls to save() that happened after the save
 * count reached saveCount. It is an error for saveCount to be less than 1.
 *
 *
 * Example:
 * int count = canvas.save();
 * ... // more calls potentially to save()
 * canvas.restoreToCount(count);
 * // now the canvas is back in the same state it was before the initial
 * // call to save().
 *
 *
 * saveCount The save level to restore to.
 *
 *
 * public void restoreToCount(int saveCount)
 * 需要注意的是，saveCount 不可以小于 1。
 *
 * @author wangzhichao
 * @since 20-3-18
 */
class CanvasRestoreToCountView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.RED)

        // 保存画布大小为整个屏幕
        val c1 = canvas.save()
        Log.d(TAG, "保存画布大小为整个屏幕 所在栈的索引： c1 = $c1")
        canvas.clipRect(Rect(100, 100, 800, 800))
        canvas.drawColor(Color.GREEN)

        // 保存画布大小为 Rect(100, 100, 800, 800) 区域
        val c2 = canvas.save()
        Log.d(TAG, "保存画布大小为 Rect(100, 100, 800, 800) 区域 所在栈的索引： c2 = $c2")
        canvas.clipRect(Rect(200, 200, 700, 700))
        canvas.drawColor(Color.BLUE)

        // 保存画布大小为 Rect(200, 200, 700, 700) 区域
        val c3 = canvas.save()
        Log.d(TAG, "保存画布大小为 Rect(200, 200, 700, 700) 区域 所在栈的索引： c3 = $c3")
        canvas.clipRect(Rect(300, 300, 600, 600))
        canvas.drawColor(Color.BLACK)

        // 保存画布大小为 Rect(300, 300, 600, 600) 区域
        val c4 = canvas.save()
        Log.d(TAG, "保存画布大小为 Rect(300, 300, 600, 600) 区域 所在栈的索引： c4 = $c4")
        canvas.clipRect(Rect(400, 400, 500, 500))
        canvas.drawColor(Color.WHITE)
        // 对比一下:
        // 想要把画布恢复到大小为 Rect(100, 100, 800, 800) 区域
        // 方式一: 多次调用 canvas.restore()
        // 恢复画布大小为 Rect(300, 300, 600, 600) 区域
//                        canvas.restore();

        // 恢复画布大小为 Rect(200, 200, 700, 700) 区域
//                    canvas.restore();

        // 恢复画布大小为 Rect(100, 100, 800, 800) 区域
//                canvas.restore();

        // 方式二: 使用 canvas.restoreToCount(int saveCount); 调用一次就够了,但是要知道 saveCount
        // 这种方式更加高效
        // 恢复画布大小为 Rect(100, 100, 800, 800) 区域
        canvas.restoreToCount(c2)
        canvas.drawColor(Color.YELLOW)
    }

    companion object {
        private val TAG = CanvasRestoreToCountView::class.java.simpleName
    }
}