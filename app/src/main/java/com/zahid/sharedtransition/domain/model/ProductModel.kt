package com.zahid.sharedtransition.domain.model

import java.util.UUID

data class ProductModel(
    val productId: Int,
    val name: String,
    val image: String,
    val price: Double,
    val category: String,
    val details: String
)
