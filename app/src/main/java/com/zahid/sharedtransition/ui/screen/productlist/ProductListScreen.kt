package com.zahid.sharedtransition.ui.screen.productlist

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.zahid.sharedtransition.domain.model.ProductModel

@Composable
fun ProductListScreen(
    navigateToDetailsPage: (productId: Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animationVisibilityScope: AnimatedVisibilityScope,
    viewModel: ProductListViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.viewAction) {
        viewModel.viewAction.collect {
            when (it) {
                is ProductListViewAction.GoToDetailsPage -> {
                    navigateToDetailsPage(it.productId)
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
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(products, key = { it.productId }) {
                    ProductItems(
                        product = it,
                        animationVisibilityScope = animationVisibilityScope,
                        onItemClick = { productId ->
                            onEvent(ProductListViewEvent.ProductListItemClick(productId = productId))
                        })
                }
            }
        }
    }
}

@Composable
private fun SharedTransitionScope.ProductItems(
    product: ProductModel,
    animationVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (id: Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = true,
                onClick = {
                    onItemClick(product.productId)
                }
            )
    ) {
        AsyncImage(
            modifier = Modifier
                .width(180.dp)
                .aspectRatio(4 / 3f)
                .sharedElement(
                    sharedContentState = rememberSharedContentState(key = "ProductImage-${product.productId}"),
                    animatedVisibilityScope = animationVisibilityScope
                ),
            model = product.image,
            contentDescription = null,
        )
        Column(
            modifier = Modifier
                .padding(start = 20.dp)
                .weight(1f)
        ) {
            Text(
                text = product.name
            )
            Text(
                text = "Price : ${product.currency} ${product.price}"
            )
            Text(
                text = "Category: ${product.category}"
            )
        }
    }
}