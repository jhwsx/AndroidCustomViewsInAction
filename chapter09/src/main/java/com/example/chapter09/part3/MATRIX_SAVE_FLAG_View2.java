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
public class MATRIX_SAVE_FLAG_View2 extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MATRIX_SAVE_FLAG_View2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.GREEN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 只可以保存 Canvas 的位置矩阵
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        // 不可以保存画布裁剪
        canvas.clipRect(100, 0, 200, 100);
        canvas.drawColor(Color.GREEN);
        // 恢复画布
        canvas.restore();

        canvas.drawColor(Color.YELLOW);
    }
}

/**
 * 总结：
 * 1，演示 canvas.save(Canvas.MATRIX_SAVE_FLAG);： 只保存了 Canvas 的位置，也只恢复 Canvas 的位置；
 * 没有保存 Canvas 的大小，当然也不会恢复 Canvas 的大小。
 * 2，在4.4上和7.0上，是可以达到预期的，画布的大小是不可以恢复的；但是，在6.0上和8.0上，效果却是全部屏幕是黄色的。也就是说，在6.0和8.0上，
 * 画布的大小也恢复了。
 */

