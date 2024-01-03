package com.nyco.tarkhineh.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.model.MenuFood
import com.nyco.tarkhineh.model.SearchFood

class SearchAdapter(
    private val clickListener: SearchFoodsClickListener
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val searchFood = mutableListOf<SearchFood>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_search_rec_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val searchFoods = searchFood[position]
        holder.bind(searchFoods)
        holder.itemView.setOnClickListener { clickListener.onSearchFoodsClick(searchFoods) }
    }

    override fun getItemCount(): Int {
        return searchFood.size
    }

    fun addSearchFood(searchFood: List<SearchFood>) {
        this.searchFood.clear()
        this.searchFood.addAll(searchFood)
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodName: TextView by lazy { itemView.findViewById(R.id.txt_SearchFoodName) }
        private val foodCat: TextView by lazy { itemView.findViewById(R.id.txt_SearchFoodCat) }

        fun bind(searchFood: SearchFood) {
            foodName.text = searchFood.foodName
            foodCat.text = searchFood.foodCategory
        }
    }

    interface SearchFoodsClickListener {
        fun onSearchFoodsClick(searchFood: SearchFood)
    }
}