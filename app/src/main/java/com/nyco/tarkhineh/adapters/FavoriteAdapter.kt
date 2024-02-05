package com.nyco.tarkhineh.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.model.FavoriteFoods

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var favoriteList: List<FavoriteFoods> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFavoriteItem = favoriteList[position]
        holder.bind(currentFavoriteItem)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    fun setFavoriteList(newFavoriteList: List<FavoriteFoods>) {
        // Use a HashSet to filter out duplicate items
        favoriteList = LinkedHashSet(newFavoriteList).toList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val foodName: TextView by lazy { itemView.findViewById(R.id.txt_foodName) }
        private val foodDiscount: TextView by lazy { itemView.findViewById(R.id.txt_food_discount) }
        private val foodStar: TextView by lazy { itemView.findViewById(R.id.txt_starCount) }
        private val foodPrice: TextView by lazy { itemView.findViewById(R.id.txt_food_price) }

        fun bind(favoriteItem: FavoriteFoods) {
            foodName.text = favoriteItem.foodName
            foodDiscount.text = favoriteItem.foodDiscount
            foodStar.text = favoriteItem.foodStar
            foodPrice.text = favoriteItem.foodPrice

        }
    }
}
