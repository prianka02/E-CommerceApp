package com.practice.edgeecomviewmodel.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.edgeecomviewmodel.api.ApiClient
import com.practice.edgeecomviewmodel.api.Resource
import com.practice.edgeecomviewmodel.data.payloads.CreateProductPayload
import com.practice.edgeecomviewmodel.data.products.Product
import com.practice.edgeecomviewmodel.data.products.ProductsResponse
import com.practice.edgeecomviewmodel.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    // Saving only NewsResponse instead of the Resource wrapper
    val productsResponse = MutableStateFlow<ProductsResponse?>(null)
    val categoriesResponse = MutableStateFlow<Product?>(null)
    val categories = MutableStateFlow<List<String>>(emptyList())



    // Separate variables to handle loading and error states
    val isLoading = MutableStateFlow(true)

    // Separate variables to handle loading and error states
    val errorMessage = MutableStateFlow<String?>(null)

    val createProductResponse = MutableStateFlow<Product?>(null)

    private val repository = ProductRepository(ApiClient.api)


    init {
        getProductsList()
        getAllCategories()
    }

    private fun getAllCategories() {
        viewModelScope.launch {
            repository.getAllCategories().collect{ resource ->
                when(resource) {
                    is Resource.Loading -> {
                        isLoading.value = true
                    }
                    is Resource.Success -> {
                        isLoading.value = false
                        categories.value = resource.data
                        Log.d("ViewModel", "getProductsList: ${categories.value}")
                    }
                    is Resource.Error -> {
                        isLoading.value = false
                    }
                }
            }
        }
    }

    private fun getProductsList() {
        viewModelScope.launch {
            repository.getProducts(
                limit = 100,
                sort = "asc"
            ).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        isLoading.value = true // Set loading state
                    }

                    is Resource.Success -> {
                        isLoading.value = false // Stop loading
                        productsResponse.value = resource.data // Set news data

                    }

                    is Resource.Error -> {
                        isLoading.value = false // Stop loading
                        errorMessage.value = resource.message // Set error message
                    }
                }
            }

        }
    }

    fun onCreateProduct(
        productTitle: String,
        productPrice: String,
        productCategory: String,
        productDescription: String,
        productImage: String
    ) {
        val newInstance = CreateProductPayload(
            title = productTitle,
            description = productDescription,
            image = productImage,
            price = productPrice,
            category = productCategory
        )

        viewModelScope.launch {
            repository.createProduct(
                newInstance
            ).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        isLoading.value = true // Set loading state
                    }

                    is Resource.Success -> {
                        isLoading.value = false // Stop loading
                        createProductResponse.value = resource.data // Set news data
                        Log.d("Viewmodel", resource.data.toString())

                    }

                    is Resource.Error -> {
                        isLoading.value = false // Stop loading
                        errorMessage.value = resource.message // Set error message
                    }
                }
            }
        }


    }

}