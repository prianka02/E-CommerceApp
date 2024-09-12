package com.practice.edgeecomviewmodel.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practice.edgeecomviewmodel.R
import com.practice.edgeecomviewmodel.data.products.Product

class ParentAdapter(private val products: List<Product>) : RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    class ParentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productsCategory: TextView = view.findViewById(R.id.tvCategoryTitle)
        val childRecyclerView: RecyclerView = view.findViewById(R.id.rvChildItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parent_item_category, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val category = products[position]
        holder.productsCategory.text = category.title

        // Set up the child RecyclerView
        holder.childRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, RecyclerView.HORIZONTAL, false)
//        holder.childRecyclerView.adapter = ChildAdapter(category.items)
    }

    override fun getItemCount(): Int {
        return products.size
    }
}
