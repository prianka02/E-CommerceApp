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
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
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


//        Image Slider Added
        val imageList = ArrayList<SlideModel>() // Create image list
        imageList.add(SlideModel("https://4.bp.blogspot.com/-1794Amzwdjs/VCmSUBztq9I/AAAAAAACUe4/0DCYQMdaLt8/s1600/fotos%2Bde%2Bpaisajes%2Bnaturales.jpg", "The animal population decreased by 58 percent in 42 years.", ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel("https://images2.fanpop.com/image/photos/13400000/beautiful-Spring-Day-on-a-country-road-spring-13476165-1600-1200.jpg", "Elephants and tigers may become extinct.", ScaleTypes.CENTER_CROP))
        imageList.add(SlideModel("https://img.buzzfeed.com/buzzfeed-static/static/2017-12/21/12/asset/buzzfeed-prod-fastlane-02/sub-buzz-32101-1513875602-1.jpg?crop=1600:1067%3B0%2C0&resize=720:720", "And people do that.", ScaleTypes.CENTER_CROP))

        val imageSlider = view.findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(imageList)

        // Initialize ViewModel
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