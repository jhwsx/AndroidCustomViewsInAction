package com.example.chapter06.view

import android.util.Log
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType
import net.sourceforge.pinyin4j.PinyinHelper
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination

/**
 * @author wangzhichao
 * @since 2020/01/11
 */
object PinyinUtils {
    fun getPinyinString(hanzi: String?): Array<String?>? {
        return if (hanzi != null && hanzi.isNotEmpty()) {
            val pinyin = arrayOfNulls<String>(hanzi.length)
            val format = HanyuPinyinOutputFormat()
            format.caseType = HanyuPinyinCaseType.LOWERCASE
            format.toneType = HanyuPinyinToneType.WITH_TONE_NUMBER
            for (index in hanzi.indices) {
                val c = hanzi[index]
                try {
                    val pinyinUnit = PinyinHelper.toHanyuPinyinStringArray(c, format)
                    if (pinyinUnit == null) {
                        pinyin[index] = "null" // 非汉字字符，如标点符号
                        continue
                    } else {
                        pinyin[index] =
                            formatCenterUnit(pinyinUnit[0].substring(0, pinyinUnit[0].length - 1)) +
                                    pinyinUnit[0][pinyinUnit[0].length - 1] // 带音调且长度固定为7个字符长度,,拼音居中,末尾优先
                        Log.e("pinyin", pinyin[index]!!)
                    }
                } catch (badHanyuPinyinOutputFormatCombination: BadHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace()
                }
            }
            pinyin
        } else {
            null
        }
    }

    // 每个拼音单元长度以7个字符长度为标准,拼音居中,末尾优先
    private fun formatCenterUnit(unit: String): String {
        var result = unit
        when (unit.length) {
            1 -> result = "   $result   "
            2 -> result = "  $result   "
            3 -> result = "  $result  "
            4 -> result = " $result  "
            5 -> result = " $result "
            6 -> result = "$result "
        }
        return result
    }

    fun getFormatHanzi(hanzi: String?): Array<String?>? {
        return if (hanzi != null && hanzi.length > 0) {
            val c = hanzi.toCharArray()
            val result = arrayOfNulls<String>(c.size)
            for (index in c.indices) {
                result[index] = c[index].toString() + ""
            }
            result
        } else {
            null
        }
    }
}