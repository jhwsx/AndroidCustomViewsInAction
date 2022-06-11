package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

/**
 * 多次调用 save 与 restore 演示
 * 栈中保存的是什么?
 * 栈中保存的是当前的画布状态.
 * 栈中取出的是什么?
 * 取出的是栈顶的画布状态, 并作为当前的画布状态.
 * @author wangzhichao
 * @since 20-3-18
 */
class M_CanvasMultiSaveRestoreView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val rect = RectF()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.RED)
        // 1,保存画布大小为整个屏幕的状态到栈中
        canvas.save()
        rect.set(width * 0.1f, width * 0.1f, width * 0.9f, width * 0.9f)
        canvas.clipRect(rect)
        canvas.drawColor(Color.GREEN)

        // 2,保存画布大小为 Rect(width * 0.1f, width * 0.1f, width * 0.9f, width * 0.9f) 区域的状态到栈中
        canvas.save()
        rect.set(width * 0.2f, width * 0.2f, width * 0.8f, width * 0.8f)
        canvas.clipRect(rect)
        canvas.drawColor(Color.BLUE)

        // 3,保存画布大小为 Rect(width * 0.2f, width * 0.2f, width * 0.8f, width * 0.8f) 区域的状态到栈中
        canvas.save()
        rect.set(width * 0.3f, width * 0.3f, width * 0.7f, width * 0.7f)
        canvas.clipRect(rect)
        canvas.drawColor(Color.BLACK)

        // 4,保存画布大小为 Rect(width * 0.3f, width * 0.3f, width * 0.7f, width * 0.7f) 区域的状态到栈中
        canvas.save()
        rect.set(width * 0.4f, width * 0.4f, width * 0.6f, width * 0.6f)
        canvas.clipRect(rect)
        canvas.drawColor(Color.WHITE)

        // 从栈中取出栈顶的画布状态,作为当前绘图的画布, 恢复画布大小为 Rect(width * 0.3f, width * 0.3f, width * 0.7f, width * 0.7f) 区域
        if (restore == 1) {
            Toast.makeText(context, "恢复黑色区域画布", Toast.LENGTH_SHORT).show()
            canvas.restore()
        }

        // 恢复画布大小为 Rect(width * 0.2f, width * 0.2f, width * 0.8f, width * 0.8f) 区域
        if (restore == 2) {
            Toast.makeText(context, "恢复蓝色区域画布", Toast.LENGTH_SHORT).show()
            canvas.restore()
            canvas.restore()
        }

        // 恢复画布大小为 Rect(width * 0.1f, width * 0.1f, width * 0.9f, width * 0.9f) 区域
        if (restore == 3) {
            Toast.makeText(context, "恢复绿色区域画布", Toast.LENGTH_SHORT).show()
            canvas.restore()
            canvas.restore()
            canvas.restore()
        }

        // 恢复画布大小为整个屏幕
        if (restore == 4) {
            Toast.makeText(context, "恢复红色区域画布", Toast.LENGTH_SHORT).show()
            canvas.restore()
            canvas.restore()
            canvas.restore()
            canvas.restore()
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