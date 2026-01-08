package com.zahid.sharedtransition.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductDataModel(
    val products: List<ProductItemDataModel>
)

@Serializable
data class ProductItemDataModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val currency: String,
    val inStock: Boolean,
    val rating: Double,
    val category: String,
    val imageUrl: String,
    val createdAt: String,
)
