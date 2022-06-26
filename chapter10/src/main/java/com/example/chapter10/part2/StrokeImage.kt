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
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * 单击描边效果
 *
 * 思路：
 * 1，给图片设置一个 selector 作为背景；
 * 2，给图片添加 padding 和可点击。
 *
 * @author wangzhichao
 * @date 2019/12/02
 */
public class StrokeImage extends ImageView {
    public StrokeImage(Context context) {
        super(context);
    }

    public StrokeImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StrokeImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 在系统将 XML 解析出对应的控件实例的时候调用。这时候控件已经生成，但还没有被使用，
    // 可以在这里对控件做一些基础设置。
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setStateDrawable(this, paint);
    }

    private void setStateDrawable(ImageView imageView, Paint paint) {
        // 制作纯色背景
        Drawable drawable = imageView.getDrawable();
        Bitmap srcBitmap = drawableToBitmap(drawable);
        Bitmap bitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Bitmap alphaBitmap = srcBitmap.extractAlpha();
        Canvas c = new Canvas(bitmap);
        paint.setColor(Color.CYAN);
        c.drawBitmap(alphaBitmap, 0,0, paint);
        // 添加状态
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, new BitmapDrawable(bitmap));
        setBackground(stateListDrawable);
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
