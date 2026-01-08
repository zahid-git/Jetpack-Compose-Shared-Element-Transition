package com.zahid.sharedtransition.data.repository

import com.zahid.sharedtransition.domain.model.ProductModel
import com.zahid.sharedtransition.domain.repository.ProductRepository

class ProductRepositoryImpl() : ProductRepository {

    override suspend fun getProductList(): List<ProductModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductDetails(id: Int): ProductModel {
        TODO("Not yet implemented")
    }

}