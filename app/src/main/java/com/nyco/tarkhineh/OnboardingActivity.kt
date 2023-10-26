package com.nyco.tarkhineh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.nyco.tarkhineh.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityOnboardingBinding

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPagerAdapter = ViewPagerAdapter(this)
        binding.onBoardingViewPager.adapter = viewPagerAdapter

        binding.onBoardingBtn.setOnClickListener {
            if (getItem(0) < 2) {
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