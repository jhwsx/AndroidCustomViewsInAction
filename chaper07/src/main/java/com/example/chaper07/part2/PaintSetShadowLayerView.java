package com.example.chaper07.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chaper07.R;

/**
 * @author wangzhichao
 * @date 2019/09/20
 */
public class PaintSetShadowLayerView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap dogBitmap;
    private int radius;
    private int dx;
    private int dy;
    public PaintSetShadowLayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 只有文字支持硬件加速，其他都不支持硬件加速，所以这里禁用了硬件加速。
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        paint.setColor(Color.GREEN);
        paint.setTextSize(25);
        dogBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showShadow) {
            paint.setShadowLayer(radius, dx, dy, Color.GRAY);
        }
        if (clearShadow1) {
            paint.clearShadowLayer();
            /*
            public void clearShadowLayer() {
            setShadowLayer(0, 0, 0, 0);
            }
            */
        }
        if (clearShadow2) {
            paint.setShadowLayer(0, dx, dy, Color.GRAY);
        }
        canvas.drawText("启舰", 100, 100, paint);

        canvas.drawCircle(200, 200, 50, paint);

        canvas.drawBitmap(dogBitmap, null, new Rect(200, 300, 200 + dogBitmap.getWidth(), 300 + dogBitmap.getHeight()), paint);
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public void setDx(int dx) {
        this.dx = dx;
        invalidate();
    }

    public void setDy(int dy) {
        this.dy = dy;
        invalidate();
    }
    private boolean showShadow = true;
    public void showShadow() {
        showShadow = true;
        clearShadow1 = false;
        clearShadow2 = false;
        invalidate();
    }

    private boolean clearShadow1 = false;
    public void clearShadow1() {
        clearShadow1 = true;
        showShadow = false;
        invalidate();

    }
    private boolean clearShadow2 = false;
    public void clearShadow2() {
        clearShadow2 = true;
        showShadow = false;
        invalidate();
    }
}

/**
 * 总结：
 * 1，图片的阴影不受 setShadowLayer 最后一个参数的影响。
 */
