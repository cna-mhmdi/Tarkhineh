package com.nyco.tarkhineh

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nyco.tarkhineh.databinding.ActivityPrivacyBinding
import java.util.ArrayList

class PrivacyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrivacyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrivacyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.privacyToolbar)
        supportActionBar?.title = null

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.privacyToolbar.setNavigationIcon(R.drawable.arrow_left1)

        val adapter = ExpandableListAdapter(this)
        binding.expandablePrivacy.setAdapter(adapter)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}