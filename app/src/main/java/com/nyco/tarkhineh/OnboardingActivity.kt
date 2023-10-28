package com.nyco.tarkhineh

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.viewpager.widget.ViewPager
import com.nyco.tarkhineh.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private var currentProgress = 40

    private val maxProgress = 120
    private val animationDuration = 500L
    private lateinit var progressAnimator: ValueAnimator

    private lateinit var binding : ActivityOnboardingBinding

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                    progressAnimator.setIntValues(currentProgress, currentProgress + 40)
                    progressAnimator.start()
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