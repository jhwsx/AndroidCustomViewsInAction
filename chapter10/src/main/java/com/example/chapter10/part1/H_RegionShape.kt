package com.example.chapter10.part1

import android.graphics.*
import android.graphics.drawable.shapes.Shape

/**
 * @author wangzhichao
 * @date 2019/10/12
 */
class H_RegionShape(region: Region?) : Shape() {
    private val mRegion: Region?

    init {
        assert(region != null)
        mRegion = region
    }

    override fun draw(canvas: Canvas, paint: Paint) {
        val iterator = RegionIterator(mRegion)
        val r = Rect()
        while (iterator.next(r)) {
            canvas.drawRect(r, paint)
        }
    }

}