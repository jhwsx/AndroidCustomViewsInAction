package com.example.chapter07.part4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * @author wangzhichao
 * @date 2019/09/21
 */
public class AvatarView extends View {
    private final BitmapShader shader;
    private Type type;
    private static final Type[] sType = {
            Type.CIRCLE,
            Type.RECTANGLE
    };
    private Bitmap bitmap;
    private int rectRadius;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AvatarView);
        int bitmapResId = typedArray.getResourceId(R.styleable.AvatarView_av_src, -1);
        if (bitmapResId == -1) {
            throw new IllegalArgumentException("Unknown img res id");
        }
        bitmap = BitmapFactory.decodeResource(getResources(), bitmapResId);
        int typeIndex = typedArray.getInt(R.styleable.AvatarView_av_type, -1);
        if (typeIndex >= 0) {
            type = sType[typeIndex];
        }
        if (Type.RECTANGLE.equals(type)) {
            rectRadius = typedArray.getInt(R.styleable.AvatarView_av_rectangle_radius, 0);
        }
        typedArray.recycle();

        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int defaultHeight = bitmap.getHeight();
        int defaultWidth = bitmap.getWidth();
        int measuredWidth = widthMode == MeasureSpec.EXACTLY ? widthSize : defaultWidth;
        int measuredHeight = heightMode == MeasureSpec.EXACTLY ? heightSize : defaultHeight;
        int size = Math.min(measuredHeight, measuredWidth);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Matrix matrix = new Matrix();
        float scaleX = getWidth() * 1F / bitmap.getWidth();
        float scaleY = getHeight()* 1F  / bitmap.getHeight();
        float scale = Math.max(scaleX, scaleY);
        matrix.setScale(scale, scale); // 利用 Matrix 缩放图片到要求的尺寸
        shader.setLocalMatrix(matrix); // TODO: 2019/10/13 setLocalMatrix 这里怎么理解？
        paint.setShader(shader);

        if (Type.CIRCLE.equals(type)) {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, paint);
        } else {
            canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), rectRadius, rectRadius, paint);
        }
    }

    public enum Type {
        CIRCLE(0),
        RECTANGLE(1);
        final int typeInt;

        Type(int typeInt) {
            this.typeInt = typeInt;
        }
    }
}
// TODO: 2019/10/13 查看一下github上圆形图像的代码
