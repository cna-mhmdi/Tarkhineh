package com.nyco.tarkhineh.ktx

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.TarkhinehApplication
import com.nyco.tarkhineh.TarkhinehViewModel
import com.nyco.tarkhineh.dataBase.FavoriteFoods
import com.nyco.tarkhineh.databinding.ActivityDetailFoodBinding
import com.nyco.tarkhineh.model.MainFood
import com.nyco.tarkhineh.model.MenuFood

class DetailFoodActivity : AppCompatActivity() {

    companion object {
        const val MOVIE_ID = "movie"
    }

    private lateinit var binding: ActivityDetailFoodBinding

    private lateinit var tarkhinehViewModel: TarkhinehViewModel
    private lateinit var mainFoodDB: FavoriteFoods

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.detailFoodToolbar)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.detailFoodToolbar.setNavigationIcon(R.drawable.arrow_left1)

        val tarkhinehRepository = (application as TarkhinehApplication).tarkhinehRepository
        tarkhinehViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TarkhinehViewModel(tarkhinehRepository) as T
            }
        })[TarkhinehViewModel::class.java]


        if (intent.hasExtra(MOVIE_ID)) {

            val foodItem = intent.getParcelableExtra<Parcelable>(MOVIE_ID)

            when (foodItem) {
                is MainFood -> {
                    binding.txtFoodDesc.text = foodItem.foodDesc
                    binding.txtFoodName.text = foodItem.foodName
                    binding.txtFoodPrice.text = foodItem.foodPrice
                    binding.txtFoodStar.text = foodItem.foodStar

                    mainFoodDB = FavoriteFoods(
                        foodItem.foodName,
                        foodItem.foodDiscount,
                        foodItem.foodPrice,
                        foodItem.foodStar,
                        foodItem.foodDesc,
                        foodItem.isFavorite
                    )

                }

                is MenuFood -> {
                    binding.txtFoodDesc.text = foodItem.foodDesc
                    binding.txtFoodName.text = foodItem.foodName
                    binding.txtFoodPrice.text = foodItem.foodPrice
                    binding.txtFoodStar.text = foodItem.foodStar
                }
            }
        }

        binding.btnAddToFavorite.setOnClickListener {

            Log.d(MOVIE_ID, mainFoodDB.toString())
            tarkhinehViewModel.insertFav(mainFoodDB)

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}