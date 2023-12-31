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
import com.nyco.tarkhineh.model.MenuFood


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
                Toast.makeText(
                    context,
                    "${menuFood.foodName} added to favorite",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        }
    }

    interface MenuFoodsClickListener {
        fun onMenuFoodsClick(menuFood: MenuFood)
    }
}