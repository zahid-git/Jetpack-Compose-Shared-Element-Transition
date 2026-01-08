package com.zahid.sharedtransition.ui.screen.productlist

import com.zahid.sharedtransition.domain.model.ProductModel

data class ProductListViewState(
    val productList: List<ProductModel> = listOf()
)
