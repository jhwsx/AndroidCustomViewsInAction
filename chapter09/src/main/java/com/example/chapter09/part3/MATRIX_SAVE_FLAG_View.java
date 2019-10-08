package com.example.chapter09.part3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * https://developer.android.com/sdk/api_diff/28/changes/android.graphics.Canvas
 * @author wangzhichao
 * @date 2019/10/08
 */
public class MATRIX_SAVE_FLAG_View extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MATRIX_SAVE_FLAG_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.rotate(40);
        canvas.drawRect(100, 0, 200, 100, paint);
        canvas.restore();

        paint.setColor(Color.YELLOW);
//        canvas
    }
}
