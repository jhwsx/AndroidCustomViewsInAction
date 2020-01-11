package com.example.chapter06.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter06.R;

/**
 * @author wangzhichao
 * @since 2020/01/09
 */
public class DiamondBackgroundTextView extends View {

    private char[] arr;
    private Rect bounds = new Rect();
    private Rect backgroundRect = new Rect();
    private Path path = new Path();
    private Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private static final float OFFSET_PERCENT = 0.2f;
    private int baseLineX;
    private int baseLineY;

    public DiamondBackgroundTextView(Context context) {
        this(context, null);
    }

    public DiamondBackgroundTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DiamondBackgroundTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs ) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DiamondBackgroundTextView);
        String text = ta.getString(R.styleable.DiamondBackgroundTextView_dbv_text);
        int backgroundColor = ta.getColor(R.styleable.DiamondBackgroundTextView_dbv_background_color, 0x80E5C8C8);
        int textColor = ta.getColor(R.styleable.DiamondBackgroundTextView_dbv_text_color, 0xFF7F101D);
        int textSize = ta.getDimensionPixelSize(R.styleable.DiamondBackgroundTextView_dbv_text_size, 100);
        int padding = ta.getDimensionPixelSize(R.styleable.DiamondBackgroundTextView_dbv_padding, 10);
        ta.recycle();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStyle(Paint.Style.FILL);
        if (TextUtils.isEmpty(text)) {
            throw new IllegalArgumentException("no text");
        }
        arr = text.toCharArray();
        initBackgroundRect(padding);
        initBaseline();
        initDiamondPath();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    /**
     * 决定 view 的高度
     *
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int result;
        if (heightMode == MeasureSpec.EXACTLY) {
            result = heightSize;
        } else {
            result = backgroundRect.height();
            if (heightMode == MeasureSpec.AT_MOST) {
                // 要求的高度不能超过父类给的高度
                result = Math.min(result, heightSize);
            }
        }
        return result;
    }

    /**
     * 决定 view 的宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int result;
        if (widthMode == MeasureSpec.EXACTLY) {
            result = widthSize;
        } else {
            float v = (arr.length - 1) * (1 - OFFSET_PERCENT) + 1;
            result = (int) (v * backgroundRect.width());
            if (widthMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, widthSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawColor(Color.GREEN);
        for (int i = 0; i < arr.length; i++) {
            canvas.save();
            canvas.translate(backgroundRect.width() * (1 - OFFSET_PERCENT) * i, 0);
            canvas.drawPath(path, backgroundPaint);
            canvas.drawText(String.valueOf(arr[i]), baseLineX, baseLineY, textPaint);
            canvas.restore();
        }
    }

    private void initDiamondPath() {
        path.reset();
        path.moveTo(0, backgroundRect.centerY());
        path.lineTo(backgroundRect.centerX(), 0);
        path.lineTo(backgroundRect.width(), backgroundRect.centerY());
        path.lineTo(backgroundRect.centerX(), backgroundRect.height());
        path.close();
    }

    private void initBackgroundRect(int padding) {
        int side = getSide();
        backgroundRect.left = 0;
        backgroundRect.top = 0;
        backgroundRect.right = (padding * 2 + side) * 2;
        backgroundRect.bottom = (padding * 2 + side) * 2;
    }

    private void initBaseline() {
        Paint.FontMetricsInt fm = textPaint.getFontMetricsInt();
        baseLineX = backgroundRect.centerX();
        baseLineY = backgroundRect.centerY() - (fm.top + fm.bottom) / 2;
    }

    private int getSide() {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            textPaint.getTextBounds(String.valueOf(arr[i]), 0, 1, bounds);
            int max = Math.max(bounds.width(), bounds.height());
            if (max > result) {
                result = max;
            }
        }
        return result;
    }
}
