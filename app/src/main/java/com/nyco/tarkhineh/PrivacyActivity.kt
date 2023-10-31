package com.nyco.tarkhineh

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.nyco.tarkhineh.databinding.ActivityPrivacyBinding
import java.util.ArrayList

class PrivacyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrivacyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ExpandableListAdapter(this)
        binding.expandablePrivacy.setAdapter(adapter)

        setSupportActionBar(binding.privacyToolbar)
        supportActionBar?.title = null

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.privacyToolbar.setNavigationIcon(R.drawable.arrow_left1)



    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}