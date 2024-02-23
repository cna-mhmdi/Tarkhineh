package com.nyco.tarkhineh.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.min

class LoadingButton(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var isButtonState = true
    private var progressFraction = 0.0f
    private val buttonPaint = Paint()
    private val progressPaint = Paint()
    private var sweepAngle = 20f
    private var isIncreasing = true

    private val handler = Handler(Looper.getMainLooper())
    private val updateInterval = 100L

    init {
        buttonPaint.color = Color.parseColor("#417F56")
        progressPaint.color = Color.BLUE
        progressPaint.style = Paint.Style.STROKE
        progressPaint.strokeWidth = 8f

        startLoop()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isButtonState) {

            val buttonRect = Rect(0, 0, width, height)
            canvas.drawRect(buttonRect, buttonPaint)
        } else {

            val centerX = width / 2
            val centerY = height / 2
            val radius = min(width, height) / 2 - progressPaint.strokeWidth

            canvas.rotate(360 * progressFraction, centerX.toFloat(), centerY.toFloat())

            progressPaint.color = Color.parseColor("#417F56")

            val useCenter = false

            canvas.drawArc(
                centerX - radius, centerY - radius, centerX + radius, centerY + radius,
                -45f, sweepAngle, useCenter, progressPaint
            )
        }
    }

    fun startProgressAnimation() {
        if (!isButtonState) return
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
            if (sweepAngle <= 20f) {
                sweepAngle = 20f
                isIncreasing = true
            }
        }
    }
}
