package com.example.chapter09.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter09.R;


/**
 * saveLayer函数指定的矩形大小就是新建的画布大小.
 * @author wzc
 * @date 2019/10/4
 */
public class SaveLayerUseExample2 extends View {
    private Paint paint;
    private Bitmap bitmap;
    public SaveLayerUseExample2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        // 新建图层
        int layerId = canvas.saveLayer(0, 0, 200, 200, paint, Canvas.ALL_SAVE_FLAG);
        // 灰色区域就是新建画布的大小
        canvas.drawColor(Color.GRAY);
        // 即便后面绘制了更大的红色矩形，也只能显示出新建画布的区域
        paint.setAlpha(100);
        canvas.drawRect(0,0,500,600, paint);
        canvas.restoreToCount(layerId);
    }
}
