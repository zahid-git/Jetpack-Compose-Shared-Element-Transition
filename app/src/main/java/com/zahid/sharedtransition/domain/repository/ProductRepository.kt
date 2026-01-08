package com.zahid.sharedtransition.domain.repository

import com.zahid.sharedtransition.domain.model.ProductModel

interface ProductRepository {
    suspend fun getProductList(): List<ProductModel>
    suspend fun getProductDetails(id: Int): ProductModel
}