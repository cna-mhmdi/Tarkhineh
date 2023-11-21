package com.nyco.tarkhineh.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class RoundedImageView : AppCompatImageView {

    private val paint: Paint = Paint()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        paint.isAntiAlias = true
        paint.color = Color.TRANSPARENT
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f // You can adjust the border width

        setBackgroundColor(Color.TRANSPARENT)
        scaleType = ScaleType.CENTER_CROP
    }

    override fun onDraw(canvas: Canvas) {
        val radius = width.coerceAtMost(height) / 2f
        canvas.drawCircle(width / 2f, height / 2f, radius, paint)

        super.onDraw(canvas)
    }
}