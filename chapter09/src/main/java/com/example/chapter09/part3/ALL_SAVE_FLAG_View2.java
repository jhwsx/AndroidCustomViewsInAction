package com.example.chapter09.part3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wangzhichao
 * @date 2019/10/09
 */
public class ALL_SAVE_FLAG_View2 extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public ALL_SAVE_FLAG_View2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
//        canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        // 这行代码和上面的一行是等价的
        canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        canvas.clipRect(0, 0, 300, 300);
        canvas.drawColor(Color.BLUE);
        canvas.skew(1.414f, 0);
        canvas.drawRect(100,0,200,100, paint);
        canvas.restore();

//        canvas.drawColor(Color.YELLOW);
    }
}
