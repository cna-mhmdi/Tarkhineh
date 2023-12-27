package com.nyco.tarkhineh.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.model.FoodOffers

class SpecialOfferAdapter(private val clickListener: SpecialOfferClickListener,private val context: Context)
    :RecyclerView.Adapter<SpecialOfferAdapter.SpecialOfferViewHolder>() {

    private val foodOffers = mutableListOf<FoodOffers>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialOfferViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_item,parent,false)
        return SpecialOfferViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpecialOfferViewHolder, position: Int) {
        val offers = foodOffers[position]
        holder.bind(offers)
        holder.itemView.setOnClickListener { clickListener.onOfferClick(offers) }
    }

    override fun getItemCount(): Int {
        return foodOffers.size
    }

    fun addOffers(offersList: List<FoodOffers>){
        this.foodOffers.clear()
        this.foodOffers.addAll(offersList)
        notifyDataSetChanged()
    }

    inner class SpecialOfferViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val foodName : TextView by lazy { itemView.findViewById(R.id.txt_foodName) }
        private val foodDiscount : TextView by lazy { itemView.findViewById(R.id.txt_food_discount) }
        private val foodPrice : TextView by lazy { itemView.findViewById(R.id.txt_food_price) }
        private val foodStar : TextView by lazy { itemView.findViewById(R.id.txt_starCount) }

        private val addToFavorite: ImageView by lazy { itemView.findViewById(R.id.img_addToFavorite) }

        fun bind(offers : FoodOffers) {
            foodName.text = offers.foodName
            foodDiscount.text = offers.foodDiscount
            foodPrice.text = offers.foodPrice
            foodStar.text = offers.foodStar

            addToFavorite.setOnClickListener {
                Toast.makeText(context,"${offers.foodName} added to favorite",Toast.LENGTH_SHORT).show()
            }
        }
    }

    interface SpecialOfferClickListener{
        fun onOfferClick(offers: FoodOffers)
    }
}