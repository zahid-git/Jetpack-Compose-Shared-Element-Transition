package com.zahid.sharedtransition.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zahid.sharedtransition.ui.screen.productlist.ProductListScreen


@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()){
    /*SharedTransitionLayout(

    ) {*/
        NavHost(
            navController = navController,
            startDestination = NavRoutes.ProductList,
        ) {
            composable<NavRoutes.ProductList> {
                ProductListScreen(
                    navigateToDetailsPage = {
                        navController.navigate(NavRoutes.ProductDetails(it))
                    }
                )
            }

        }
    /*}*/


}