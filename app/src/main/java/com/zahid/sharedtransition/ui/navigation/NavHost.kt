package com.zahid.sharedtransition.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zahid.sharedtransition.ui.screen.productlist.ProductListScreen


@Composable
fun AppNavHost(){
    /*SharedTransitionLayout(

    ) {*/
        NavHost(
            navController = rememberNavController(),
            startDestination = NavRoutes.ProductList,
        ) {
            composable<NavRoutes.ProductList> {
                ProductListScreen()
            }

        }
    /*}*/


}