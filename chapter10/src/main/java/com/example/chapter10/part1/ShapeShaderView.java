package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.chapter10.R;
import com.example.chapter10.Utils;

/**
 * @author wangzhichao
 * @date 2019/10/12
 */
public class ShapeShaderView extends View {
    private ShapeDrawable drawable;
    public ShapeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        drawable = new ShapeDrawable(new RectShape());
        drawable.setBounds(Utils.dp2px(50), Utils.dp2px(50), Utils.dp2px(300), Utils.dp2px(300));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avator);
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        drawable.getPaint().setShader(bitmapShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawable.draw(canvas);
    }
}

/**
 * 总结：
 * 1，Shader 是从哪里开始绘制的？
 * 是从 ShapeDrawable 的左上角开始绘制的。
 */
