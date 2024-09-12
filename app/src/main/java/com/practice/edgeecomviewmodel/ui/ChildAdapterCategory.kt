package com.practice.edgeecomviewmodel.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practice.edgeecomviewmodel.R
import com.practice.edgeecomviewmodel.data.products.Product

class ChildAdapter(private val items: List<Product>) : RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    class ChildViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemName: TextView = view.findViewById(R.id.tvItemName)
        val itemImage: ImageView = view.findViewById(R.id.ivItemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item_category, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val item = items[position]
//        holder.itemName.text = item.name
        // Use Coil, Glide, or Picasso to load images into ImageView
        // e.g., Coil: holder.itemImage.load(item.imageUrl)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
