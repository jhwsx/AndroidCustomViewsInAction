package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author wangzhichao
 * @date 2019/10/16
 */
public class BitmapCompressFormat extends View {
    public BitmapCompressFormat(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        save("scenary_png.png", bitmap, Bitmap.CompressFormat.PNG);
        save("scenary_jpeg.jpeg", bitmap, Bitmap.CompressFormat.JPEG);
        save("scenary_webp.webp", bitmap, Bitmap.CompressFormat.WEBP);
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
