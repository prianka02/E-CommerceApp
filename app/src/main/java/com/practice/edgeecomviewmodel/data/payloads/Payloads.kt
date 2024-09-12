package com.practice.edgeecomviewmodel.data.payloads

data class CreateProductPayload(
    val category: String?,
    val description: String?,
    val image: String?,
    val price: Any?,
    val title: String?
)