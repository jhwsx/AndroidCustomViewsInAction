package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.Utils;

/**
 * 自定义线性渐变带描边的 View
 * 步骤：
 * 1.先创建一个空白的bitmap；
 * 2，在空白的 bitmap 上绘制线性渐变的颜色；
 * 3，把绘制好渐变色的 bitmap 绘制到画布上；
 * 4，绘制描边
 *
 * @author wangzhichao
 * @date 2019/10/29
 */
public class BitmapCreateBitmapLinearGradientView extends View {

    private final Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    public BitmapCreateBitmapLinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = Bitmap.createBitmap(Utils.dp2px(200), Utils.dp2px(300), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, Utils.dp2px(300), new int[]{0xffffffff, 0x00ffffff}, new float[]{0f, 1f}, Shader.TileMode.CLAMP);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        p.setShader(linearGradient);
        Rect rect = new Rect(0, 0, Utils.dp2px(200), Utils.dp2px(300));
        canvas.drawRect(rect, p);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int left = width / 2 - bitmap.getWidth() / 2;
        int top = height / 2 - bitmap.getHeight() / 2;
        canvas.drawBitmap(bitmap, left, top, paint);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(Utils.dp2px(3));
        canvas.drawRect(left, top, left + bitmap.getWidth(), top + bitmap.getHeight(), paint);
    }
}
