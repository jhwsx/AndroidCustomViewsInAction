package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import com.example.chapter10.Utils;

/**
 * @author wangzhichao
 * @date 2019/10/22
 */
public class DensityDpiView extends View {

    private final String info;
    private TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

    public DensityDpiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        float widthInch = displayMetrics.widthPixels / displayMetrics.xdpi;
        float heightInch = displayMetrics.heightPixels / displayMetrics.ydpi;
        float screenInch = (float) Math.sqrt(Math.pow(widthInch, 2) + Math.pow(heightInch, 2));
        info = "density: " + displayMetrics.density + "\n"
                + "densityDpi: " + displayMetrics.densityDpi + "\n"
                + "heightPixels: " + displayMetrics.heightPixels + "\n"
                + "widthPixels: " + displayMetrics.widthPixels + "\n"
                + "xdpi: " + displayMetrics.xdpi + "\n"
                + "ydpi: " + displayMetrics.ydpi + "\n"
                + "widthInch: " + widthInch + "\n"
                + "heightInch: " + heightInch + "\n"
                + "screenInch: " + screenInch;
        paint.setColor(Color.GREEN);
        paint.setTextSize(Utils.dp2px(24));
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        StaticLayout staticLayout = new StaticLayout(info, paint, getWidth(), Layout.Alignment.ALIGN_CENTER, 1f, 0f, false);
        staticLayout.draw(canvas);
    }
}
