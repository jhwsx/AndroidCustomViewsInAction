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
import android.view.View;

import com.example.chapter10.R;
import com.example.chapter10.Utils;

/**
 * @author wangzhichao
 * @date 2019/10/23
 */
public class BitmapFactoryOptionsInScaledView extends View {
    private StringBuilder sb = new StringBuilder();
    TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

    public BitmapFactoryOptionsInScaledView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setTextSize(Utils.dp2px(20));
        paint.setColor(Color.GREEN);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        sb.append("inScaled 默认设置：").append("width: ").append(bitmap.getWidth())
                .append("\t").append("height: ").append(bitmap.getHeight())
                .append("\t").append("内存：").append(bitmap.getByteCount());
        sb.append("\n");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = true;
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.scenery, options);
        sb.append("inScaled = true：").append("width: ").append(bitmap1.getWidth())
                .append("\t").append("height: ").append(bitmap1.getHeight())
                .append("\t").append("内存：").append(bitmap1.getByteCount());
        sb.append("\n");
        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inScaled = false;
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.scenery, options2);
        sb.append("inScaled = false：").append("width: ").append(bitmap2.getWidth())
                .append("\t").append("height: ").append(bitmap2.getHeight())
                .append("\t").append("内存：").append(bitmap2.getByteCount());
        sb.append("\n\n");
        sb.append("---------------结论--------------------");
        sb.append("\n");
        sb.append("inScaled 参数表示在需要缩放时，是否对当前文件进行缩放" +
                "1,inScaled 参数默认设置是 true；" +
                "2，当 inScaled 参数为 true 时，图片会被缩放；" +
                "3，当 inScaled 参数为 false 时，图片不会被缩放，也就是保持原本的大小");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        StaticLayout staticLayout = new StaticLayout(sb.toString(), paint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
        staticLayout.draw(canvas);
    }
}
