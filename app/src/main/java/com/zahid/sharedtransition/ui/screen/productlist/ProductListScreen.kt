package com.zahid.sharedtransition.ui.screen.productlist

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.zahid.sharedtransition.domain.model.ProductModel

@Composable
fun ProductListScreen(
    navigateToDetailsPage: (productId: Int, productImage: String) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animationVisibilityScope: AnimatedVisibilityScope,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.viewAction) {
        viewModel.viewAction.collect {
            when (it) {
                is ProductListViewAction.GoToDetailsPage -> {
                    navigateToDetailsPage(it.productId, it.productImage)
                }
            }
        }
    }

    MainProductList(
        products = viewState.productList,
        onEvent = viewModel::onEvent,
        sharedTransitionScope = sharedTransitionScope,
        animationVisibilityScope = animationVisibilityScope
    )
}

@Composable
private fun MainProductList(
    products: List<ProductModel>,
    onEvent: (ProductListViewEvent) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animationVisibilityScope: AnimatedVisibilityScope,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(products, key = { it.productId }) {
                with(sharedTransitionScope) {
                    ProductItems(
                        product = it,
                        animationVisibilityScope = animationVisibilityScope,
                        onItemClick = { productId, productImage ->
                            onEvent(
                                ProductListViewEvent.ProductListItemClick(
                                    productId = productId,
                                    productImage = productImage
                                )
                            )
                        }
                    )
                }
            }
        }


        /*LazyColumn {
            items(items = products, key = { it.productId }) {
                with(sharedTransitionScope) {
                    ProductItems(
                        product = it,
                        animationVisibilityScope = animationVisibilityScope,
                        onItemClick = { productId, productImage ->
                            onEvent(
                                ProductListViewEvent.ProductListItemClick(
                                    productId = productId,
                                    productImage = productImage
                                )
                            )
                        }
                    )
                }
            }
        }*/
    }
}

@Composable
private fun SharedTransitionScope.ProductItems(
    product: ProductModel,
    animationVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (id: Int, image: String) -> Unit,
) {
    Card(
        modifier = Modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(
                    enabled = true,
                    onClick = {
                        onItemClick(product.productId, product.image)
                    }
                )
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .sharedElement(
                        sharedContentState = rememberSharedContentState(key = "ProductImage-${product.productId}"),
                        animatedVisibilityScope = animationVisibilityScope
                    ),
                contentScale = ContentScale.FillWidth,
                model = product.image,
                contentDescription = null,
            )
            Column(
                modifier = Modifier
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            top = 10.dp,
                            bottom = 5.dp,
                        ),
                    color = Color.Black,
                    text = product.name,
                    fontSize = 16.sp
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 10.dp,
                            end = 10.dp,
                            bottom = 10.dp,
                        ),
                    color = Color.DarkGray,
                    text = "$ ${product.price}",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
        }
    }


}