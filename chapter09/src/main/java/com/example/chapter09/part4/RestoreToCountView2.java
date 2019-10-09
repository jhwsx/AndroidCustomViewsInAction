package com.example.chapter09.part4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * @author wangzhichao
 * @date 2019/10/09
 */
public class RestoreToCountView2 extends View {
    private static final String TAG = "RestoreToCountView2";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RestoreToCountView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "count: " + canvas.getSaveCount() + ", id0 = ?");

        int id1 = canvas.save();
        canvas.clipRect(0, 0, 300, 300);
        canvas.drawColor(Color.RED);
        Log.d(TAG, "count: " + canvas.getSaveCount() + ", id1 = " + id1);

        int id2 = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        canvas.clipRect(50, 50, 250, 250);
        canvas.drawColor(Color.GREEN);
        Log.d(TAG, "count: " + canvas.getSaveCount() + ", id2 = " + id2);

        int id3 = canvas.saveLayerAlpha(0, 0, getWidth(), getHeight(), 0xFF, Canvas.ALL_SAVE_FLAG);
        canvas.clipRect(100, 100, 200, 200);
        canvas.drawColor(Color.YELLOW);
        Log.d(TAG, "count: " + canvas.getSaveCount() + ", id3 = " + id3);

        int id4 = canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.clipRect(130, 130, 170, 170);
        canvas.drawColor(Color.BLUE);
        Log.d(TAG, "count: " + canvas.getSaveCount() + ", id4 = " + id4);
        // 这行代码的作用是把画布恢复到生成 id3 之前的状态，也就是说调用完这段代码后画布大小是（50,50,250,250）。这样再去绘制灰色，就会覆盖在绿色矩形的位置上。
        // 如果不写这句代码，直接绘制灰色，那么灰色只会覆盖蓝色的小矩形。
//        canvas.restoreToCount(id3);
        // 上面这行代码等价于下面两行代码
        //canvas.restore();
        //canvas.restore();

        canvas.drawColor(Color.GRAY);
        Log.d(TAG, "count: " + canvas.getSaveCount());
    }
}

/**
 * 总结：
 * 1, canvas.restoreToCount(int saveCount),注意这里的参数名字应该是 id 才对，这个方法的含义是将画布恢复到生成 id 之前的状态。
 * 2, canvas.restore() 的含义是把回退栈中的最上层画布状态出栈，恢复画布状态。
 */
