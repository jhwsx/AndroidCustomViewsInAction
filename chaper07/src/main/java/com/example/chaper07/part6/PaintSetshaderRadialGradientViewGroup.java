package com.example.chaper07.part6;

import android.content.Context;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.chaper07.R;

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
public class PaintSetshaderRadialGradientViewGroup extends LinearLayout {
    private static final String[] TILE_MODES = {"CLAMP", "REPEAT", "MIRROR"};

    public PaintSetshaderRadialGradientViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_paint_setshader_radialgradient_viewgroup, this);
        CheckBox cbMultiColor = findViewById(R.id.cb_multi_color);
        CheckBox cbDrawRect = findViewById(R.id.cb_draw_rect);
        final CheckBox cbSmallRect = findViewById(R.id.cb_small_rect);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1, TILE_MODES));
        final PaintSetshaderRadialGradientView view = findViewById(R.id.view);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                if (0 == position) {
                    tileMode = Shader.TileMode.CLAMP;
                } else if (1 == position) {
                    tileMode = Shader.TileMode.REPEAT;
                } else if (2 == position) {
                    tileMode = Shader.TileMode.MIRROR;
                }
                view.setTileMode(tileMode);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cbMultiColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                view.setMultiColor(isChecked);
            }
        });
        cbDrawRect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                view.setDrawRect(isChecked);
                cbSmallRect.setVisibility(isChecked ? View.VISIBLE : View.INVISIBLE);
            }
        });
        cbSmallRect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                view.setSmallRect(isChecked);
            }
        });
    }
}
