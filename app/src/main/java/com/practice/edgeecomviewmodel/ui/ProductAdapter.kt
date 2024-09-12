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
import com.practice.edgeecomviewmodel.data.products.ProductsResponse

class ProductAdapter(
    private var productList:  ProductsResponse,
    private val onItemClick: (Product) -> Unit
):
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val title: TextView = itemView.findViewById(R.id.title)
    val price: TextView = itemView.findViewById(R.id.price)
    val description: TextView = itemView.findViewById(R.id.description)
    val image: ImageView = itemView.findViewById(R.id.image)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
     }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.itemView.setOnClickListener { onItemClick(product) }
        holder.apply {
            title.text = product.title
            price.text = "${product.price} TK"
            description.text = product.description
            image.load(product.image){
                crossfade(true)

            }
            // Assign a unique transition name using the product id or position
            image.transitionName = "product_image_$position"
        }

    }
    fun addNewProduct(response: Product) {
        productList.add(response)
        notifyDataSetChanged()
    }
}