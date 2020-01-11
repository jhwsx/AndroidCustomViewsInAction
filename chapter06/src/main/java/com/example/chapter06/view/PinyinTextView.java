package com.example.chapter06.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;


import com.example.chapter06.R;

import java.util.ArrayList;


public class PinyinTextView extends View {

    private String[] pinyinArr;
    private String[] hanziArr;

    private TextPaint textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    // 对应 <br>
    public static final String BREAK_TAG = "#";
    // 对应 <p>
    public static final String PARAGRAPH_TAG = "&";
    /**
     * 统计标点长度
     */
    private ArrayList<String> punctuationList = new ArrayList<>();

    int row = 1;

    float density;

    public PinyinTextView(Context context) {
        this(context, null);
    }

    public PinyinTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PinyinTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PinyinTextView);
        int textColor = ta.getColor(R.styleable.PinyinTextView_ptv_text_color, 0x333333);
        int textSize = ta.getDimensionPixelSize(R.styleable.PinyinTextView_ptv_text_size, 20);
        ta.recycle();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        density = getResources().getDisplayMetrics().density;

        paint.setColor(Color.RED);
        paint.setStrokeWidth(1f);
        paint.setStyle(Paint.Style.STROKE);
    }

    public void setPinyinAndHanzi(String[] pinyin, String[] hanzi) {
        this.pinyinArr = pinyin;
        this.hanziArr = hanzi;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec, width);
        setMeasuredDimension(width, height);
    }
    float baselineX = 0f;
    @Override
    protected void onDraw(Canvas canvas) {
        if (pinyinArr == null || pinyinArr.length == 0) {
            return;
        }
        baselineX = 0f;
        float pinyinRowWidth;
        row = 1;
        for (int index = 0; index < pinyinArr.length; index++) {
            if (!TextUtils.equals(pinyinArr[index], "null") && !TextUtils.equals(pinyinArr[index], " ")) {
                String pinyin = getPinyinByIndex(index);
                pinyinRowWidth = baselineX + textPaint.measureText(pinyin);
                if (pinyinRowWidth > getWidth()) {
                    row++;
                    baselineX = 0;
                }
                float baselineY = (row * 2 - 1) * (textPaint.getFontSpacing());
                canvas.drawText(pinyin, baselineX, baselineY, textPaint);
                float width = textPaint.measureText(pinyin);
                Paint.FontMetricsInt fontMetricsInt = textPaint.getFontMetricsInt();

                RectF rect = new RectF();
                rect.left = baselineX;
                rect.right = baselineX + width;
                rect.top = fontMetricsInt.top + baselineY;
                rect.bottom = fontMetricsInt.bottom + baselineY;
//                textPaint.getTextBounds(pinyin, 0, pinyin.length(), rect);
//                rect.set((int) (baselineX +rect.left), (int)(baselineY + rect.top), (int)(baselineX + rect.right), (int)(baselineY + rect.bottom));
                canvas.drawRect(rect, paint);
                drawTone(canvas, baselineX, pinyinArr[index], baselineY);
                drawHanzi(canvas, baselineX, index);
                updateBaselineX(textPaint.measureText(getPinyinByIndex(index)));
            } else if (TextUtils.equals(pinyinArr[index], "null")) {
                if (TextUtils.equals(hanziArr[index], BREAK_TAG)) {
                    row++;
                    baselineX = 0;
                } else if (TextUtils.equals(hanziArr[index], PARAGRAPH_TAG)) {
                    row += 2;
                    baselineX = 0;
                } else {
                    drawPunctuation(canvas, hanziArr[index]);
                    updateBaselineX(textPaint.measureText(hanziArr[index]));
                }
            }
        }
    }

    private void drawPunctuation(Canvas canvas, String text) {
        // 绘制标点符号
        float hanziWidth = baselineX + textPaint.measureText(text);
        if (hanziWidth > getWidth()) {
            row++;
            baselineX = 0;
        }
        canvas.drawText(text, baselineX, (row * 2) * textPaint.getFontSpacing(), textPaint);
    }

    private void drawHanzi(Canvas canvas, float baselineX, int index) {
        // 由于拼音长度固定，采用居中显示策略，计算拼音实际长度不需要去掉拼音后面空格
        String hanzi = hanziArr[index];
        float hanziBaselineX = baselineX + (textPaint.measureText(getPinyinByIndex(index))
                - textPaint.measureText(hanzi)) / 2 - moveHalfIfNeed(getPinyinByIndex(index), textPaint);
        float hanziBaselineY = (row * 2) * (textPaint.getFontSpacing());
        canvas.drawText(hanzi, hanziBaselineX, hanziBaselineY, textPaint);
    }

    private void updateBaselineX(float width) {
        baselineX += width;
    }

    private void drawTone(Canvas canvas, float baselineX, String pinyinWithTone, float baselineY) {
        String tone = " ";
        switch (pinyinWithTone.charAt(pinyinWithTone.length() - 1)) {
            case '1':
                tone = "ˉ";
                break;
            case '2':
                tone = "ˊ";
                break;
            case '3':
                tone = "ˇ";
                break;
            case '4':
                tone = "ˋ";
                break;
            default:
                break;
        }
        // 去掉数字和空格符
        int toneIndex = pinyinWithTone.length() - 3;
        int stateIndex = -1;
        for (; toneIndex >= 0; toneIndex--) {
            if (pinyinWithTone.charAt(toneIndex) == 'a' || pinyinWithTone.charAt(toneIndex) == 'e'
                    || pinyinWithTone.charAt(toneIndex) == 'i' || pinyinWithTone.charAt(toneIndex) == 'o'
                    || pinyinWithTone.charAt(toneIndex) == 'u' || pinyinWithTone.charAt(toneIndex) == 'v') {
                if (stateIndex == -1 || pinyinWithTone.charAt(toneIndex) < pinyinWithTone.charAt(stateIndex)) {
                    stateIndex = toneIndex;
                }
            }
        }
        // iu同时存在规则
        if (pinyinWithTone.contains("u")
                && pinyinWithTone.contains("i")
                && !pinyinWithTone.contains("a")
                && !pinyinWithTone.contains("o")
                && !pinyinWithTone.contains("e")) {
            stateIndex = pinyinWithTone.indexOf("u") > pinyinWithTone.indexOf("i") ? pinyinWithTone.indexOf("u") : pinyinWithTone.indexOf("i");
        }
        if (stateIndex != -1) {
            // 没有声母存在时，stateIndex一直为-1 （'嗯' 转成拼音后变成 ng,导致没有声母存在，stateIndex一直为-1，数组越界crash）
            canvas.drawText(tone, baselineX + textPaint.measureText(pinyinWithTone.substring(0, stateIndex))
                            + (textPaint.measureText(pinyinWithTone.charAt(stateIndex) + "") - textPaint.measureText(tone + "")) / 2,
                    baselineY, textPaint);
        }
    }

    private int measureHeight(int heightMeasureSpec, int width) {
        int result = 0;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            result = heightSize;
        } else {
            if (pinyinArr != null && pinyinArr.length != 0) {
                float calculatedRowWidth = 0;
                int rowTotal = 1;
                for (int index = 0; index < pinyinArr.length; index++) {
                    if (TextUtils.equals(pinyinArr[index], "null")) {
                        if (TextUtils.equals(hanziArr[index], BREAK_TAG) || TextUtils.equals(hanziArr[index], PARAGRAPH_TAG)) {
                            // 没有对应的拼音，对应汉字部分是换行符，段落标签
                            rowTotal += TextUtils.equals(hanziArr[index], BREAK_TAG) ? 1 : 2;
                            // 重置宽度,初始宽度要么是 null 的宽度，要么是对应拼音的宽度。
                            calculatedRowWidth = resetCalculatedRowWidth(index);
                            continue;
                        } else {
                            // 没有对应的拼音，则需要加上汉字部分的宽度(指的是标点符号)
                            calculatedRowWidth += textPaint.measureText(hanziArr[index]);
                        }
                    } else {
                        // 有对象的拼音，则加上拼音的宽度（注意不带最后的声调标志1,2,3,4）
                        calculatedRowWidth += textPaint.measureText(getPinyinByIndex(index));
                    }
                    if (calculatedRowWidth > width) {
                        // 当前已经宽度超过控件的宽度
                        rowTotal++;
                        calculatedRowWidth = resetCalculatedRowWidth(index);
                    }
                }
                // TODO: 2020/01/11 这句是怎么计算的？
                result = (int) Math.ceil((rowTotal * 2) * (textPaint.getFontSpacing() + density * 1));
            }
            if (heightMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, heightSize);
            }
        }
        return result;
    }

    /**
     * 重置宽度,初始宽度要么是 null 的宽度，要么是对应拼音的宽度。
     */
    private float resetCalculatedRowWidth(int index) {
        return TextUtils.equals(pinyinArr[index], "null") ?
                textPaint.measureText(pinyinArr[index]) : textPaint.measureText(getPinyinByIndex(index));
    }

    /**
     * 按索引获取拼音部分，不带声调
     *
     * @param index
     * @return
     */
    private String getPinyinByIndex(int index) {
        return pinyinArr[index].substring(0, pinyinArr[index].length() - 1);
    }

    private int measureWidth(int widthMeasureSpec) {
        int result = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            result = widthSize;
        } else {
            result = 200;
            if (widthMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, widthSize);
            }
        }
        return result;
    }

    private float moveHalfIfNeed(String pinyinUnit, TextPaint paint) {

        if (pinyinUnit.trim().length() % 2 == 0) {
            return paint.measureText(" ") / 2;
        } else {
            return 0;
        }
    }
}