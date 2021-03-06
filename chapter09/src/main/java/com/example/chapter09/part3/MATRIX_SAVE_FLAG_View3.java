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
public class MATRIX_SAVE_FLAG_View3 extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MATRIX_SAVE_FLAG_View3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        // Canvas.HAS_ALPHA_LAYER_SAVE_FLAG，表示在新建的画布在合成到上一个画布上时，直接覆盖，不清空所在区域原图像
        canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.MATRIX_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        canvas.rotate(40);
        canvas.drawRect(100, 0, 200, 100, paint);
        // 恢复画布
        canvas.restore();

        paint.setColor(Color.YELLOW);
        canvas.drawRect(100, 0, 200, 100, paint);
    }
}

/**
 * 总结：
 * 当使用canvas.saveLayer(Canvas.MATRIX_SAVE_FLAG)时，需要与Canvas.HAS_ALPHA_LAYER_SAVE_FLAG一起使用，不然新建画布所在区域原来的图像将被清空。
 */
