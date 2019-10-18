package com.example.chapter10.part1;

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

import com.example.chapter10.R;
import com.example.chapter10.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangzhichao
 * @date 2019/10/16
 */
public class BitmapCompressFormat extends View {
    private TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    public BitmapCompressFormat(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setTextSize(40);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(5F);
        paint.setStyle(Paint.Style.FILL);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        save("scenary_png.png", bitmap, Bitmap.CompressFormat.PNG);
        save("scenary_jpeg.jpeg", bitmap, Bitmap.CompressFormat.JPEG);
        save("scenary_webp.webp", bitmap, Bitmap.CompressFormat.WEBP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        StaticLayout staticLayout = new StaticLayout(
                "Bitmap 的三种压缩格式：\n"
                +"  Bitmap.CompressFormat.PNG\n"
                + " Bitmap.CompressFormat.JPEG\n"
                + " Bitmap.CompressFormat.WEBP\n"
                + "可以去 SD 卡根目录查看转化成的三种照片。", paint,
                getWidth(), Layout.Alignment.ALIGN_CENTER, 1, 0F, true);
        staticLayout.draw(canvas);
    }

    private void save(final String fileName, final Bitmap bitmap, final Bitmap.CompressFormat compressFormat) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File file = new File(Environment.getExternalStorageDirectory() + File.separator + fileName);
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(compressFormat, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
