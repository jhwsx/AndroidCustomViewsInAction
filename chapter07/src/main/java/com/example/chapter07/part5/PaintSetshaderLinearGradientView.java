package com.example.chapter07.part5;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter07.R;

/**
 * @author wangzhichao
 * @date 2019/09/22
 */
public class PaintSetshaderLinearGradientView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private LinearGradient linearGradient;
    private Type type = Type.DOUBLE_COLOR;
    private Coordinate coordinateX0 = Coordinate.ZERO;
    private Coordinate coordinateY0 = Coordinate.ZERO;
    private Coordinate coordinateX1 = Coordinate.ZERO;
    private Coordinate coordinateY1 = Coordinate.ZERO;

    public PaintSetshaderLinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PaintSetshaderLinearGradientView);
        int index = typedArray.getInt(R.styleable.PaintSetshaderLinearGradientView_pslv_type, -1);
        if (index >= 0) {
            type = mapType(index);
        }
        int x0Coor = typedArray.getInt(R.styleable.PaintSetshaderLinearGradientView_pslv_x0, -1);
        if (x0Coor >= 0) {
            coordinateX0 = mapCoordinate(x0Coor);
        }
        int y0Coor = typedArray.getInt(R.styleable.PaintSetshaderLinearGradientView_pslv_y0, -1);
        if (y0Coor >= 0) {
            coordinateY0 = mapCoordinate(y0Coor);
        }
        int x1Coor = typedArray.getInt(R.styleable.PaintSetshaderLinearGradientView_pslv_x1, -1);
        if (x1Coor >= 0) {
            coordinateX1 = mapCoordinate(x1Coor);
        }
        int y1Coor = typedArray.getInt(R.styleable.PaintSetshaderLinearGradientView_pslv_y1, -1);
        if (y1Coor >= 0) {
            coordinateY1 = mapCoordinate(y1Coor);
        }

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (linearGradient == null) {
            if (Type.DOUBLE_COLOR.equals(type)) {
                linearGradient = new LinearGradient(mapPx(coordinateX0), mapPx(coordinateY0), mapPx(coordinateX1), mapPx(coordinateY1), 0xFFFF0000, 0xFF00FF00, Shader.TileMode.CLAMP);
            } else {
                int[] colors = {0xffff0000, 0xff00ff00, 0xff0000ff, 0xffffff00, 0xff00ffff};
                float[] positions = {0f, 0.2f, 0.4f, 0.6f, 1f};
                linearGradient = new LinearGradient(mapPx(coordinateX0), mapPx(coordinateY0), mapPx(coordinateX1), mapPx(coordinateY1), colors, positions, Shader.TileMode.CLAMP);
            }
        }
        paint.setShader(linearGradient);
        if (drawText) {
            paint.setTextSize(50);
            canvas.drawText("欢迎光临启舰的blog",0,200,paint);
            return;
        }
        if (smallRect) {
            canvas.drawRect(getWidth()/3,getHeight()/3,getWidth()*2/3, getHeight()*2/3, paint);
        } else {
            canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        }

    }

    private Type mapType(int index) {
        switch (index) {
            case 0:
                return Type.DOUBLE_COLOR;
            case 1:
            default:
                return Type.MULTI_COLOR;
        }
    }

    private Coordinate mapCoordinate(int coor) {
        switch (coor) {
            case 0:
            default:
                return Coordinate.ZERO;
            case 1:
                return Coordinate.HALF_WIDTH;
            case 2:
                return Coordinate.WIDTH;
            case 3:
                return Coordinate.HALF_HEIGHT;
            case 4:
                return Coordinate.HEIGHT;

        }
    }

    private float mapPx(Coordinate coordinate) {
        switch (coordinate) {
            case ZERO:
                return 0;
            case HALF_WIDTH:
                return getWidth() / 2;
            case WIDTH:
                return getWidth();
            case HALF_HEIGHT:
                return getHeight() / 2;
            case HEIGHT:
                return getHeight();
            default:
                return 0;
        }
    }
    private boolean smallRect;
    public void setSmallRect(boolean smallRect) {
        this.smallRect = smallRect;
        invalidate();
    }

    private boolean drawText;

    public void setDrawText(boolean drawText) {
        this.drawText = drawText;
        invalidate();
    }

    enum Coordinate {
        ZERO(0),
        HALF_WIDTH(1),
        WIDTH(2),
        HALF_HEIGHT(3),
        HEIGHT(4);
        private int coordinate;

        Coordinate(int coordinate) {
            this.coordinate = coordinate;
        }
    }

    enum Type {
        DOUBLE_COLOR(0),
        MULTI_COLOR(1);
        private int type;

        Type(int type) {
            this.type = type;
        }
    }
}

/**
 * 总结：
 * 1，需要注意的是 LinearGradient 的颜色参数必须用0xAARRGGBB的完整16进制的颜色样式表示,否则就不会有任何效果。
 * 2, 多种颜色构造时，colors 数组和 positions 数组的长度必须一致，否则抛异常：
 * java.lang.IllegalArgumentException: color and position arrays must be of equal length。
 * 3, LinearGradient 的 X 轴和 Y 轴公用一个填充参数。
 */
