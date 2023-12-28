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
import com.nyco.tarkhineh.model.FoodOffers
import com.nyco.tarkhineh.model.PopFoods

class PopularFoodAdapter(
    private val clickListener: PopularFoodsClickListener,
    private val context: Context
) : RecyclerView.Adapter<PopularFoodAdapter.PopularFoodViewHolder>() {

    private val popFoods = mutableListOf<PopFoods>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularFoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return PopularFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularFoodViewHolder, position: Int) {
        val popFood = popFoods[position]
        holder.bind(popFood)
        holder.itemView.setOnClickListener { clickListener.onPopFoodClick(popFood) }
    }

    override fun getItemCount(): Int {
        return popFoods.size
    }

    fun addPopFood(popFoodList: List<PopFoods>) {
        this.popFoods.clear()
        this.popFoods.addAll(popFoodList)
        notifyDataSetChanged()
    }

    inner class PopularFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodName: TextView by lazy { itemView.findViewById(R.id.txt_foodName) }
        private val foodDiscount: TextView by lazy { itemView.findViewById(R.id.txt_food_discount) }
        private val foodPrice: TextView by lazy { itemView.findViewById(R.id.txt_food_price) }
        private val foodStar: TextView by lazy { itemView.findViewById(R.id.txt_starCount) }

        private val addToFavorite: ImageView by lazy { itemView.findViewById(R.id.img_addToFavorite) }

        fun bind(offers: PopFoods) {
            foodName.text = offers.foodName
            foodDiscount.text = offers.foodDiscount
            foodPrice.text = offers.foodPrice
            foodStar.text = offers.foodStar

            addToFavorite.setOnClickListener {
                Toast.makeText(context, "${offers.foodName} added to favorite", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    interface PopularFoodsClickListener {
        fun onPopFoodClick(popFoods: PopFoods)
    }
}