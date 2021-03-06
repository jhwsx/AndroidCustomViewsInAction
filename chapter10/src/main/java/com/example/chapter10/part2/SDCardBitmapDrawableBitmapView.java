package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.example.chapter10.R;
import com.example.chapter10.Utils;

import java.io.File;

/**
 * @author wangzhichao
 * @date 2019/10/23
 */
public class SDCardBitmapDrawableBitmapView extends View {
    StringBuilder sb = new StringBuilder();
    TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    public SDCardBitmapDrawableBitmapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setTextSize(Utils.dp2px(20));
        paint.setColor(Color.GREEN);
        // 从 Drawable 读取是存在缩放的
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        sb.append("drawableBmp_width:").append(bitmap.getWidth())
                .append("\t").append("drawableBmp_height:").append(bitmap.getHeight())
                .append("\t").append("内存：").append(bitmap.getByteCount());
        sb.append("\n\n");
        // 直接从文件中读取是不存在缩放的，原本是多大，读进来就是多大尺寸
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String path = externalStorageDirectory.getAbsoluteFile() + File.separator + "scenery.png";
        Bitmap bmp = BitmapFactory.decodeFile(path);
        if (bmp == null) {
            Toast.makeText(context, "sd card 上没有 scenery.png", Toast.LENGTH_SHORT).show();
            return;
        }
        sb.append("fileBmp_width:").append(bmp.getWidth())
                .append("\t").append("fileBmp_height:").append(bmp.getHeight())
                .append("\t").append("内存：").append(bmp.getByteCount());
        sb.append("\n\n");
        sb.append("----------结论--------------\n");
        sb.append("1,从 Drawable 读取是存在缩放的;2,直接从文件中读取是不存在缩放的，原本是多大，读进来就是多大尺寸");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        StaticLayout staticLayout = new StaticLayout(sb.toString(), paint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false);
        staticLayout.draw(canvas);
    }
}
