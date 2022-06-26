package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.chapter10.R;
import com.example.chapter10.Utils;

/**
 * Bitmap.copy() 方法的使用
 * @author wangzhichao
 * @date 2019/10/31
 */
public class BitmapCopyView extends View {
    private StringBuilder sb = new StringBuilder();
    private TextPaint textPaint = new TextPaint();
    public BitmapCopyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 先加载一张图片
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        Log.d("BitmapCopyView", "bitmap.isMutable() = " + bitmap.isMutable());
        //
        Bitmap copy1 = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Log.d("BitmapCopyView", "copy1.isMutable() = " + copy1.isMutable());
        //
        Bitmap copy2 = bitmap.copy(Bitmap.Config.ARGB_8888, false);
        Log.d("BitmapCopyView", "copy2.isMutable() = " + copy2.isMutable());
        try {
            copy2.setPixel(1,1, 0x000000);
        } catch (Exception e) {
            Log.e("BitmapCopyView", "error: ",e);
        }
        sb.append("1,通过 BitmapFactory 加载的 Bitmap 都是像素不可变的，只有通过 Bitmap 中的几个函数创建的 Bitmap 才是像素可变的；\n")
                .append("2,对于像素不可变的 Bitmap，是不可以作为画布的；\n")
                .append("3,使用 bitmap.copy() 方法可以实现像素可变与不可变的转化.");
        textPaint.setColor(Color.GREEN);
        textPaint.setTextSize(Utils.dp2px(20));
        textPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        StaticLayout staticLayout = new StaticLayout(sb.toString(), textPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
        staticLayout.draw(canvas);
    }
}
