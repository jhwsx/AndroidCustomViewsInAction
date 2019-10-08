package com.example.chapter09.part1;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * @author wangzhichao
 * @date 2019/09/30
 */
public class OnDrawDispatchDrawOrderView extends View {
    public static final String TAG = OnDrawDispatchDrawOrderView.class.getSimpleName();
    public OnDrawDispatchDrawOrderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw");
        super.onDraw(canvas);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.d(TAG, "dispatchDraw");
        super.dispatchDraw(canvas);
    }
}
