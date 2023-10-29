package com.nyco.tarkhineh

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.nyco.tarkhineh.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private var currentProgress = 40

    private val maxProgress = 120
    private val animationDuration = 250L
    private lateinit var progressAnimator: ValueAnimator

    private lateinit var binding: ActivityOnboardingBinding

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.circleButton.setImageResource(R.drawable.arrow_left)
        binding.circleButton.scaleX = (-1).toFloat()

        progressAnimator = ValueAnimator.ofInt(0, maxProgress).apply {
            duration = animationDuration
            interpolator = LinearInterpolator()
            addUpdateListener { animation ->
                currentProgress = animation.animatedValue as Int
                binding.circleProgressBar.setProgress(currentProgress)
            }
        }

        viewPagerAdapter = ViewPagerAdapter(this)
        binding.onBoardingViewPager.adapter = viewPagerAdapter

        binding.circleButton.setOnClickListener {
            if (getItem(0) < 2) {
                if (!progressAnimator.isRunning) {
                    val newProgress = currentProgress + 40
                    progressAnimator.setIntValues(currentProgress, newProgress)
                    progressAnimator.start()
                    if (newProgress > 80) {
                        binding.circleButton.setImageResource(R.drawable.vector)
                        binding.circleButton.scaleX = (1).toFloat()
                    }
                }
                binding.onBoardingViewPager.setCurrentItem(getItem(1), true)
            } else {
                val intent = Intent(this@OnboardingActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun getItem(int: Int): Int {
        return binding.onBoardingViewPager.currentItem + int
    }

}