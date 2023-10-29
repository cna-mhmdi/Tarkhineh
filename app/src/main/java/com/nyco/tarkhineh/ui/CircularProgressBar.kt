package com.nyco.tarkhineh.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircularProgressBar(context: Context, attr: AttributeSet) : View(context, attr) {

    private val paint: Paint = Paint()

    private val borderPaint: Paint = Paint()
    private var progress = 40
    private val maxProgress = 120


    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
        paint.color = 0xFF417F56.toInt()

        borderPaint.isAntiAlias = true
        borderPaint.style = Paint.Style.STROKE
        borderPaint.strokeWidth = 1f
        borderPaint.color = Color.GRAY
    }

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2
        val centerY = height / 2
        val radius = (centerX.coerceAtMost(centerY) - paint.strokeWidth / 2)
        val startAngle = -90f
        val sweepAngle = 360 * progress / maxProgress

        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius + 1f / 2, borderPaint)

        canvas.drawArc(
            centerX - radius,
            centerY - radius,
            centerX + radius,
            centerY + radius,
            startAngle, sweepAngle.toFloat(),
            false, paint
        )
    }

    fun setProgress(progress: Int) {
        this.progress = progress
        invalidate()
    }
}