package com.example.chapter10.part1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.chapter10.R;

/**
 * 圆角 Drawable
 *
 * @author wangzhichao
 * @date 2019/10/15
 */
public class CircledDrawableViewGroup extends ScrollView {

    public CircledDrawableViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_circled_drawable_viewgroup, this);
        ImageView iv0 = findViewById(R.id.iv0);
        ImageView iv1 = findViewById(R.id.iv1);
        ImageView iv2 = findViewById(R.id.iv2);
        ImageView iv3 = findViewById(R.id.iv3);
        ImageView iv4 = findViewById(R.id.iv4);
        ImageView iv5 = findViewById(R.id.iv5);
        ImageView iv6 = findViewById(R.id.iv6);
        ImageView iv7 = findViewById(R.id.iv7);
        ImageView iv8 = findViewById(R.id.iv8);
        TextView tv = findViewById(R.id.tv);
        TextView tv1 = findViewById(R.id.tv1);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avator);
        iv0.setImageDrawable(new CircledDrawable(bitmap));
        iv1.setImageDrawable(new CircledDrawable(bitmap));
        iv2.setImageDrawable(new CircledDrawable(bitmap));
        iv3.setImageDrawable(new CircledDrawable(bitmap));
        iv4.setImageDrawable(new CircledDrawable(bitmap));
        iv5.setImageDrawable(new CircledDrawable(bitmap));
        iv6.setImageDrawable(new CircledDrawable(bitmap));
        iv7.setImageDrawable(new CircledDrawable(bitmap));
        iv8.setImageDrawable(new CircledDrawable(bitmap));
        tv.setBackgroundDrawable(new CircledDrawable(bitmap));
        tv1.setBackgroundDrawable(new CircledDrawable1(bitmap));
    }
}

/**
 * 总结：
 * 1，ImageView 默认的 scaleType 是 fitCenter；
 * 2，setImageDrawable 对应于 src 属性；
 * 3，源图片的显示大小是与 ImageView 的 scaleType 相关的。todo 怎么验证？
 */
