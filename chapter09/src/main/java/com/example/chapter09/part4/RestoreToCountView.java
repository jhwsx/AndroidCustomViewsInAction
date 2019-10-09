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
public class RestoreToCountView extends View {
    private static final String TAG = "RestoreToCountView";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public RestoreToCountView(Context context, @Nullable AttributeSet attrs) {
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
    }
}

/**
 * 总结：
 * 1，疑问：
 * 添加 setLayerType(View.LAYER_TYPE_SOFTWARE, null); 输出结果为：
 * RestoreToCountView: count: 3, id1 = 2
 * RestoreToCountView: count: 4, id2 = 3
 * RestoreToCountView: count: 5, id3 = 4
 * RestoreToCountView: count: 6, id4 = 5
 * 不添加 setLayerType(View.LAYER_TYPE_SOFTWARE, null); 输出结果为：
 * RestoreToCountView: count: 2, id1 = 1
 * RestoreToCountView: count: 3, id2 = 2
 * RestoreToCountView: count: 4, id3 = 3
 * RestoreToCountView: count: 5, id4 = 4
 * 这是为什么呢？
 * 2, 返回的 id 值表示当前保存的画布信息的栈层索引（从 0 开始），比如保存在第三层，则返回 2。
 * 注意到 count 总是比 id 大 1.
 * 3, canvas.getSaveCount() 获取到的是当前的栈的层数。
 */
