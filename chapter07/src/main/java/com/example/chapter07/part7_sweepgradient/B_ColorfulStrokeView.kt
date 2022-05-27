package com.example.chapter07.part7_sweepgradient

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import com.example.chapter07.R
import com.example.common.dp

class B_ColorfulStrokeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rectF: RectF = RectF()
    private val defaultStrokeWidth: Float = 5.dp
    private val defaultRadius: Float = 10.dp
    private var strokeWidth: Float
    private var radius: Float
    private var rotateStart = 225
    private var currDegree = 0
    private var animStarted = false
    private var isAutoStart = true
    private val colors: IntArray
    private val positions: FloatArray
    private var sweepGradient: SweepGradient
    private val mtx = Matrix()
    private var valueAnimator: ValueAnimator? = null

    init {
        strokeWidth = defaultStrokeWidth
        radius = defaultRadius
        colors = IntArray(13)
        colors[0] = Color.parseColor("#FF000000")
        colors[1] = Color.parseColor("#FFFBC85B")
        colors[2] = Color.parseColor("#FFFBC85B")
        colors[3] = Color.parseColor("#FFF1448A")
        colors[4] = Color.parseColor("#FF8925FF")
        colors[5] = Color.parseColor("#FF6DCDFC")
        colors[6] = Color.parseColor("#FF000000")
        colors[7] = Color.parseColor("#FF000000")
        colors[8] = Color.parseColor("#FFFBC85B")
        colors[9] = Color.parseColor("#FFFBC85B")
        colors[10] = Color.parseColor("#FF6DCDFC")
        colors[11] = Color.parseColor("#FF8925FF")
        colors[12] = Color.parseColor("#FFF1448A")
        positions = floatArrayOf(0.25f,
            0.251f,
            0.37f,
            0.41f,
            0.46f,
            0.5f,
            0.501f,
            0.75f,
            0.751f,
            0.87f,
            0.91f,
            0.96f,
            1.0f)
        val cx = (measuredWidth / 2).toFloat()
        val cy = (measuredHeight / 2).toFloat()
        sweepGradient = SweepGradient(cx, cy, colors, positions)
        parseTypeArray(context, attrs)

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokeWidth
    }

    public override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        val cx = (measuredWidth / 2).toFloat()
        val cy = (measuredHeight / 2).toFloat()
        sweepGradient = SweepGradient(cx, cy, colors, positions)
        paint.shader = sweepGradient
        super.onSizeChanged(w, h, oldw, oldh)
    }

    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        rectF.left = strokeWidth / 2
        rectF.top = strokeWidth / 2
        rectF.right = measuredWidth.toFloat() - strokeWidth / 2
        rectF.bottom = measuredHeight.toFloat() - strokeWidth / 2
        if (isAutoStart) {
            startAnim()
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        mtx.reset()
        mtx.setRotate(currDegree.toFloat(),
            (measuredWidth / 2).toFloat(),
            (measuredHeight / 2).toFloat())
        sweepGradient.setLocalMatrix(mtx)
        canvas.drawRoundRect(rectF, radius, radius, paint)
    }

    fun startAnim() {
        if (!animStarted) {
            animStarted = true
            valueAnimator = ValueAnimator.ofInt(rotateStart, 360 + rotateStart)
            valueAnimator!!.duration = 2000L
            valueAnimator!!.interpolator = LinearInterpolator()
            valueAnimator!!.repeatCount = ValueAnimator.INFINITE
            valueAnimator!!.addUpdateListener { animation ->
                currDegree = animation.animatedValue as Int
                invalidate()
            }
            valueAnimator!!.start()
        }
    }

    private fun parseTypeArray(var1: Context, var2: AttributeSet?) {
        val ta = var1.obtainStyledAttributes(var2, R.styleable.B_ColorfulStrokeView)
        isAutoStart = ta.getBoolean(R.styleable.B_ColorfulStrokeView_is_auto_start, true)
        rotateStart = ta.getInteger(R.styleable.B_ColorfulStrokeView_rotate_start, 225)
        strokeWidth =
            ta.getDimension(R.styleable.B_ColorfulStrokeView_stroke_width, defaultStrokeWidth)
        radius = ta.getDimension(R.styleable.B_ColorfulStrokeView_stroke_radius, defaultRadius)
        ta.recycle()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (valueAnimator != null) {
            valueAnimator!!.removeAllUpdateListeners()
            valueAnimator!!.cancel()
            valueAnimator = null
        }
    }

}