package com.example.chapter07.part2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * @author wangzhichao
 * @date 2019/09/20
 */
public class PaintSetShadowLayerViewGroup extends LinearLayout {
    private static final String TAG = PaintSetShadowLayerViewGroup.class.getSimpleName();
    private SeekBar seekBarRadius;
    private SeekBar seekBarDx;
    private SeekBar seekBarDy;
    private PaintSetShadowLayerView view;
    private View btnShowShadow;
    private View btnClearShadow1;
    private View btnClearShadow2;

    public PaintSetShadowLayerViewGroup(Context context) {
        super(context);
        init();
    }

    public PaintSetShadowLayerViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintSetShadowLayerViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.layout_paint_setshadowlayer_viewgroup, this);
        initViews();
        initData();
    }
    private void initData() {
        seekBarRadius.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                Log.d(TAG, "radius = " + progress);
                view.setRadius(progress);
            }
        });
        seekBarDx.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                int newProgress = progress - 20;
                Log.d(TAG, "dx = " + newProgress);
                view.setDx(newProgress);
            }
        });
        seekBarDy.setOnSeekBarChangeListener(new SimpleOnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                super.onProgressChanged(seekBar, progress, fromUser);
                int newProgress = progress - 20;
                Log.d(TAG, "dy = " + newProgress);
                view.setDy(newProgress);
            }
        });
        btnShowShadow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view.showShadow();
            }
        });
        btnClearShadow1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view.clearShadow1();
            }
        });
        btnClearShadow2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                view.clearShadow2();
            }
        });
    }

    private void initViews() {
        seekBarRadius =  findViewById(R.id.seekbar_radius);
        seekBarDx =  findViewById(R.id.seekbar_dx);
        seekBarDy =  findViewById(R.id.seekbar_dy);
        view = findViewById(R.id.view);
        btnShowShadow = findViewById(R.id.btnShowShadow);
        btnClearShadow1 = findViewById(R.id.btnClearShadow1);
        btnClearShadow2 = findViewById(R.id.btnClearShadow2);
    }

    private class SimpleOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
