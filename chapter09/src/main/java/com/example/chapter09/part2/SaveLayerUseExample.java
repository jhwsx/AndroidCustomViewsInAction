package com.example.chapter09.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter09.R;

import androidx.annotation.Nullable;

/**
 * saveLayer函数会新建一个全新的画布，其后的所有动作都只对新建画布有效，对原画布没有影响。
 * @author wzc
 * @date 2019/10/4
 */
public class SaveLayerUseExample extends View {
    private Paint paint;
    private Bitmap bitmap;
    public SaveLayerUseExample(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        canvas.skew(1.732f, 0);
        canvas.drawRect(0, 0, 150, 160, paint);
        canvas.restoreToCount(layerId);
    }
}
