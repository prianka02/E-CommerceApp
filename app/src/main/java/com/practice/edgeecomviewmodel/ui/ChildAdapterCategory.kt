package com.practice.edgeecomviewmodel.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.practice.edgeecomviewmodel.R
import com.practice.edgeecomviewmodel.data.products.Product

class ChildAdapter(
    private var categoryProducts: List<Product>,
    private val onItemClick: (Product) -> Unit
):
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {

    class ChildViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemName: TextView = view.findViewById(R.id.tvItemName)
        var itemImage: ImageView = view.findViewById(R.id.ivItemImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_item_category, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        val product = categoryProducts[position]
        holder.itemName.text = product.title
        holder.itemImage.load(product.image){
            crossfade(true)
        }
    }

    override fun getItemCount(): Int {
        return categoryProducts.size
    }

    fun addNewProduct(response: List<Product>) {
        categoryProducts = response
        notifyDataSetChanged()
    }

}
