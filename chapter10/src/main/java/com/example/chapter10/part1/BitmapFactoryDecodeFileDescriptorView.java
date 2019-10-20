package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author wangzhichao
 * @date 2019/10/20
 */
public class BitmapFactoryDecodeFileDescriptorView extends View {

    private Bitmap bitmap;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public BitmapFactoryDecodeFileDescriptorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        String path = Environment.getExternalStorageDirectory() + File.separator + "scenary_png.png";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            bitmap = BitmapFactory.decodeFileDescriptor(fileInputStream.getFD());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0,0, paint);
    }
}

/**
 * 总结：
 * 1，BitmapFactory.decodeFileDescriptor 解析的方式比使用 BitmapFactory.decodeFile 更节省内存。
 */
