package com.nyco.tarkhineh.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.model.Shopping

class ShoppingCardAdapter() : RecyclerView.Adapter<ShoppingCardAdapter.ShoppingCardViewHolder>() {

    private val items = mutableListOf<Shopping>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_shopping_card_item, parent, false)
        return ShoppingCardViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingCardViewHolder, position: Int) {
        val foodItems = items[position]
        holder.bind(foodItems)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItems(items: List<Shopping>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ShoppingCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val foodName: TextView by lazy { itemView.findViewById(R.id.food_name) }
        private val foodPrice: TextView by lazy { itemView.findViewById(R.id.food_price) }

        fun bind(shop: Shopping) {
            foodName.text = shop.foodName
            foodPrice.text = shop.foodPrice
        }
    }
}