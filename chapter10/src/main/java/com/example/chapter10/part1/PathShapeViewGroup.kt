package com.example.chapter10.part1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chapter10.R;

/**
 * @author wangzhichao
 * @date 2019/10/11
 */
public class PathShapeViewGroup extends LinearLayout {
    public PathShapeViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_path_shape_viewgroup, this);
        final PathShapeView pathShapeView = findViewById(R.id.pathshapeview);
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final TextView tv = findViewById(R.id.tv);
        tv.setText(button1.getText());
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pathShapeView.updateStdWidthAndStdHeight(100,100);
                tv.setText(button1.getText());
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pathShapeView.updateStdWidthAndStdHeight(100,200);
                tv.setText(button2.getText());
            }
        });
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                pathShapeView.updateStdWidthAndStdHeight(200,100);
                tv.setText(button3.getText());
            }
        });
    }
}
