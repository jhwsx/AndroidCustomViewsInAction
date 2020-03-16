package com.example.chapter01.part4_region;

import android.content.Context;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.chapter01.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wangzhichao
 * @since 20-3-16
 */
public class RegionOperationViewGroup extends LinearLayout {
    //     *     DIFFERENCE(0), // 差集
// *     INTERSECT(1), // 交集
// *     UNION(2), // 并集
// *     XOR(3), // 异或
// *     REVERSE_DIFFERENCE(4),
// *     REPLACE(5); // 替换
    private List<Region.Op> items =
            new ArrayList<>();

    {
        Collections.addAll(items,
                Region.Op.DIFFERENCE,
                Region.Op.INTERSECT,
                Region.Op.UNION,
                Region.Op.XOR,
                Region.Op.REVERSE_DIFFERENCE,
                Region.Op.REPLACE);
    }

    private List<String> itemStrs = new ArrayList<>();

    public RegionOperationViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.view_region_operation_viewgroup, this);
        for (int i = 0; i < items.size(); i++) {
            String s = items.get(i).toString();
            Log.d("wzc", " s = " + s);
            itemStrs.add(s);
        }
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1, itemStrs) );
        final RegionOperationView regionOperationView = findViewById(R.id.view);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                regionOperationView.setOp(items.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


}
