package com.example.chapter10.part2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.R;
import com.example.chapter10.Utils;
import com.example.chapter10.part1.CircledDrawable;

/**
 * https://blog.csdn.net/l_lhc/article/details/50923372
 * @author wangzhichao
 * @date 2019/10/15
 */
public class BitmapDrawableConvert extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public BitmapDrawableConvert(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint.setTextSize(Utils.dp2px(16));
        paint.setColor(Color.GREEN);
        paint.setTextAlign(Paint.Align.CENTER);
        // 1, Drawable 资源转成 Bitmap
        // 1.1
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avator);
        // 1.2 适用于 21 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Drawable drawable = getResources().getDrawable(R.drawable.avator, null);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bmp = bitmapDrawable.getBitmap();
        }
        // 1.3
        CircledDrawable drawable = new CircledDrawable(bitmap);
        Bitmap bitmap1 = drawableToBitmap(drawable);
        // 2, Bitmap 转 Drawable
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.drawText("演示 Drawable，Bitmap 的相互转换，去看代码吧", 0,0,paint);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {

        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config =
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        //注意，下面三行代码要用到，否则在View或者SurfaceView里的canvas.drawBitmap会看不到图
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);

        return bitmap;
    }
}
