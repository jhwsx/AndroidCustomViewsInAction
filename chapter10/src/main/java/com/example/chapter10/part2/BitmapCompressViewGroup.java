package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.chapter10.R;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wangzhichao
 * @since 2019/12/10
 */
public class BitmapCompressViewGroup extends LinearLayout {
    private Handler handler = new Handler(Looper.getMainLooper());
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 30;
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
            sPoolWorkQueue, sThreadFactory);
    public BitmapCompressViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_bitmap_compress_viewgroup, this);
        ImageView iv1 = findViewById(R.id.iv1);
        final ImageView iv2 = findViewById(R.id.iv2);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cat);
        iv1.setImageBitmap(bitmap);

        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                boolean compress = bitmap.compress(Bitmap.CompressFormat.JPEG, 1, baos);
                byte[] bytes = baos.toByteArray();
                final Bitmap bitmap1 = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iv2.setImageBitmap(bitmap1);
                    }
                });
            }
        });

    }
}
