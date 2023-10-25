package com.nyco.tarkhineh

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nyco.tarkhineh.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            val intent = Intent(this@SplashScreenActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}