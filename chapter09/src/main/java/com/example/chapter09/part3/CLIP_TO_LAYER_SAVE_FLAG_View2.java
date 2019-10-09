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
public class CLIP_TO_LAYER_SAVE_FLAG_View2 extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CLIP_TO_LAYER_SAVE_FLAG_View2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        // 调用这句代码时，Canvas 画板就被裁剪了，不仅影响了自己，而且还把view的原始画布给影响了，但是这里保存了裁剪区域
        canvas.saveLayer(0, 0, 200, 200, paint, Canvas.CLIP_SAVE_FLAG | Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        canvas.drawColor(Color.BLUE);
        // 恢复裁剪区域
        canvas.restore();

        canvas.drawColor(Color.YELLOW);
    }
}
