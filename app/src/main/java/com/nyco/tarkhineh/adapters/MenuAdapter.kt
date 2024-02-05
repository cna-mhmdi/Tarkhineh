package com.nyco.tarkhineh.adapters


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.model.FavoriteFoods
import com.nyco.tarkhineh.model.MenuFood


@Suppress("IMPLICIT_CAST_TO_ANY")
class MenuAdapter(
    private val clickListener: MenuFoodsClickListener,
    private val context: Context
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    private val menuFood = mutableListOf<MenuFood>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_menu_rec_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuFoods = menuFood[position]
        holder.bind(menuFoods)
        holder.itemView.setOnClickListener { clickListener.onMenuFoodsClick(menuFoods) }
    }

    override fun getItemCount(): Int {
        return menuFood.size
    }

    fun addMenuFood(menuFood: List<MenuFood>) {
        this.menuFood.clear()
        this.menuFood.addAll(menuFood)
        notifyDataSetChanged()
    }

    interface MenuFoodsClickListener {
        fun onMenuFoodsClick(menuFood: MenuFood)
    }

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodName: TextView by lazy { itemView.findViewById(R.id.txt_menufoodName) }
        private val foodDiscount: TextView by lazy { itemView.findViewById(R.id.txt_menuFoodDis) }
        private val foodDesc: TextView by lazy { itemView.findViewById(R.id.txt_menuFoodDesc) }
        private val foodPrice: TextView by lazy { itemView.findViewById(R.id.txt_menuFoodPrice) }


        private val addToFavorite: ImageView by lazy { itemView.findViewById(R.id.btn_menuAddToFavorite) }

        fun bind(menuFood: MenuFood) {
            foodName.text = menuFood.foodName
            foodDiscount.text = menuFood.foodDiscount
            foodDesc.text = menuFood.foodDesc
            foodPrice.text = menuFood.foodPrice

            addToFavorite.setOnClickListener {

                // Get the current list of favorite items from SharedPreferences
                val currentFavoriteList = getFavoriteListFromSharedPreferences().toMutableList()

                if (!menuFood.isFavorite) {
                    addToFavorite.setBackgroundColor(Color.RED)

                    // Add the current item to the favorite list
                    val favoriteItem = FavoriteFoods(
                        menuFood.foodName,
                        menuFood.foodDiscount,
                        menuFood.foodPrice,
                        menuFood.foodStar,
                        menuFood.foodDesc,
                        isFavorite = true
                    )
                    currentFavoriteList.add(favoriteItem)
                } else {
                    addToFavorite.setBackgroundColor(Color.TRANSPARENT)

                    // Remove the current item from the favorite list based on its properties
                    currentFavoriteList.removeAll { it.foodName == menuFood.foodName && it.foodDesc == menuFood.foodDesc }
                }
                saveFavoriteListToSharedPreferences(currentFavoriteList)
            }
        }
    }

    private fun getFavoriteListFromSharedPreferences(): List<FavoriteFoods> {
        val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("favoriteList", null)

        // If there is no stored data, return an empty list
        return if (json == null) {
            emptyList()
        } else {
            // Deserialize the JSON string into a list of FavoriteFoods
            gson.fromJson(json, object : TypeToken<List<FavoriteFoods>>() {}.type)
        }
    }

    private fun saveFavoriteListToSharedPreferences(favoriteList: List<FavoriteFoods>) {
        val sharedPreferences =context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(favoriteList)

        // Save the JSON representation of the favorite list
        editor.putString("favoriteList", json)
        editor.apply()
    }
}