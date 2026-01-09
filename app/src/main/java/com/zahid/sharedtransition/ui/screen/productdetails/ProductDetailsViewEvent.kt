package com.zahid.sharedtransition.ui.screen.productdetails

sealed class ProductDetailsViewEvent {

    class OnBackButtonClicked(): ProductDetailsViewEvent()

}
