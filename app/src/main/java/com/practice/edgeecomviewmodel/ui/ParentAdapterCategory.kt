package com.practice.edgeecomviewmodel.ui

import android.icu.text.Transliterator.Position
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practice.edgeecomviewmodel.R
import com.practice.edgeecomviewmodel.data.model.CategoryProduct
import com.practice.edgeecomviewmodel.data.products.Product

class ParentAdapter(
    private var category: List<CategoryProduct>,
    private val onProductClick: (Product) -> Unit
) :
    RecyclerView.Adapter<ParentAdapter.ParentViewHolder>() {

    class ParentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var productsCategory: TextView = view.findViewById(R.id.tvCategoryTitle)
        var childRecyclerView: RecyclerView = view.findViewById(R.id.rvChildItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        var view =
            LayoutInflater.from(parent.context)
            .inflate(R.layout.parent_item_category, parent, false)
        return ParentViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {

        val category = category[position]

        holder.productsCategory.text = category.categoryName

//        holder.childRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context, RecyclerView.HORIZONTAL,false)
        holder.childRecyclerView.layoutManager = GridLayoutManager(holder.itemView.context, 3)
        holder.childRecyclerView.adapter = ChildAdapter(category.productList, onProductClick)

//        holder.apply {
//            productsCategory.text = category.categoryName?.capitalize() ?:
//            childRecyclerView.layoutManager = GridLayoutManager(holder.itemView.context, 3)
//            childRecyclerView.adapter = ChildAdapter(category.productList)
//        }

    }

    override fun getItemCount(): Int {
        return category.size
    }

    // Function to update the adapter with new data
    fun addNewCategory(response: List<CategoryProduct>) {
        category = response
        notifyDataSetChanged()
    }
}
