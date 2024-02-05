package com.nyco.tarkhineh.ktx

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.databinding.ActivityDetailFoodBinding
import com.nyco.tarkhineh.model.MainFood
import com.nyco.tarkhineh.model.MenuFood

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



        if (intent.hasExtra(MOVIE_ID)){

            val foodItem = intent.getParcelableExtra<Parcelable>(MOVIE_ID)

            when(foodItem){
                is MainFood -> {
                    binding.txtFoodDesc.text = foodItem.foodDesc
                    binding.txtFoodName.text = foodItem.foodName
                    binding.txtFoodPrice.text = foodItem.foodPrice
                    binding.txtFoodStar.text = foodItem.foodStar
                }
                is MenuFood -> {
                    binding.txtFoodDesc.text = foodItem.foodDesc
                    binding.txtFoodName.text = foodItem.foodName
                    binding.txtFoodPrice.text = foodItem.foodPrice
                    binding.txtFoodStar.text = foodItem.foodStar
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}