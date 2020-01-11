package com.example.chapter06.view;

import android.util.Log;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author wangzhichao
 * @since 2020/01/11
 */
public class PinyinUtils {
    public static String[] getPinyinString(String hanzi) {
        if (hanzi != null && hanzi.length() > 0) {
            String[] pinyin = new String[hanzi.length()];
            HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
            for (int index = 0; index < hanzi.length(); index++) {
                char c = hanzi.charAt(index);
                try {
                    String[] pinyinUnit = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pinyinUnit == null) {
                        pinyin[index] = "null";  // 非汉字字符，如标点符号
                        continue;
                    } else {
                        pinyin[index] = formatCenterUnit(pinyinUnit[0].substring(0, pinyinUnit[0].length() - 1)) +
                                pinyinUnit[0].charAt(pinyinUnit[0].length() - 1);  // 带音调且长度固定为7个字符长度,,拼音居中,末尾优先
                        Log.e("pinyin", pinyin[index]);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }

            }
            return pinyin;
        } else {
            return null;
        }
    }

    // 每个拼音单元长度以7个字符长度为标准,拼音居中,末尾优先
    private static String formatCenterUnit(String unit) {
        String result = unit;
        switch(unit.length()) {
            case 1:
                result = "   " + result + "   ";
                break;
            case 2:
                result = "  " + result + "   ";
                break;
            case 3:
                result = "  " + result + "  ";
                break;
            case 4:
                result = " " + result + "  ";
                break;
            case 5:
                result = " " + result + " ";
                break;
            case 6:
                result = result + " ";
                break;
        }
        return result;
    }

    public static String[] getFormatHanzi(String hanzi) {
        if (hanzi != null && hanzi.length() > 0) {
            char[] c = hanzi.toCharArray();
            String[] result = new String[c.length];
            for (int index = 0; index < c.length; index++) {
                result[index] = c[index] + "";
            }
            return result;
        } else {
            return null;
        }
    }
}
