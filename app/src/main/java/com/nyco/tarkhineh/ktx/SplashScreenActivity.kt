package com.nyco.tarkhineh.ktx

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nyco.tarkhineh.TokenManager
import com.nyco.tarkhineh.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val onBoarding = TokenManager.getState(this, "onBoarding")
        val login = TokenManager.getState(this, "login")

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            if (!onBoarding) {
                startActivity(Intent(this@SplashScreenActivity, OnboardingActivity::class.java))
            } else if (!login) {
                startActivity(Intent(this@SplashScreenActivity, LoginActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            }
            finish()
        }
    }
}