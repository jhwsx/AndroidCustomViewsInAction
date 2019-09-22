package com.example.chapter07.part5;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
public class PaintSetshaderLinearGradientViewGroup extends FrameLayout {
    private PaintSetshaderLinearGradientView pslv1;
    private PaintSetshaderLinearGradientView pslv2;
    private PaintSetshaderLinearGradientView pslv3;
    private PaintSetshaderLinearGradientView pslv4;
    public PaintSetshaderLinearGradientViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_paint_setshader_lineargradient_viewgroup, this);
        CheckBox cb = findViewById(R.id.cb);
        pslv1 = findViewById(R.id.pslv1);
        pslv2 = findViewById(R.id.pslv2);
        pslv3 = findViewById(R.id.pslv3);
        pslv4 = findViewById(R.id.pslv4);
        pslv4.setDrawText(true);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                pslv1.setSmallRect(isChecked);
                pslv2.setSmallRect(isChecked);
                pslv3.setSmallRect(isChecked);
            }
        });
    }
}
