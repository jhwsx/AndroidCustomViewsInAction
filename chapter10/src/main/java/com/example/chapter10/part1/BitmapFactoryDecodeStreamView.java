package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
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
public class BitmapFactoryDecodeStreamView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;
    public BitmapFactoryDecodeStreamView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream data = getImage("http://172.16.40.10:8080/dog.jpg");
                    if (data == null) {
                        return;
                    }
                    final Bitmap bitmap = BitmapFactory.decodeStream(data);
                    data.close();
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

    private InputStream getImage(String link) throws Exception {
        InputStream result = null;
        URL url = new URL(link);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setReadTimeout(6 * 1000);
        if (httpURLConnection.getResponseCode() == 200) {
            result = httpURLConnection.getInputStream();
//            result = readStream(inputStream);
//            result = readStream2(inputStream);
        }
        return result;
    }

//    private byte[] readStream(InputStream inputStream) throws Exception{
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        byte[] buffer = new byte[1024];
//        int length;
//        while((length = inputStream.read(buffer)) != -1) {
//            baos.write(buffer, 0, length);
//        }
//        inputStream.close();
//        baos.close();
//        return baos.toByteArray();
//    }
//
//    private byte[] readStream2(InputStream inputStream) throws Exception{
//        byte[] result = new byte[inputStream.available()];
//        inputStream.read(result);
//        inputStream.close();
//        return result;
//    }
}
