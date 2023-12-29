package com.nyco.tarkhineh.ktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.adapters.MenuViewPagerAdapter
import com.nyco.tarkhineh.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.menuToolbar)
        supportActionBar?.title = null

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.menuToolbar.setNavigationIcon(R.drawable.arrow_left1)


        val adapter = MenuViewPagerAdapter(supportFragmentManager)
        binding.menuViewPager.adapter = adapter
        binding.manuTabLayout.setupWithViewPager(binding.menuViewPager)
    }
}