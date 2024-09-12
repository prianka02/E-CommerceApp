package com.practice.edgeecomviewmodel.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practice.edgeecomviewmodel.R
import com.practice.edgeecomviewmodel.data.products.Product
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: ProductAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        recyclerView = view.findViewById(R.id.recyclerView)
        progressBar = view.findViewById(R.id.progressBar)
//        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.addItemDecoration(ItemSpacingDecoration(horizontal = 4, vertical = 8))
        recyclerView.setPadding(0, 0, 0, 0)

//        this will handle the loading
        handleLoading()

//       Call the ProductResponse from viewModel and set the Product Response in the Adapter
        lifecycleScope.launch {
            viewModel.productsResponse.collect { response ->
                if (response != null) {
                    adapter = ProductAdapter(response) { item ->
                         navigateToDetails(item)

                    }
                    recyclerView.adapter = adapter
                }
            }
        }

        lifecycleScope.launch {
            viewModel.createProductResponse.collect { response ->
                if (response != null) {
                    adapter.addNewProduct(response)

                    recyclerView.adapter = adapter
                }
            }
        }

    }

    private fun navigateToDetails(item: Product) {
        val intent = Intent(requireContext(), ProductDetails::class.java).apply {
            putExtra("PRODUCT", item)
        }

        startActivity(intent)
    }


    private fun handleLoading() {
        lifecycleScope.launch {
            viewModel.isLoading.collect { isLoading ->
                if (isLoading) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

 }