package com.example.chapter08.part2

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import com.example.chapter08.R
import java.util.ArrayList

/**
 * @author wangzhichao
 * @date 2019/09/23
 */
class A_PaintSetXfermodePorterDuffXfermodeViewGroup(context: Context?, attrs: AttributeSet?) :
    LinearLayout(context, attrs) {
    private val MODE_MODELS: MutableList<ModeModel> = ArrayList()

    class ModeModel(var mode: PorterDuff.Mode, var name: String, var desc: String)

    init {
        // 这里有 18 种混合模式
        // 1，颜色叠加相关模式 - 6 种
        // a, 这 6 种模式在 PhotoShop 中都有，是通过计算改变交合区域的颜色值的；
        // b, 除了 Mode.MULTIPLY 这个模式会在目标图像透明时将结果对应区域置为透明外，其它模式都不受目标图像透明区域的影响，也就是说
        // 源图像非交合区域会保持原样。
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.ADD, "Mode.ADD", "饱和度相加，公式是Saturate(S + D)。" +
                "在交合区域对SRC与DST两张图片相交区域的饱和度进行相加（PhotoShop也有），非交合区域显示原图像。 " +
                "Adds the source pixels to the destination pixels and saturates the result."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.LIGHTEN,
            "Mode.LIGHTEN",
            "变亮模式，算法是： [Sa + Da - Sa*Da,Sc*(1 - Da) + Dc*(1 - Sa) + max(Sc, Dc)]。" +
                    "只有交合区域才有 PhotoShop 变亮效果，非交合区域显示原图像。" +
                    "Retains the largest component of the source and destination pixel."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.DARKEN,
            "Mode.DARKEN",
            "变暗模式，对应公式是： [Sa + Da - Sa*Da,Sc*(1 - Da) + Dc*(1 - Sa) + mix(Sc, Dc)]。" +
                    "只有交合区域才有 PhotoShop 变暗效果，非交合区域显示原图像。" +
                    "Retains the smallest component of the source and destination pixels."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.MULTIPLY,
            "Mode.MULTIPLY",
            "正片叠底，公式是：[Sa * Da, Sc * Dc]。" +
                    "在交合区域显示 PhotoShop 的正片叠底效果，非交合区域变为透明。" +
                    "Multiplies the source and destination pixels."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.OVERLAY, "Mode.OVERLAY", "叠加，没有对应公式。" +
                "只有交合区域才有 PhotoShop 叠加效果，非交合区域显示原图像。" +
                "Multiplies or screens the source and destination depending on the destination color."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.SCREEN,
            "Mode.SCREEN",
            "滤色，对应公式是：[Sa + Da - Sa * Da, Sc + Dc - Sc * Dc]。" +
                    "只有交合区域才有 PhotoShop 滤色效果，非交合区域显示原图像。" +
                    "Adds the source and destination pixels, then subtracts the source pixels multiplied by the destination."))
        // 2，SRC相关模式 - 5 种
        // 在处理结果时，以原图像的显示为主
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.SRC, "Mode.SRC", "计算公式为：[Sa, Sc]。" +
                "在处理源图像所在区域的相交问题时，全部以源图像显示。" +
                "The source pixels replace the destination pixels."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.SRC_IN,
            "Mode.SRC_IN",
            "计算公式为：[Sa * Da, Sc * Da]。" +
                    "在相交时利用目标图像的透明度来改变源图像的透明度和饱和度。当目标图像透明度为0时，源图像就完全不显示。" +
                    "Keeps the source pixels that cover the destination pixels,discards the remaining source and destination pixels."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.SRC_OUT,
            "Mode.SRC_OUT",
            "计算公式为：[Sa * (1 - Da), Sc * (1 - Da)]。" +
                    "以目标图像的透明度的补值来调节源图像的透明度和色彩饱和度。即当目标图像为空白像素时，就完全显示源图像，当目标图像的透明度为100%时，交合区域为空像素。" +
                    "Keeps the source pixels that do not cover destination pixels.Discards source pixels that cover destination pixels. Discards all destination pixels."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.SRC_OVER,
            "Mode.SRC_OVER",
            "计算公式为：[Sa + (1 - Sa)*Da, Rc = Sc + (1 - Sa)*Dc]。" +
                    "在目标图像的顶部绘制源图像。" +
                    "The source pixels are drawn over the destination pixels."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.SRC_ATOP,
            "Mode.SRC_ATOP",
            "计算公式为：[Da, Sc * Da + (1 - Sa) * Dc]。" +
                    "当透明度只有100%和0%时，SRC_ATOP是SRC_IN是通用的；当透明度不只有100%和0%时，SRC_ATOP相比SRC_IN源图像的饱和度会增加，即会显得更亮！" +
                    "Discards the source pixels that do not cover destination pixels.Draws remaining source pixels over destination pixels."))
        // 3, DST相关模式 - 5 种
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.DST, "Mode.DST", "计算公式为：[Da, Dc]。" +
                "在处理源图像所在区域的相交问题时，正好与Mode.SRC相反，全部以目标图像显示。" +
                "The source pixels are discarded, leaving the destination intact."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.DST_IN,
            "Mode.DST_IN",
            "计算公式为：[Da * Sa,Dc * Sa]。" +
                    "与 SRC_IN 相反，DST_IN 是在相交时利用源图像的透明度来改变目标图像的透明度和饱和度。当源图像透明度为0时，目标图像就完全不显式。" +
                    "Keeps the destination pixels that cover source pixels,discards the remaining source and destination pixels."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.DST_OUT,
            "Mode.DST_OUT",
            "计算公式为：[Da * (1 - Sa), Dc * (1 - Sa)]。" +
                    "与 SRC_OUT 相反，以源图像的透明度的补值来调节目标图像的透明度和饱和度。当源图像透明度是 100% 时，则相交区域为空值；当源图像透明度为 0 时，则完全显示目标图像。" +
                    "Keeps the destination pixels that are not covered by source pixels. Discards destination pixels that are covered by source pixels. Discards all source pixels."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.DST_OVER,
            "Mode.DST_OVER",
            "计算公式为：[Sa + (1 - Sa)*Da, Rc = Dc + (1 - Da)*Sc]。" +
                    "与 SRC_OVER 相反，在源图像的顶部绘制目标图像。" +
                    "The source pixels are drawn behind the destination pixels."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.DST_ATOP,
            "Mode.DST_ATOP",
            "计算公式为：[Sa, Sa * Dc + Sc * (1 - Da)]。" +
                    "一般而言DST_ATOP是可以和DST_IN通用的，但DST_ATOP所产生的效果图在源图像的透明度不是0或100%的时候，会比DST_IN模式产生的图像更亮些。" +
                    "Discards the destination pixels that are not covered by source pixels. Draws remaining destination pixels over source pixels."))
        // 4, 其他模式
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.CLEAR, "Mode.CLEAR", "计算公式：[0, 0]。" +
                "Destination pixels covered by the source are cleared to 0."))
        MODE_MODELS.add(ModeModel(PorterDuff.Mode.XOR,
            "Mode.XOR",
            "计算公式为：[Sa + Da - Sa*Da,Sc*(1 - Da) + Dc*(1 - Sa) + min(Sc, Dc)]。" +
                    "Discards the source and destination pixels where source pixels cover destination pixels. Draws remaining source pixels."))
    }

    init {
        inflate(context, R.layout.layout_paint_setxfermode_porterduffxfermode_viewgroup, this)
        val spinner = findViewById<Spinner>(R.id.spinner)
        val v = findViewById<A_PaintSetXfermodePorterDuffXfermodeView>(R.id.view)
        val names: MutableList<String> = ArrayList()
        for (modeModel in MODE_MODELS) {
            names.add(modeModel.name)
        }
        val adapter = ArrayAdapter(
            context!!, android.R.layout.simple_list_item_1, android.R.id.text1, names)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                v.setModeModel(MODE_MODELS[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}