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
public class CLIP_SAVE_FLAG_View extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CLIP_SAVE_FLAG_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.RED);
        // 只保存画布的大小信息
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(100, 0, 200, 100);
        canvas.drawColor(Color.GREEN);
        // 恢复画布大小
        canvas.restore();

        canvas.drawColor(Color.YELLOW);
    }
}
