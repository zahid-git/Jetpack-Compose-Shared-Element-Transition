package com.zahid.sharedtransition.domain.usecase

import com.zahid.sharedtransition.domain.model.ProductModel
import com.zahid.sharedtransition.domain.repository.ProductRepository
import javax.inject.Inject

class GetAllProductListUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): List<ProductModel>{
        return repository.getProductList()
    }

}