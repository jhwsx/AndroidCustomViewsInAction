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
 * @date 2019/10/08
 */
public class MATRIX_SAVE_FLAG_View4 extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MATRIX_SAVE_FLAG_View4(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);

        // Canvas.HAS_ALPHA_LAYER_SAVE_FLAG，表示在新建的画布在合成到上一个画布上时，直接覆盖，不清空所在区域原图像
        canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.MATRIX_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        canvas.clipRect(100, 0, 200, 100);
        // 恢复画布
        canvas.restore();

        canvas.drawColor(Color.YELLOW);
    }
}
