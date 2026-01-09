package com.zahid.sharedtransition.ui.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zahid.sharedtransition.ui.screen.productdetails.ProductDetails
import com.zahid.sharedtransition.ui.screen.productlist.ProductListScreen

@Composable
fun AppNavHost(navController: NavHostController){
    SharedTransitionLayout{
        NavHost(
            navController = navController,
            startDestination = NavRoutes.ProductList,
        ) {


            composable<NavRoutes.ProductList> {
                ProductListScreen(
                    navigateToDetailsPage = { id, image ->
                        navController.navigate(NavRoutes.ProductDetails(id, image))
                    },
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animationVisibilityScope = this@composable,
                )
            }

            composable<NavRoutes.ProductDetails> {
                val productImage = it.toRoute<NavRoutes.ProductDetails>().productImage
                val productId = it.toRoute<NavRoutes.ProductDetails>().productId
                ProductDetails(
                    productId = productId,
                    productImage = productImage,
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animationVisibilityScope = this@composable,
                    onBackClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }

}