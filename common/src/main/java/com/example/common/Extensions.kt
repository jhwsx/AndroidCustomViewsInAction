package com.example.common

import android.content.res.Resources
import android.util.TypedValue

/**
 *
 * @author wangzhichao
 * @since 2022/5/22
 */
val Float.dp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics)
val Int.dp
    get() = this.toFloat().dp

val Float.sp
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics)