package com.practice.edgeecomviewmodel.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.practice.edgeecomviewmodel.R
import com.practice.edgeecomviewmodel.data.products.Product

class ProductDetails : AppCompatActivity() {
    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var price: TextView
    private lateinit var description: TextView
    private var product: Product? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_details)

        product = intent.getParcelableExtra<Product>("PRODUCT")


        image = findViewById(R.id.image)
        title = findViewById(R.id.title)
        description = findViewById(R.id.description)
        price = findViewById(R.id.price)

        image.load(product?.image) {
            crossfade(true)
        }

        title.text = product?.title
        price.text = "${product?.price} tk"
        description.text = product?.description


    }
}