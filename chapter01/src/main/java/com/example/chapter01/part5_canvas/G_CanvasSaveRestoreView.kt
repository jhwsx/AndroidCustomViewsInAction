package com.example.chapter01.part5_canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * 为什么需要画布的保存和恢复操作？
 * 因为 translate，rotate，scale，skew，clipXXX 这些对画布的操作是不可逆的，
 * 为了在进行这些操作后，仍然可以拿到画布的原始状态，就需要在操作前保存画布的状态，在
 * 操作后恢复之前保存的画布状态。
 *
 * Canvas 的 save() 与 restore() 函数，执行保存与恢复
 * // Saves the current matrix and clip onto a private stack.
 * public int save()
 *
 *
 * This call balances a previous call to save(), and is used to remove all
 * modifications to the matrix/clip state since the last save call. It is
 * an error to call restore() more times than save() was called.
 * public void restore()
 *
 *
 * restore() 的次数比 save()多，就会抛出异常。
 * save() 的次数比 restore()多，测试了没有影响。
 * 这里用到的是栈的概念，后进先出。
 * save() 是入栈的操作，保存画布的状态；
 * restore() 是出栈的操作，取出位于栈顶的画布状态。
 *
 * @author wangzhichao
 * @since 20-3-18
 */
class G_CanvasSaveRestoreView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (step == 0 || step == 1 || step == 2) {
            // 绘制红色的背景
            canvas.drawColor(Color.RED)
        }
        if (step == 1 || step == 2) {
            // 保存当前画布，即整个屏幕
            canvas.save()
            // 裁剪出一个绿色的矩形
            canvas.clipRect(200, 200, 600, 600)
            canvas.drawColor(Color.GREEN)
            // 恢复整屏画布
            // 如果这里不调用 canvas.restore();, 那么画布区域就是绿色的矩形区域; 再调用
            // canvas.drawColor(Color.BLUE); 只是把绿色的区域变成了蓝色.
            canvas.restore()
        }
        if (step == 2) {
            // 再把背景绘制为蓝色
            canvas.drawColor(Color.BLUE)
        }
    }

    var step = 0
    var count = 0
    override fun onTouchEvent(event: MotionEvent): Boolean {
        count++
        step = count % 3
        invalidate()
        return super.onTouchEvent(event)
    }
}