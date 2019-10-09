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
public class CLIP_SAVE_FLAG_View2 extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CLIP_SAVE_FLAG_View2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GREEN);
        canvas.drawRect(100, 0, 200, 100, paint);

        // 只可以保存 Canvas 的大小
        canvas.save(Canvas.CLIP_SAVE_FLAG);
        // translate, rotate, scale, skew 都是利用位置矩阵 matrix 实现的。
        canvas.rotate(40);
//        canvas.translate(200,200);
//        canvas.scale(0.5f, 0.5f);
//        canvas.skew(1.414f, 0f);
        // 恢复画布大小
        canvas.restore();

        paint.setColor(Color.YELLOW);
        canvas.drawRect(100, 0, 200, 100, paint);
    }
}

/**
 * 总结：
 * 1，这个例子演示 CLIP_SAVE_FLAG 不可以保存位置矩阵，也就是说对于位置矩阵，它不会保存，也就谈不上恢复。
 */
