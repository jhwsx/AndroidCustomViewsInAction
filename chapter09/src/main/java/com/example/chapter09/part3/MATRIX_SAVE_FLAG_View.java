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
        // 只可以保存 Canvas 的位置矩阵
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        // translate, rotate, scale, skew 都是利用位置矩阵 matrix 实现的。
//        canvas.rotate(40);
//        canvas.translate(200,200);
//        canvas.scale(0.5f, 0.5f);
        canvas.skew(1.414f, 0f);
        canvas.drawRect(100, 0, 200, 100, paint);
        // 恢复画布
        canvas.restore();

        paint.setColor(Color.YELLOW);
        canvas.drawRect(100, 0, 200, 100, paint);
    }
}
/*
https://developer.android.com/sdk/api_diff/28/changes/android.graphics.Canvas
28 版本：
Removed Methods
int save(int)

Removed Fields
int CLIP_SAVE_FLAG
int CLIP_TO_LAYER_SAVE_FLAG
int FULL_COLOR_LAYER_SAVE_FLAG
int HAS_ALPHA_LAYER_SAVE_FLAG
int MATRIX_SAVE_FLAG
 */
