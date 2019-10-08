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
 * saveLayerAlpha函数新建的画布是有透明度的.
 * @author wzc
 * @date 2019/10/4
 */
public class SaveLayerAlphaUseExample extends View {
    private Paint paint;
    private Bitmap bitmap;
    public SaveLayerAlphaUseExample(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, paint);

        int layerId = canvas.saveLayerAlpha(0, 0, 200, 200, 100, Canvas.ALL_SAVE_FLAG);
        canvas.drawColor(Color.GRAY);
        canvas.restoreToCount(layerId);
    }
}
