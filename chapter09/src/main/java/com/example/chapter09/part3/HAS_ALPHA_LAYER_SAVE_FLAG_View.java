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
public class HAS_ALPHA_LAYER_SAVE_FLAG_View extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public HAS_ALPHA_LAYER_SAVE_FLAG_View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制红色背景
        canvas.drawColor(Color.RED);

        // 新建图层
        canvas.saveLayer(0, 0, 200, 200, paint, Canvas.HAS_ALPHA_LAYER_SAVE_FLAG);
        canvas.drawRect(50,50,150,150, paint);
        // 恢复图层
        canvas.restore();
    }
}

/**
 * 总结：
 * 1，HAS_ALPHA_LAYER_SAVE_FLAG 的作用是新建的画布在与上一个画布合成时，不会先将上一层画布对应区域清空，直接把新建画布盖上去。
 */
