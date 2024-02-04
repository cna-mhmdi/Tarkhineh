package com.nyco.tarkhineh.ktx

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.databinding.ActivityDetailFoodBinding
import com.nyco.tarkhineh.model.MainFood

class DetailFoodActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "movie"
    }

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




        val movie = intent.getParcelableExtra<MainFood>(MOVIE_ID)
        movie?.run {
            binding.txtFoodDesc.text = movie.foodDesc
            binding.txtFoodName.text = movie.foodName
            binding.txtFoodPrice.text = movie.foodPrice
            binding.txtFoodStar.text = movie.foodStar
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}