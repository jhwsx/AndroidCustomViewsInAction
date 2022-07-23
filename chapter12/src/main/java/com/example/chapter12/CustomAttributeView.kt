package com.example.chapter12

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.*
import androidx.core.content.res.*

/**
 * https://mp.weixin.qq.com/s/lOxB91Fin3XyttzHn6y90A
 */
class CustomAttributeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    /**
     * reference: 指的是引用值，如@bool/open，@string/app_name，@id/btn1 等。只要是引用值都可以。
     *  Drawable getDrawable(@StyleableRes int index)
     *
     * string: 指的是字符串或引用字符串
     * dimension:
     */
    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomAttributeView)
        /*<attr name="header" format="reference" />*/
        val id = typedArray.getResourceIdOrThrow(R.styleable.CustomAttributeView_cav_id)
//         typedArray.getDrawable()
//        typedArray.getDrawableOrThrow() // ktx
//        typedArray.getColor()
//        typedArray.getColorOrThrow()
//        typedArray.getString()
//        typedArray.getStringOrThrow()
        // string: 指的是字符串或引用字符串
        /*<attr name="name" format="string" />*/
        // getText 获取带格式的字符串
        val tag1 = typedArray.getText(R.styleable.CustomAttributeView_cav_tag)
        Log.d(TAG, "tag1=$tag1")
        // getString 获取不带格式的字符串
        val tag2 = typedArray.getStringOrThrow(R.styleable.CustomAttributeView_cav_tag)
        Log.d(TAG, "tag2=$tag2")
        // getText 和 getString 都会把属性值强制转为字符串。
        val padding1 = typedArray.getDimension(R.styleable.CustomAttributeView_cav_padding, 0f) // 会抛出异常
        Log.d(TAG, "padding1=$padding1")
        val padding2 = typedArray.getDimensionOrThrow(R.styleable.CustomAttributeView_cav_padding) // ktx，比 getDimension 更清晰
        Log.d(TAG, "padding2=$padding2")
        val padding3 = typedArray.getDimensionPixelSize(R.styleable.CustomAttributeView_cav_padding, 0) // 会转为像素值，会抛出异常
        Log.d(TAG, "padding3=$padding3")
        val padding4 = typedArray.getDimensionPixelSizeOrThrow(R.styleable.CustomAttributeView_cav_padding)
        Log.d(TAG, "padding4=$padding4") // ktx
        val padding5 = typedArray.getDimensionPixelOffset(R.styleable.CustomAttributeView_cav_padding, 0) // 会转为像素值，会抛出异常
        Log.d(TAG, "padding5=$padding5")
        val padding6 = typedArray.getDimensionPixelOffsetOrThrow(R.styleable.CustomAttributeView_cav_padding)
        Log.d(TAG, "padding6=$padding6") // ktx
        /*<attr name="cav_clickable" format="boolean" />*/
        val clickable1 = typedArray.getBoolean(R.styleable.CustomAttributeView_cav_clickable, false)
        Log.d(TAG, "clickable1=$clickable1")
        val clickable2 = typedArray.getBooleanOrThrow(R.styleable.CustomAttributeView_cav_clickable)
        Log.d(TAG, "clickable2=$clickable2")
        /*<attr name="cav_backgroundTint" format="color" />*/
        // 获取硬编码的色值，颜色或颜色选择器默认颜色的引用值
        val backgroundTint1 = typedArray.getColor(R.styleable.CustomAttributeView_cav_backgroundTint, 0)
        Log.d(TAG, "backgroundTint1=$backgroundTint1")
        val backgroundTint2 = typedArray.getColorOrThrow(R.styleable.CustomAttributeView_cav_backgroundTint)
        Log.d(TAG, "backgroundTint2=$backgroundTint2")
        // 获取硬编码的色值，颜色或颜色选择器的引用值
        val backgroundTint3 = typedArray.getColorStateList(R.styleable.CustomAttributeView_cav_backgroundTint)
        Log.d(TAG, "backgroundTint3=$backgroundTint3")
        val backgroundTint4 = typedArray.getColorStateListOrThrow(R.styleable.CustomAttributeView_cav_backgroundTint)
        Log.d(TAG, "backgroundTint4=$backgroundTint4")
        // enum
        // getInt 会去把不是整型的值，强制转换为整型，转换失败时使用默认值。
        val visibility1 = typedArray.getInt(R.styleable.CustomAttributeView_cav_visibility, 0)
        Log.d(TAG, "visibility1=$visibility1")
        val visibility2 = typedArray.getIntOrThrow(R.styleable.CustomAttributeView_cav_visibility)
        Log.d(TAG, "visibility2=$visibility2")
        val visibility3 = typedArray.getInteger(R.styleable.CustomAttributeView_cav_visibility, 0)
        Log.d(TAG, "visibility3=$visibility3")
        val visibility4 = typedArray.getIntegerOrThrow(R.styleable.CustomAttributeView_cav_visibility)
        Log.d(TAG, "visibility4=$visibility4")
        // flag 与 enum 相比：flag 可以进行或位运算，而 enum 不可以进行或位运算。
        val gravity1 = typedArray.getInt(R.styleable.CustomAttributeView_cav_gravity, -1)
        Log.d(TAG, "gravity1=$gravity1")
        /*<attr name="cav_alpha" format="float" />*/
        val alpha1 = typedArray.getFloat(R.styleable.CustomAttributeView_cav_alpha, 0f)
        Log.d(TAG, "alpha1=$alpha1")
        val alpha2 = typedArray.getFloatOrThrow(R.styleable.CustomAttributeView_cav_alpha)
        Log.d(TAG, "alpha2=$alpha2")
        /*fraction*/
        val pivotX1 = typedArray.getFraction(R.styleable.CustomAttributeView_cav_pivotX,1,1,0f)
        Log.d(TAG, "pivotX1=$pivotX1")
        typedArray.recycle()
    }

    companion object {
        private const val TAG = "CustomAttributeView"
    }
}