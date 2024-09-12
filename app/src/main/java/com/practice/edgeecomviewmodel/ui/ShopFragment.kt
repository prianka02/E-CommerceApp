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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practice.edgeecomviewmodel.R
import com.practice.edgeecomviewmodel.data.products.Product
import com.practice.edgeecomviewmodel.data.products.ProductsResponse
import kotlinx.coroutines.launch

class ShopFragment : Fragment() {
    private lateinit var progressBar: ProgressBar
    private lateinit var parentAdapter: ParentAdapter
    private lateinit var parentRecyclerView: RecyclerView

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


        parentRecyclerView = view.findViewById(R.id.categoryParentRec)
        progressBar = view.findViewById(R.id.progressBarCat)


        parentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
//        recyclerView.addItemDecoration(ItemSpacingDecoration(horizontal = 4, vertical = 14))
//        recyclerView.setPadding(0, 0, 0, 0)

        parentAdapter = ParentAdapter(emptyList()) { item ->

            Log.d("Clicked", item.toString())
//            navigateToDetails(item)
        }
        parentRecyclerView.adapter = parentAdapter
        handleLoading()


        lifecycleScope.launch {
             viewModel.categoryByProducts.collect{data ->
                 parentAdapter.addNewCategory(data)
             }
        }
    }

//    private fun navigateToDetails(item: Product) {
//        val intent = Intent(this, ProductDetails::class.java)
//            .apply {
//                putExtra("PRODUCT", item)
//            }
//        startActivity(intent)
//    }

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