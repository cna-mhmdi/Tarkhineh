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
import com.nyco.tarkhineh.model.NonPersianFood
import com.nyco.tarkhineh.model.PopFoods

class NonPersianFoodAdapter(
    private val clickListener: NonPersianFoodsClickListener,
    private val context: Context
) : RecyclerView.Adapter<NonPersianFoodAdapter.NonPersianFoodViewHolder>() {

    private val nonPersian = mutableListOf<NonPersianFood>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NonPersianFoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return NonPersianFoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: NonPersianFoodViewHolder, position: Int) {
        val nonPersianFood = nonPersian[position]
        holder.bind(nonPersianFood)
        holder.itemView.setOnClickListener { clickListener.onNonPersianFoodClick(nonPersianFood) }
    }

    override fun getItemCount(): Int {
        return nonPersian.size
    }

    fun addNonFood(nonPersianList: List<NonPersianFood>) {
        this.nonPersian.clear()
        this.nonPersian.addAll(nonPersianList)
        notifyDataSetChanged()
    }

    inner class NonPersianFoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodName: TextView by lazy { itemView.findViewById(R.id.txt_foodName) }
        private val foodDiscount: TextView by lazy { itemView.findViewById(R.id.txt_food_discount) }
        private val foodPrice: TextView by lazy { itemView.findViewById(R.id.txt_food_price) }
        private val foodStar: TextView by lazy { itemView.findViewById(R.id.txt_starCount) }

        private val addToFavorite: ImageView by lazy { itemView.findViewById(R.id.img_addToFavorite) }

        fun bind(nonPersianFood: NonPersianFood) {
            foodName.text = nonPersianFood.foodName
            foodDiscount.text = nonPersianFood.foodDiscount
            foodPrice.text = nonPersianFood.foodPrice
            foodStar.text = nonPersianFood.foodStar

            addToFavorite.setOnClickListener {
                Toast.makeText(context, "${nonPersianFood.foodName} added to favorite", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    interface NonPersianFoodsClickListener {
        fun onNonPersianFoodClick(nonPersianFood: NonPersianFood)
    }
}