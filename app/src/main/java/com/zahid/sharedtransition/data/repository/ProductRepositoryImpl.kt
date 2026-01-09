package com.zahid.sharedtransition.data.repository

import com.zahid.sharedtransition.data.datasource.DataSource
import com.zahid.sharedtransition.domain.model.ProductModel
import com.zahid.sharedtransition.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(

) : ProductRepository {

    override suspend fun getProductList(): List<ProductModel> {
        val productList = DataSource().fetchProductList()
        val productModelList = productList.map {
            ProductModel(
                productId = it.id,
                name = it.name,
                image = it.imageUrl,
                price = it.price,
                currency = it.currency,
                category = it.category,
                details = it.description
            )
        }
        return productModelList
    }

    override suspend fun getProductDetails(id: Int): ProductModel? {
        val productData = DataSource().fetchProduct(id)
        val product = productData?.let {
            ProductModel(
                productId = it.id,
                name = it.name,
                image = it.imageUrl,
                price = it.price,
                currency = it.currency,
                category = it.category,
                details = it.description
            )
        } ?: run {
            null
        }
        return product
    }

}