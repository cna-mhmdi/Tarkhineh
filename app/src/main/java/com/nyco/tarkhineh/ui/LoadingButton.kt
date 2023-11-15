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
import android.annotation.SuppressLint
import android.graphics.drawable.PaintDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Handler
import android.os.Looper
import android.view.animation.LinearInterpolator
import com.nyco.tarkhineh.R
import kotlin.math.min

class LoadingButton(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var isButtonState = true
    private var progressFraction = 0.0f
    private val buttonPaint = Paint()
    private val progressPaint = Paint()
    private var sweepAngle = 20f // Initial value for sweepAngle
    private var isIncreasing = true

    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 100L // Update interval in milliseconds (0.1 seconds)

    init {
        buttonPaint.color = Color.parseColor("#417F56")
        progressPaint.color = Color.BLUE
        progressPaint.style = Paint.Style.STROKE
        progressPaint.strokeWidth = 8f

        // Start the loop
        startLoop()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isButtonState) {
            // Draw the button appearance
            val buttonRect = Rect(0, 0, width, height)
            canvas.drawRect(buttonRect, buttonPaint)
        } else {
            // Draw the indeterminate circular progress bar (spinner)
            val centerX = width / 2
            val centerY = height / 2
            val radius = min(width, height) / 2 - progressPaint.strokeWidth

            // Rotate the canvas based on progressFraction
            canvas.rotate(360 * progressFraction, centerX.toFloat(), centerY.toFloat())

            progressPaint.color = Color.parseColor("#417F56") // Set the color to #417F56

            val useCenter = false

            canvas.drawArc(centerX - radius, centerY - radius, centerX + radius, centerY + radius,
                -45f, sweepAngle, useCenter, progressPaint)
        }
    }

    fun startProgressAnimation() {
        if (!isButtonState) return // Animation is already in progress
        isButtonState = false

        val animator = ValueAnimator.ofFloat(0f, 1f)
        animator.duration = 500
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation ->
            progressFraction = animation.animatedValue as Float
            invalidate()
        }

        animator.repeatMode = ValueAnimator.RESTART
        animator.repeatCount = ValueAnimator.INFINITE

        animator.start()
    }

    private fun startLoop() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                updateSweepAngle()
                invalidate()
                handler.postDelayed(this, updateInterval)
            }
        }, updateInterval)
    }

    private fun updateSweepAngle() {
        val increment = 10f

        if (isIncreasing) {
            sweepAngle += increment
            if (sweepAngle >= 360f) {
                sweepAngle = 360f
                isIncreasing = false
            }
        } else {
            sweepAngle -= increment
            if (sweepAngle <= 20f) { // Change this threshold as needed
                sweepAngle = 20f
                isIncreasing = true
            }
        }
    }
}
