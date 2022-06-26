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
class PathShapeView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val drawable: ShapeDrawable
    private val path: Path

    init {
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(100f, 0f)
        path.lineTo(100f, 100f)
        path.lineTo(0f, 100f)
        path.close()
        val pathShape = PathShape(path, 100f, 100f)
        //        PathShape pathShape = new PathShape(path, 100, 200);
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