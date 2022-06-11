package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

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
class N_CanvasRestoreToCountView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val rect = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.RED)
        // 1,保存画布大小为整个屏幕的状态到栈中
        val saveCount1 = canvas.save()
        rect.set(width * 0.1f, width * 0.1f, width * 0.9f, width * 0.9f)
        canvas.clipRect(rect)
        canvas.drawColor(Color.GREEN)

        // 2,保存画布大小为 Rect(width * 0.1f, width * 0.1f, width * 0.9f, width * 0.9f) 区域的状态到栈中
        val saveCount2 = canvas.save()
        rect.set(width * 0.2f, width * 0.2f, width * 0.8f, width * 0.8f)
        canvas.clipRect(rect)
        canvas.drawColor(Color.BLUE)

        // 3,保存画布大小为 Rect(width * 0.2f, width * 0.2f, width * 0.8f, width * 0.8f) 区域的状态到栈中
        val saveCount3 = canvas.save()
        rect.set(width * 0.3f, width * 0.3f, width * 0.7f, width * 0.7f)
        canvas.clipRect(rect)
        canvas.drawColor(Color.BLACK)

        // 4,保存画布大小为 Rect(width * 0.3f, width * 0.3f, width * 0.7f, width * 0.7f) 区域的状态到栈中
        val saveCount4 = canvas.save()
        rect.set(width * 0.4f, width * 0.4f, width * 0.6f, width * 0.6f)
        canvas.clipRect(rect)
        canvas.drawColor(Color.WHITE)

        // 从栈中取出栈顶的画布状态,作为当前绘图的画布, 恢复画布大小为 Rect(width * 0.3f, width * 0.3f, width * 0.7f, width * 0.7f) 区域
        if (restore == 1) {
            Toast.makeText(context, "恢复黑色区域画布", Toast.LENGTH_SHORT).show()
            canvas.restoreToCount(saveCount4)
        }

        // 恢复画布大小为 Rect(width * 0.2f, width * 0.2f, width * 0.8f, width * 0.8f) 区域
        if (restore == 2) {
            Toast.makeText(context, "恢复蓝色区域画布", Toast.LENGTH_SHORT).show()
            canvas.restoreToCount(saveCount3)
        }

        // 恢复画布大小为 Rect(width * 0.1f, width * 0.1f, width * 0.9f, width * 0.9f) 区域
        if (restore == 3) {
            Toast.makeText(context, "恢复绿色区域画布", Toast.LENGTH_SHORT).show()
            canvas.restoreToCount(saveCount2)
        }

        // 恢复画布大小为整个屏幕
        if (restore == 4) {
            Toast.makeText(context, "恢复红色区域画布", Toast.LENGTH_SHORT).show()
            canvas.restoreToCount(saveCount1)
        }
        canvas.drawColor(Color.YELLOW)
    }

    var restore = 0
    var count = 0
    override fun onTouchEvent(event: MotionEvent): Boolean {
        restore = count % 5
        count++
        invalidate()
        return super.onTouchEvent(event)
    }
}