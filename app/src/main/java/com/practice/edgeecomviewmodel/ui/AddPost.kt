package com.practice.edgeecomviewmodel.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.practice.edgeecomviewmodel.R
import com.practice.edgeecomviewmodel.data.products.Product
import kotlinx.coroutines.launch

class AddPost : AppCompatActivity() {

    private lateinit var title: EditText
    private lateinit var price: EditText
    private lateinit var category: EditText
    private lateinit var description: EditText
    private lateinit var submitBtn: Button
    private lateinit var viewModel: MainViewModel
//    private lateinit var adapter: ProductAdapter
//    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_post)

        title = findViewById(R.id.productTitle)
        price = findViewById(R.id.productPrice)
        category = findViewById(R.id.productCategory)
        description = findViewById(R.id.productDescription)
        submitBtn = findViewById(R.id.buttonSubmit)


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val image = "https://picsum.photos/id/237/200/300"


        submitBtn.setOnClickListener {
            val  productTitle = title.text.toString()
            val productPrice = price.text.toString()
            val productCategory = category.text.toString()
            val productDescription = description.text.toString()
//            adapter = ProductAdapter(mutableListOf()) {}
            Toast.makeText(this@AddPost, "Product Added", Toast.LENGTH_SHORT).show()


            viewModel.onCreateProduct(productTitle, productPrice, productCategory, productDescription, image)

            lifecycleScope.launch {
                viewModel.createProductResponse.collect{ response ->
                    if(response != null){
                        Toast.makeText(this@AddPost, "Product Added", Toast.LENGTH_SHORT).show()
//                        adapter.addNewProduct(response)
                        navigateToDetails()

                    }

                }
            }

        }

        }
    private fun navigateToDetails() {
        val intent = Intent(this, HomeFragment::class.java).apply {
        }

        startActivity(intent)
    }


    }
