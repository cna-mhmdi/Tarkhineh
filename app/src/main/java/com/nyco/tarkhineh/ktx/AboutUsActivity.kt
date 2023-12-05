package com.nyco.tarkhineh.ktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.adapters.AboutUsAdapter
import com.nyco.tarkhineh.databinding.ActivityAboutUsBinding
import com.nyco.tarkhineh.model.Branches

class AboutUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val branchList = listOf(
            Branches("branch 1", "branch location 1", "0910", "2"),
            Branches("branch 2", "branch location 2", "0911", "3"),
            Branches("branch 3", "branch location 3", "0913", "3")
        )
        recyclerView = binding.recyclerAboutUs
        val adapter = AboutUsAdapter(branchList,this)
        recyclerView.adapter = adapter

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