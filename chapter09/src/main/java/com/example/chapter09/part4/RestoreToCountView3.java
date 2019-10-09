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
 * 本例用于验证应用不同的FLAG时，都会保存在同一个栈中。
 *
 * @author wangzhichao
 * @date 2019/10/09
 */
public class RestoreToCountView3 extends View {
    private static final String TAG = "RestoreToCountView3";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public RestoreToCountView3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        Log.d(TAG, "count: " + canvas.getSaveCount());

        canvas.save(Canvas.ALL_SAVE_FLAG);
        Log.d(TAG, "count: " + canvas.getSaveCount());

        canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.CLIP_SAVE_FLAG);
        Log.d(TAG, "count: " + canvas.getSaveCount());

        canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.MATRIX_SAVE_FLAG);
        Log.d(TAG, "count: " + canvas.getSaveCount());

        canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        Log.d(TAG, "count: " + canvas.getSaveCount());

        canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        Log.d(TAG, "count: " + canvas.getSaveCount());

    }
}
