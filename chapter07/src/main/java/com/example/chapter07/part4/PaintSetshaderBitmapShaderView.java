package com.example.chapter07.part4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * @author wangzhichao
 * @date 2019/09/21
 */
public class PaintSetshaderBitmapShaderView extends View {
    private Paint paint;
    private Bitmap bitmap;
    private Shader.TileMode tileX;
    private Shader.TileMode tileY;
    private boolean smallRect;

    public PaintSetshaderBitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog_edge);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (tileX != null) {
            // bitmap: 指定用什么图来填充
            // tileX: 当需要填充的图形区域 x 向大于 bitmap 时，采用什么重复策略来填满 x 向的区域。
            paint.setShader(new BitmapShader(bitmap, tileX, tileY));
        }
        if (smallRect)
            canvas.drawRect(getWidth() / 3, getHeight() / 3, getWidth() * 2 / 3, getHeight() * 2 / 3, paint);
        else
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

    public void setTileX(Shader.TileMode tileX) {
        this.tileX = tileX;
        invalidate();
    }

    public void setTileY(Shader.TileMode tileY) {
        this.tileY = tileY;
        invalidate();
    }

    public void setSmallRect(boolean smallRect) {
        this.smallRect = smallRect;
        invalidate();
    }
}

/**
 * 总结：
 * 1，BitmapShader 就是用 Bitmap 来进行着色，填充图形的。
 * 1，绘制是从控件的左上角开始的；
 * 2，填充顺序是先填充 Y 轴，再填充 X 轴，这一点是从都是 CLAMP 的效果看出来；
 */
