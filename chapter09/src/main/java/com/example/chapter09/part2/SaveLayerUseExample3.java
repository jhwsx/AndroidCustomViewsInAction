package com.example.chapter09.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter09.R;

import androidx.annotation.Nullable;

/**
 * saveLayer函数指定的矩形大小就是新建的画布大小.
 * @author wzc
 * @date 2019/10/5
 */
public class SaveLayerUseExample3 extends View {
    private Paint paint;
    private Bitmap bitmap;
    public SaveLayerUseExample3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);
        // 这里的作用是裁剪画布，虽然裁剪了，但是坐标系没有变化。
        canvas.clipRect(new Rect(100, bitmap.getHeight(), 200, bitmap.getHeight() + 100));
        canvas.drawColor(Color.GREEN);
        int layerId = canvas.saveLayer(0, 0, 200, 200, paint, Canvas.ALL_SAVE_FLAG);
        // 画的颜色不在裁剪后的画布上，所以是看不见的。
        canvas.drawColor(Color.GRAY);
        canvas.restoreToCount(layerId);
        int layerId2 = canvas.saveLayer(100, bitmap.getHeight(), 150, bitmap.getHeight() + 50, paint, Canvas.ALL_SAVE_FLAG);
        // 画的颜色在裁剪后的画布上，所以可以看见。
        canvas.drawARGB(100, 255, 0, 0);
        canvas.restoreToCount(layerId2);
    }
}
