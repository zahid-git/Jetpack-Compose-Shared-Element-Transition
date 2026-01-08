package com.zahid.sharedtransition.ui.navigation

import kotlinx.serialization.Serializable

object NavRoutes {

    @Serializable
    object ProductList;
    @Serializable
    data class ProductDetails(val productId: Int);
}