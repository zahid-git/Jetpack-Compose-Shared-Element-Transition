package com.zahid.sharedtransition.ui.screen.productlist

sealed class ProductListViewAction {

    data class GoToDetailsPage(val productId: Int):ProductListViewAction()


}