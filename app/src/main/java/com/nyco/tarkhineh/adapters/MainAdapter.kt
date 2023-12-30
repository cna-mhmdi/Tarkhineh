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
import com.nyco.tarkhineh.model.MainFood


class MainAdapter(
    private val clickListener: MainFoodsClickListener,
    private val context: Context
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val mainFood = mutableListOf<MainFood>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val mainFoods = mainFood[position]
        holder.bind(mainFoods)
        holder.itemView.setOnClickListener { clickListener.onMainFoodsClick(mainFoods) }
    }

    override fun getItemCount(): Int {
        return mainFood.size
    }

    fun addMainFood(mainFood: List<MainFood>) {
        this.mainFood.clear()
        this.mainFood.addAll(mainFood)
        notifyDataSetChanged()
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodName: TextView by lazy { itemView.findViewById(R.id.txt_foodName) }
        private val foodDiscount: TextView by lazy { itemView.findViewById(R.id.txt_food_discount) }
        private val foodPrice: TextView by lazy { itemView.findViewById(R.id.txt_food_price) }
        private val foodStar: TextView by lazy { itemView.findViewById(R.id.txt_starCount) }

        private val addToFavorite: ImageView by lazy { itemView.findViewById(R.id.img_addToFavorite) }

        fun bind(mainFood: MainFood) {
            foodName.text = mainFood.foodName
            foodDiscount.text = mainFood.foodDiscount
            foodPrice.text = mainFood.foodPrice
            foodStar.text = mainFood.foodStar

            addToFavorite.setOnClickListener {
                Toast.makeText(context, "${mainFood.foodName} added to favorite", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    interface MainFoodsClickListener {
        fun onMainFoodsClick(mainFood: MainFood)
    }
}