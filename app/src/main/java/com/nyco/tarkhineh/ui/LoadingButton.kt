package com.nyco.tarkhineh.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.animation.ValueAnimator
import kotlin.math.min

class LoadingButton(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var isButtonState = true
    private var progressFraction = 0.0f
    private val buttonPaint = Paint()
    private val progressPaint = Paint()

    init {
        buttonPaint.color = Color.parseColor("#417F56")
        progressPaint.color = Color.BLUE
        progressPaint.style = Paint.Style.STROKE
        progressPaint.strokeWidth = 8f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isButtonState) {
            // Draw the button appearance
            val buttonRect = Rect(0, 0, width, height)
            canvas.drawRect(buttonRect, buttonPaint)
        } else {
            // Draw the progress bar appearance
            val centerX = width / 2
            val centerY = height / 2
            val radius = min(width, height) / 2 - progressPaint.strokeWidth
            val progressRect = RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
            canvas.drawArc(progressRect, -90f, 360 * progressFraction, false, progressPaint)
        }
    }

    fun startProgressAnimation() {
        if (!isButtonState) return // Animation is already in progress
        isButtonState = false
        val animator = ValueAnimator.ofFloat(1.0f, 0.0f)
        animator.duration = 1000
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.addUpdateListener { animation ->
            progressFraction = animation.animatedValue as Float
            invalidate()
        }
        animator.start()
    }
}
