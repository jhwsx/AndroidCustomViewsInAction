package com.example.chapter01.part5_canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 多次调用 save 与 restore 演示
 * 栈中保存的是什么?
 * 栈中保存的是当前的画布状态.
 * 栈中取出的是什么?
 * 取出的是栈顶的画布状态, 并作为当前的画布状态.
 * @author wangzhichao
 * @since 20-3-18
 */
public class CanvasMultiSaveRestoreView extends View {
    public CanvasMultiSaveRestoreView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        // 保存画布大小为整个屏幕的状态到栈中
        canvas.save();
        canvas.clipRect(new Rect(100, 100, 800, 800));
        canvas.drawColor(Color.GREEN);

        // 保存画布大小为 Rect(100, 100, 800, 800) 区域的状态到栈中
        canvas.save();
        canvas.clipRect(new Rect(200, 200, 700, 700));
        canvas.drawColor(Color.BLUE);

        // 保存画布大小为 Rect(200, 200, 700, 700) 区域的状态到栈中
        canvas.save();
        canvas.clipRect(new Rect(300, 300, 600, 600));
        canvas.drawColor(Color.BLACK);

        // 保存画布大小为 Rect(300, 300, 600, 600) 区域的状态到栈中
        canvas.save();
        canvas.clipRect(new Rect(400, 400, 500, 500));
        canvas.drawColor(Color.WHITE);

        // 从栈中取出栈顶的画布状态,作为当前绘图的画布, 恢复画布大小为 Rect(300, 300, 600, 600) 区域
        if (restore >= 1) {
            canvas.restore();
        }

        // 恢复画布大小为 Rect(200, 200, 700, 700) 区域
        if (restore >= 2) {
            canvas.restore();
        }

        // 恢复画布大小为 Rect(100, 100, 800, 800) 区域
        if (restore >= 3) {
            canvas.restore();
        }

        // 恢复画布大小为整个屏幕
        if (restore >= 4) {
            canvas.restore();
        }

        canvas.drawColor(Color.YELLOW);


    }

    int restore;
    int count;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        restore = count % 5;
        count++;
        invalidate();
        return super.onTouchEvent(event);
    }

}
