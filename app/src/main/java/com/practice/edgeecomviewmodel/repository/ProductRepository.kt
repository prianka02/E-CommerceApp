package com.practice.edgeecomviewmodel.repository

import android.util.Log
import com.practice.edgeecomviewmodel.api.Resource
import com.practice.edgeecomviewmodel.data.category.Categories
import com.practice.edgeecomviewmodel.data.payloads.CreateProductPayload
import com.practice.edgeecomviewmodel.data.products.Product
import com.practice.edgeecomviewmodel.data.products.ProductsResponse
import com.practice.edgeecomviewmodel.service.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Body

class ProductRepository(private val api: ProductService) {

//     Function for getting Products
    suspend fun getProducts(
        limit: Number?,
        sort: String?
    ): Flow<Resource<ProductsResponse>> = flow {
         try {
            emit(Resource.Loading) // Emit loading state
            val response = api.getProducts(
                limit,
                sort
            ) // Make the network request
            emit(Resource.Success(response))
         } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error")) // Emit error state
        }
    }.flowOn(Dispatchers.IO)


 // Functions for getting all categories
    suspend fun getAllCategories():
            Flow<Resource<Categories>> = flow {
         try {
                    emit(Resource.Loading)
                    val response = api.getAllCategories()
//                    Log.d("Repository", "getCategory: $response")

                    emit(Resource.Success(response))

        }catch (e: Exception){
                    emit(Resource.Error(e.message ?: "Unknown Error"))
//                    Log.d("Repository", "getCategory: $e")
                }
    }.flowOn(Dispatchers.IO)


//     Function for Creating Products
    suspend fun createProduct(
        product:CreateProductPayload
    ): Flow<Resource<Product>> = flow {
        try {
            emit(Resource.Loading) // Emit loading state
            val response = api.createProduct(product) // Make the network request
//            Log.d("Repository", response.toString())

            emit(Resource.Success(response))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error")) // Emit error state
        }
    }.flowOn(Dispatchers.IO)

//  Functions for getting Products by Category wise

    suspend fun getProductsByCategory(
        categoryName: String?
    ): Flow<Resource<ProductsResponse>> = flow {
        try {
            emit(Resource.Loading) // Emit loading state
            val response = api.getProductsByCategory(
                categoryName
            ) // Make the network request
            emit(Resource.Success(response))
            Log.d("Repository", response.toString())
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error")) // Emit error state
        }
    }.flowOn(Dispatchers.IO)



}