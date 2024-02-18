package com.nyco.tarkhineh.ktx

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.TarkhinehApplication
import com.nyco.tarkhineh.TarkhinehViewModel
import com.nyco.tarkhineh.adapters.FavoriteAdapter
import com.nyco.tarkhineh.databinding.ActivityFavoriteBinding
import com.nyco.tarkhineh.model.FavoriteFoods

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var recyclerView: RecyclerView
    private val favoriteAdapter = FavoriteAdapter()
    private lateinit var tarkhinehViewModel: TarkhinehViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerSearch
        recyclerView.adapter = favoriteAdapter

        // Assuming you have a function to retrieve the list of favorite items
        val favoriteList = getFavoriteListFromSharedPreferences()

        // Set the distinct list to the adapter
        favoriteAdapter.setFavoriteList(favoriteList)

        val tarkhinehRepository = (application as TarkhinehApplication).tarkhinehRepository
        tarkhinehViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TarkhinehViewModel(tarkhinehRepository) as T
            }
        })[TarkhinehViewModel::class.java]

        tarkhinehViewModel.allFavoriteFoods.observe(this){
            Toast.makeText(this,"$it",Toast.LENGTH_LONG).show()
        }
    }

    // Your function to retrieve the list of favorite items from SharedPreferences
    private fun getFavoriteListFromSharedPreferences(): List<FavoriteFoods> {
        val sharedPreferences = getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("favoriteList", null)

        // If there is no stored data, return an empty list
        if (json == null) {
            return emptyList()
        }

        // Deserialize the JSON string into a list of FavoriteFoods
        return gson.fromJson(json, object : TypeToken<List<FavoriteFoods>>() {}.type)
    }

}