package com.example.chapter06.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.example.chapter06.R
import java.util.regex.Pattern

/**
 * @author wangzhichao
 * @since 2020/01/11
 */
class PinyinTextViewGroup(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private fun filterContentHtml(content: String): String {
        // 过滤HTML里去除br、p、/p外的所有标签
        val regex = "(?!<(br|p).*?>)<.*?>"
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(content)
        return matcher.replaceAll("")
    }

    init {
        inflate(context, R.layout.practice_pinyin_text_view, this)
        val ptv1 = findViewById<PinyinTextView>(R.id.ptv_1)
        val content =
            "    <p><p><p><p>关关雎鸠。在河之洲。<p>大人之道有三：一曰正蒙難，二曰法授聖，三曰化及民。殷有仁人曰箕子 <p>大人之道有三：一曰正蒙難，二曰法授聖，三曰化及民。殷有仁人曰箕子"
        //        content = "　　凡大人之道有三：一曰正蒙難，二曰法授聖，三曰化及民。殷有仁人曰箕子，實具茲道以立於世，故孔子述六經之旨，尤殷勤焉。</p> " +
//                "<p>　　當紂之時，大道悖亂，天威之動不能戒，聖人之言無所用。進死以並命，誠仁矣，無益吾祀，故不為。委身以存祀，誠仁矣，與亡吾國，故不忍。具是二道，有行之者矣。是用保其明哲，與之俯仰；晦是謨範，辱於囚奴；昏而無邪，隤而不息；故在易曰“箕子之明夷”，正蒙難也。及天命既改，生人以正，乃出大法，用為聖師。周人得以序彝倫而立大典；故在書曰“以箕子歸作《洪範》”，法授聖也。及封朝鮮，推道訓俗，惟德無陋，惟人無遠，用廣殷祀，俾夷為華，化及民也。率是大道，叢於厥躬，天地變化，我得其正，其大人歟？</p> " +
//                "<p>　　嗚乎！當其周時未至，殷祀未殄，比幹已死，微子已去，向使紂惡未稔而自斃，武庚念亂以圖存，國無其人，誰與興理？是固人事之或然者也。然則先生隱忍而為此，其有志於斯乎？</p> " +
//                "<p>　　唐某年，作廟汲郡，歲時致祀，嘉先生獨列於易象，作是頌云：</p> " +
//                "<p>　　蒙難以正，授聖以謨。宗祀用繁，夷民其蘇。憲憲大人，顯晦不渝。聖人之仁，道合隆汙。明哲在躬，不陋為奴。沖讓居禮，不盈稱孤。高而無危，卑不可逾。非死非去，有懷故都。時詘而伸，卒為世模。易象是列，文王為徒。大明宣昭，崇祀式孚。古闕頌辭，繼在後儒。 "
//
//                + "　　凡大人之道有三：一曰正蒙難，二曰法授聖，三曰化及民。殷有仁人曰箕子，實具茲道以立於世，故孔子述六經之旨，尤殷勤焉。</p> " +
//                "<p>　　當紂之時，大道悖亂，天威之動不能戒，聖人之言無所用。進死以並命，誠仁矣，無益吾祀，故不為。委身以存祀，誠仁矣，與亡吾國，故不忍。具是二道，有行之者矣。是用保其明哲，與之俯仰；晦是謨範，辱於囚奴；昏而無邪，隤而不息；故在易曰“箕子之明夷”，正蒙難也。及天命既改，生人以正，乃出大法，用為聖師。周人得以序彝倫而立大典；故在書曰“以箕子歸作《洪範》”，法授聖也。及封朝鮮，推道訓俗，惟德無陋，惟人無遠，用廣殷祀，俾夷為華，化及民也。率是大道，叢於厥躬，天地變化，我得其正，其大人歟？</p> " +
//                "<p>　　嗚乎！當其周時未至，殷祀未殄，比幹已死，微子已去，向使紂惡未稔而自斃，武庚念亂以圖存，國無其人，誰與興理？是固人事之或然者也。然則先生隱忍而為此，其有志於斯乎？</p> " +
//                "<p>　　唐某年，作廟汲郡，歲時致祀，嘉先生獨列於易象，作是頌云：</p> " +
//                "<p>　　蒙難以正，授聖以謨。宗祀用繁，夷民其蘇。憲憲大人，顯晦不渝。聖人之仁，道合隆汙。明哲在躬，不陋為奴。沖讓居禮，不盈稱孤。高而無危，卑不可逾。非死非去，有懷故都。時詘而伸，卒為世模。易象是列，文王為徒。大明宣昭，崇祀式孚。古闕頌辭，繼在後儒。 ";
        val newContent = filterContentHtml(content)
        var replace = newContent.replace("<br>", PinyinTextView.Companion.BREAK_TAG)
        replace = replace.replace("<p>", PinyinTextView.Companion.PARAGRAPH_TAG)
        val pinyinString = PinyinUtils.getPinyinString(replace)
        for (s in pinyinString!!) {
            Log.d("PinyinTextViewGroup", s + "=>" + s!!.length)
        }
        val formatHanzi = PinyinUtils.getFormatHanzi(replace)
        ptv1.setPinyinAndHanzi(pinyinString, formatHanzi)
    }
}