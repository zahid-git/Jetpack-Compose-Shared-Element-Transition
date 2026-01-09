package com.zahid.sharedtransition.ui.screen.productdetails

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
fun ProductDetails(
    productImage: String,
    productId: Int,
    sharedTransitionScope: SharedTransitionScope,
    animationVisibilityScope: AnimatedVisibilityScope,
    onBackClick: () -> Unit
) {
    val viewModel: ProductDetailsViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(productId) {
        viewModel.getProductDetails(productId = productId)
    }

    LaunchedEffect(viewModel.viewAction) {
        viewModel.viewAction.collect {action ->
            when(action) {
                is ProductDetailsViewAction.NavigateToProductPage -> {
                    onBackClick()
                }
            }
        }
    }


    DetailsPage(
        productImage = productImage,
        productId = productId,
        product = viewState.product,
        sharedTransitionScope = sharedTransitionScope,
        animationVisibilityScope = animationVisibilityScope,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun DetailsPage(
    productImage: String,
    productId: Int,
    product: ProductModel?,
    sharedTransitionScope: SharedTransitionScope,
    animationVisibilityScope: AnimatedVisibilityScope,
    onEvent: (ProductDetailsViewEvent) -> Unit
){
    with(sharedTransitionScope) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(color = Color.White)
        ) {
            Box() {
                AsyncImage(
                    model = productImage,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .sharedElement(
                            sharedContentState = rememberSharedContentState(key = "ProductImage-$productId"),
                            animatedVisibilityScope = animationVisibilityScope,
                            boundsTransform = { _, _ ->
                                spring(
                                    stiffness = Spring.StiffnessMediumLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                )
                            }
                        ),
                    contentScale = ContentScale.FillWidth
                )
                IconButton(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp, start = 15.dp)
                        .background(color = Color.White, shape = CircleShape),
                    shape = CircleShape,
                    onClick = {
                        onEvent(ProductDetailsViewEvent.OnBackButtonClicked())
                    },
                    content = {
                        Icon(
                            modifier = Modifier
                                .rotate(90f)
                                .size(35.dp),
                            imageVector = Icons.Outlined.KeyboardArrowDown,
                            contentDescription = null,
                        )
                    },
                )
            }

            product?.let { data ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 20.dp
                        )
                ) {
                    AssistChip(
                        onClick = {},
                        label = {
                            Text(
                                modifier = Modifier,
                                text = data.category,
                                fontSize = 13.sp
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            labelColor = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        shape = CircleShape
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 10.dp),
                        text = data.name,
                        color = Color.Black,
                        fontWeight = FontWeight.Light,
                        fontSize = 25.sp
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 6.dp),
                        text = "$ ${data.price}",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Text(
                        modifier = Modifier
                            .padding(top = 15.dp),
                        color = Color.Black,
                        text = data.details,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }

}

