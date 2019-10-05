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
        canvas.clipRect(new Rect(100, bitmap.getHeight(), 200, bitmap.getHeight() + 100));
        canvas.drawColor(Color.GREEN);
//        int layerId = canvas.saveLayer(0, 0, 200, 200, paint, Canvas.ALL_SAVE_FLAG);
//        canvas.drawColor(Color.GRAY);
//        canvas.restoreToCount(layerId);
    }
}
