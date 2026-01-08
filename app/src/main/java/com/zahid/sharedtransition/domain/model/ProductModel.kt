package com.zahid.sharedtransition.domain.model

data class ProductModel(
    val productId: Int,
    val name: String,
    val image: String,
    val price: Double,
    val currency: String,
    val category: String,
    val details: String
)
