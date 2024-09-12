package com.practice.edgeecomviewmodel.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practice.edgeecomviewmodel.R
import com.practice.edgeecomviewmodel.data.products.Product
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: CategoryAdapter
    private lateinit var viewModel: MainViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]


        recyclerView = view.findViewById(R.id.categoryRec)
        progressBar = view.findViewById(R.id.progressBarCat)


        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.addItemDecoration(ItemSpacingDecoration(horizontal = 4, vertical = 14))
        recyclerView.setPadding(0, 0, 0, 0)

        handleLoading()

        lifecycleScope.launch {
            viewModel.categories.collect { response ->

                Log.d("Category", "onViewCreated: $response")
                adapter = CategoryAdapter(response) { item ->
                    navigateToDetails(item)

                }
                recyclerView.adapter = adapter
            }
        }


//        This is for nested Recycler View..........................................................................................................
//        val parentRecyclerView: RecyclerView = view.findViewById(R.id.rvParent)
//
//        val sampleItems = listOf(
//            Product("electronics", "Item 1", 1, "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg", 109.95, "Title 1"  ),
//            Product("jewelery", "Item 2", 2, "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg", 109.95, "Title 2" ),
//            Product("men's clothing", "Item 3", 3, "https://fakestoreapi.com/img/71YAIFU48IL._AC_UL640_QL65_ML3_.jpg", 109.95, "Title 3")
//        )
//        Nested Recycler Block...............................................................................................................................

    }

    private fun navigateToDetails(item: String) {
        val intent = Intent(requireContext(), CategoryDetails::class.java).apply {
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