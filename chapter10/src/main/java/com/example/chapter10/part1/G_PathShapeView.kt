package com.example.chapter10.part1

import android.content.Context
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.PathShape
import android.graphics.*
import android.util.AttributeSet
import android.view.*
import com.example.common.dp

/**
 * @author wangzhichao
 * @date 2019/10/11
 */
class G_PathShapeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable: ShapeDrawable
    private val path: Path

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        // 在这里点的单位是份 而不是 px。
        path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(100f, 0f)
        path.lineTo(100f, 100f)
        path.lineTo(0f, 100f)
        path.close()
        // stdWidth: 表示标准宽度，即将整个 ShapeDrawable 的宽度分成多少份。
        // stdHeight: 表示标准高度，即将整个 ShapeDrawable 的高度分成多少份。
        val pathShape = PathShape(path, 100f, 100f)
        drawable = ShapeDrawable()
        drawable.shape = pathShape
        drawable.bounds = Rect(0,
            0,
            250.dp.toInt(),
            150.dp.toInt())
        drawable.paint.color = Color.YELLOW
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawable.draw(canvas)
    }

    fun updateStdWidthAndStdHeight(stdWidth: Int, stdHeight: Int) {
        val pathShape = PathShape(path, stdWidth.toFloat(), stdHeight.toFloat())
        drawable.shape = pathShape
        invalidate()
    }
}