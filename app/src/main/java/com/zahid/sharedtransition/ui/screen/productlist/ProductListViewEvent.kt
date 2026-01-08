package com.zahid.sharedtransition.ui.screen.productlist

sealed class ProductListViewEvent {

    class ProductListItemClick(val productId: Int):ProductListViewEvent()


}