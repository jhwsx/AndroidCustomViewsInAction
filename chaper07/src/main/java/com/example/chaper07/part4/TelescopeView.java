package com.example.chaper07.part4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chaper07.R;

/**
 * @author wangzhichao
 * @date 2019/09/21
 */
public class TelescopeView extends View {
    private static final String TAG = "TelescopeView";
    private Paint paint;
    private Bitmap bitmap;
    private Bitmap canvasBitmap;
    private BitmapShader bitmapShader;
    private float cx = -1;
    private float cy = -1;

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (canvasBitmap == null) {
            // 创建一张与背景一样大的空白位图
            canvasBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
            // 把背景图画到空白位图上
            Canvas bgCanvas = new Canvas(canvasBitmap);
            bgCanvas.drawBitmap(bitmap, null, new Rect(0, 0, getWidth(), getHeight()), paint);
        }
        if (bitmapShader == null) {
            bitmapShader = new BitmapShader(canvasBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        }
        paint.setShader(bitmapShader);
        if (cx != -1) {
            canvas.drawCircle(cx, cy, 200, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "ACTION_DOWN");
                cx = event.getX();
                cy = event.getY();
                return true; // 这里需要返回 true，这样后续的事件才会陆续过来。
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "ACTION_MOVE");
                cx = event.getX();
                cy = event.getY();
                break;
            default:
                cx = -1;
                cy = -1;
        }
        invalidate();
        return super.onTouchEvent(event);
    }
}
