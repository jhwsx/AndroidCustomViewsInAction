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
public class CLIP_TO_LAYER_SAVE_FLAG_View extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public CLIP_TO_LAYER_SAVE_FLAG_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.RED);
        // 调用这句代码时，Canvas 画板就被裁剪了，不仅影响了自己，而且还把view的原始画布给影响了，
        // 虽然在调用了canvas.restore()，但最后一句再将原始画布填充为黄色，也可以看出，原始画布没有被恢复！
        canvas.saveLayer(0, 0, 200, 200, paint, Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        canvas.drawColor(Color.BLUE);
        // 即便调用了这行，也无法恢复
        canvas.restore();

        canvas.drawColor(Color.YELLOW);
    }
}
