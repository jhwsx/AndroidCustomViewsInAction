package com.example.chapter10;

import android.content.res.Resources;

/**
 * @author wangzhichao
 * @date 2019/10/10
 */
public class Utils {
    private Utils() {
        //no instance
    }

    public static int dp2px(final float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
