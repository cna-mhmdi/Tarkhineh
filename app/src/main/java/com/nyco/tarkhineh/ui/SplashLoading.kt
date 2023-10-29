package com.nyco.tarkhineh.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomLoadingWidget : View {

    /*
    this section has a problem that need to be solve later
    the circles need to change the color to white every
    time they get big and turn to gray when they are small
    */
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    private val circlePaint = Paint()
    private val circleRadius = 5f
    private val targetRadius = 10f
    private val animationDuration = 500L
    private val numCircles = 3
    private val animators = mutableListOf<ValueAnimator>()
    private val radiusList = MutableList(numCircles) { circleRadius }

    init {
        circlePaint.isAntiAlias = true
        circlePaint.color = Color.WHITE
        circlePaint.style = Paint.Style.FILL

        for (i in 0 until numCircles) {
            val animator = createValueAnimator(i)
            animators.add(animator)
        }
    }

    private fun createValueAnimator(index: Int): ValueAnimator {
        val animator = ValueAnimator.ofFloat(circleRadius, targetRadius, circleRadius)
        animator.duration = animationDuration
        animator.repeatMode = ValueAnimator.RESTART
        animator.repeatCount = ValueAnimator.INFINITE
        animator.startDelay = index * (animationDuration / numCircles)
        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            radiusList[index] = animatedValue

            if (animatedValue == targetRadius) {
                circlePaint.color = Color.LTGRAY
            } else {
                circlePaint.color = Color.WHITE
            }

            postInvalidate()
        }
        animator.start()
        return animator
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        for (i in 0 until numCircles) {
            drawCircle(canvas, i)
        }
    }

    private fun drawCircle(canvas: Canvas, index: Int) {
        val x = (2 * index + 1) * targetRadius
        val y = height / 2f
        val radius = radiusList[index]
        canvas.drawCircle(x, y, radius, circlePaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = (2 * numCircles * targetRadius).toInt()
        val desiredHeight = (2 * targetRadius).toInt()
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val finalWidth = when (widthMode) {
            MeasureSpec.EXACTLY -> widthSize
            MeasureSpec.AT_MOST -> desiredWidth.coerceAtMost(widthSize)
            else -> desiredWidth
        }

        val finalHeight = when (heightMode) {
            MeasureSpec.EXACTLY -> heightSize
            MeasureSpec.AT_MOST -> desiredHeight.coerceAtMost(heightSize)
            else -> desiredHeight
        }

        setMeasuredDimension(finalWidth, finalHeight)
    }
}