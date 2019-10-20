package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author wangzhichao
 * @date 2019/10/20
 */
public class BitmapFactoryDecodeByteArrayView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;
    public BitmapFactoryDecodeByteArrayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] data = getImage("http://172.16.40.10:8080/dog.jpg");
                    if (data == null) {
                        return;
                    }
                    final Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    post(new Runnable() {
                        @Override
                        public void run() {
                            mBitmap = bitmap;
                            invalidate();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBitmap == null) {
            return;
        }
        canvas.drawBitmap(mBitmap,0,0,paint);

    }

    private byte[] getImage(String link) throws Exception {
        byte[] result = null;
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setReadTimeout(6 * 1000);
        if (httpURLConnection.getResponseCode() == 200) {
            InputStream inputStream = httpURLConnection.getInputStream();
            result = readStream(inputStream);
            inputStream.close();
        }
        return result;
    }

    private byte[] readStream(InputStream inputStream) throws Exception{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        inputStream.close();
        baos.close();
        return baos.toByteArray();
    }
}
