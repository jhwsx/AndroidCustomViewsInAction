package com.example.chapter08.part2;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import com.example.chapter08.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhichao
 * @date 2019/09/23
 */
public class PaintSetXfermodePorterDuffXfermodeViewGroup extends LinearLayout {
    private final List<ModeModel> MODE_MODELS = new ArrayList<>();

    {
        MODE_MODELS.add(new ModeModel(PorterDuff.Mode.ADD, "Mode.ADD", "对SRC与DST两张图片相交区域的饱和度进行相加, Adds the source pixels to the destination pixels and saturates the result."));
        MODE_MODELS.add(new ModeModel(PorterDuff.Mode.LIGHTEN, "Mode.LIGHTEN", "变亮模式，Retains the largest component of the source and destination pixel."));
        MODE_MODELS.add(new ModeModel(PorterDuff.Mode.DARKEN, "Mode.DARKEN", "变暗模式，Retains the smallest component of the source and destination pixels."));
        MODE_MODELS.add(new ModeModel(PorterDuff.Mode.MULTIPLY, "Mode.MULTIPLY", "正片叠底，Multiplies the source and destination pixels."));
        MODE_MODELS.add(new ModeModel(PorterDuff.Mode.OVERLAY, "Mode.OVERLAY", "叠加，Multiplies or screens the source and destination depending on the destination color."));
        MODE_MODELS.add(new ModeModel(PorterDuff.Mode.SCREEN, "Mode.SCREEN", "滤色，Adds the source and destination pixels, then subtracts the source pixels multiplied by the destination."));

    }
    public PaintSetXfermodePorterDuffXfermodeViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_paint_setxfermode_porterduffxfermode_viewgroup, this);
        final Spinner spinner = findViewById(R.id.spinner);
        final PaintSetXfermodePorterDuffXfermodeView v = findViewById(R.id.view);
        List<String> names = new ArrayList<>();
        for (ModeModel modeModel : MODE_MODELS) {
            names.add(modeModel.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1, names);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                v.setModeModel(MODE_MODELS.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public static class ModeModel {
        PorterDuff.Mode mode;
        String name;
        String desc;

        public ModeModel(PorterDuff.Mode mode, String name, String desc) {
            this.mode = mode;
            this.name = name;
            this.desc = desc;
        }
    }
}
