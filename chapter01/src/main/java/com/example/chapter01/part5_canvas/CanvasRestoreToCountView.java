package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Canvas 的 restoreToCount() 函数演示
 * Efficient way to pop any calls to save() that happened after the save
 * count reached saveCount. It is an error for saveCount to be less than 1.
 * <p>
 * Example:
 * int count = canvas.save();
 * ... // more calls potentially to save()
 * canvas.restoreToCount(count);
 * // now the canvas is back in the same state it was before the initial
 * // call to save().
 * <p>
 * saveCount The save level to restore to.
 * <p>
 * public void restoreToCount(int saveCount)
 * 需要注意的是，saveCount 不可以小于 1。
 *
 * @author wangzhichao
 * @since 20-3-18
 */
public class CanvasRestoreToCountView extends View {
    private static final String TAG = CanvasRestoreToCountView.class.getSimpleName();

    public CanvasRestoreToCountView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.RED);

        // 保存画布大小为整个屏幕
        int c1 = canvas.save();
        Log.d(TAG, "保存画布大小为整个屏幕 所在栈的索引： c1 = " + c1);
        canvas.clipRect(new Rect(100, 100, 800, 800));
        canvas.drawColor(Color.GREEN);

        // 保存画布大小为 Rect(100, 100, 800, 800) 区域
        int c2 = canvas.save();
        Log.d(TAG, "保存画布大小为 Rect(100, 100, 800, 800) 区域 所在栈的索引： c2 = " + c2);
        canvas.clipRect(new Rect(200, 200, 700, 700));
        canvas.drawColor(Color.BLUE);

        // 保存画布大小为 Rect(200, 200, 700, 700) 区域
        int c3 = canvas.save();
        Log.d(TAG, "保存画布大小为 Rect(200, 200, 700, 700) 区域 所在栈的索引： c3 = " + c3);

        canvas.clipRect(new Rect(300, 300, 600, 600));
        canvas.drawColor(Color.BLACK);

        // 保存画布大小为 Rect(300, 300, 600, 600) 区域
        int c4 = canvas.save();
        Log.d(TAG, "保存画布大小为 Rect(300, 300, 600, 600) 区域 所在栈的索引： c4 = " + c4);

        canvas.clipRect(new Rect(400, 400, 500, 500));
        canvas.drawColor(Color.WHITE);
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
        canvas.restoreToCount(c2);
        canvas.drawColor(Color.YELLOW);

    }
}
