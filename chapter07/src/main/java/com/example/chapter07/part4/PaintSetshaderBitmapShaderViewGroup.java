package com.example.chapter07.part4;

import android.content.Context;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * @author wangzhichao
 * @date 2019/09/21
 */
public class PaintSetshaderBitmapShaderViewGroup extends LinearLayout {
    private static final String TAG = PaintSetshaderBitmapShaderViewGroup.class.getSimpleName();
    private Spinner spinnerTileX;
    private Spinner spinnerTileY;
    private PaintSetshaderBitmapShaderView v;
    private CheckBox cb;
    private String[] spinnerItems = {"REPEAT", "MIRROR", "CLAMP"};

    public PaintSetshaderBitmapShaderViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_paint_setshader_bitmapshader_viewgroup, this);
        spinnerTileX = findViewById(R.id.spinnerTileX);
        spinnerTileY = findViewById(R.id.spinnerTileY);
        cb = findViewById(R.id.cb);
        v = findViewById(R.id.view);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1, spinnerItems);
        spinnerTileX.setAdapter(adapter);
        spinnerTileY.setAdapter(adapter);
        spinnerTileX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "spinnerTileX, onItemSelected: " + spinnerItems[position]);
                v.setTileX(mapTileMode(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "spinnerTileX, onNothingSelected");
            }
        });
        spinnerTileY.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "spinnerTileY, onItemSelected: " + spinnerItems[position]);
                v.setTileY(mapTileMode(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "spinnerTileY, onNothingSelected");
            }
        });
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                v.setSmallRect(isChecked);
            }
        });
    }

    private Shader.TileMode mapTileMode(int position) {
        switch (position) {
            case 0:
                return Shader.TileMode.REPEAT;
            case 1:
                return Shader.TileMode.MIRROR;
            case 2:
            default:
                return Shader.TileMode.CLAMP;

        }
    }
}
