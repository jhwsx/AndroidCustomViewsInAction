package com.example.chapter09.part3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wangzhichao
 * @date 2019/10/09
 */
public class CLIP_SAVE_FLAG_View3 extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CLIP_SAVE_FLAG_View3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.RED);
        canvas.drawText("Hello World",50,200, paint);
        // 只保存画布的大小信息
        // 如果不加 Canvas.HAS_ALPHA_LAYER_SAVE_FLAG 这个 flag，那么新建画布所在区域的的内容就会被清掉。
        canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.CLIP_SAVE_FLAG | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        canvas.clipRect(100, 0, 200, 100);
        canvas.drawColor(Color.GREEN);
        // 恢复画布大小
        canvas.restore();

        canvas.drawColor(Color.YELLOW);
    }
}
