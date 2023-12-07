package com.nyco.tarkhineh.ktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.adapters.AboutUsAdapter
import com.nyco.tarkhineh.databinding.ActivityAboutUsBinding
import com.nyco.tarkhineh.model.Branches

class AboutUsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AboutUsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val branchList = listOf(
            Branches("شعبه اکباتان", "آدرس: شهرک اکباتان، فاز ۳، مجتمع تجاری کوروش، طبقه سوم", "۰۲۱-۵۴۸۹۱۲۵۰-۵۱", "ساعت کاری: همه\u200Cروزه از ساعت"),
            Branches("شعبه چالوس", "چالوس، خیابان ۱۷ شهریور، بعد کوچه کوروش، جنب داروخانه دکتر میلانی", "0911", "3"),
            Branches("شعبه چالوس", "خیابان اقدسیه ، نرسیده به میدان خیام، پلاک ۸", "0913", "3")
        )

        recyclerView = binding.recyclerAboutUs
        adapter = AboutUsAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter.addBranch(branchList)

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