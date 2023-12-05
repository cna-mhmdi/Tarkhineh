package com.nyco.tarkhineh.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.model.Branches

class AboutUsAdapter(private val items: List<Branches>, private val context: Context)
    :RecyclerView.Adapter<AboutUsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_about_us_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[position]

        holder.branchNameTextView.text = currentItem.branchName
        holder.branchLocationTextView.text = currentItem.branchLocation
        holder.branchPhoneTextView.text = currentItem.branchPhone
        holder.branchWorkTimeTextView.text = currentItem.branchWorkTime

        holder.btnBranch.setOnClickListener {
            Toast.makeText(context,currentItem.branchName,Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val branchNameTextView: TextView = itemView.findViewById(R.id.nameBranch)
        val branchLocationTextView: TextView = itemView.findViewById(R.id.locationBranch)
        val branchPhoneTextView: TextView = itemView.findViewById(R.id.phoneBranch)
        val branchWorkTimeTextView: TextView = itemView.findViewById(R.id.workTimeBranch)
        val btnBranch: Button = itemView.findViewById(R.id.btn_Branch)
    }
}