package com.example.chapter06.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.chapter06.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author wangzhichao
 * @since 2020/01/11
 */
public class PinyinTextViewGroup extends LinearLayout {
    public PinyinTextViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.practice_pinyin_text_view, this);
        PinyinTextView ptv1 = findViewById(R.id.ptv_1);
        String content = "关关雎鸠，在河之洲。<br>窈窕淑女，君子好逑。<p>魯有執長竿入城門者，初豎執之，不可入；橫執之，亦不可入。計無所出。<br>俄有老父至，曰：“吾非聖人，但見事多矣！何不以鋸中截而入？”遂依而截之。";
        String newContent = filterContentHtml(content);
        String replace = newContent.replace("<br>", PinyinTextView.BREAK_TAG);
        replace = replace.replace("<p>", PinyinTextView.PARAGRAPH_TAG);
        String[] pinyinString = PinyinUtils.getPinyinString(replace);
        for (String s : pinyinString) {
            Log.d("PinyinTextViewGroup", s+"=>" + s.length());
        }
        String[] formatHanzi = PinyinUtils.getFormatHanzi(replace);
        ptv1.setPinyinAndHanzi(pinyinString, formatHanzi);
    }

    private String filterContentHtml(String content) {
        // 过滤HTML里去除br、p、/p外的所有标签
        String regex = "(?!<(br|p).*?>)<.*?>";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        return matcher.replaceAll("");
    }
}
