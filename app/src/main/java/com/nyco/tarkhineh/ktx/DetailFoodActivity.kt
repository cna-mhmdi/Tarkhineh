package com.nyco.tarkhineh.ktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.databinding.ActivityDetailFoodBinding

class DetailFoodActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailFoodBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.detailFoodToolbar)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.detailFoodToolbar.setNavigationIcon(R.drawable.arrow_left1)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}