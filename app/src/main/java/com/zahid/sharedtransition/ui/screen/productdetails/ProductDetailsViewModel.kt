package com.zahid.sharedtransition.ui.screen.productdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zahid.sharedtransition.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel(){

    private val _viewState = MutableStateFlow(ProductDetailsViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewAction = Channel<ProductDetailsViewAction>(Channel.BUFFERED)
    val viewAction = _viewAction.receiveAsFlow()

    fun getProductDetails(productId: Int){
        viewModelScope.launch {
            val product = repository.getProductDetails(productId)
            _viewState.value = _viewState.value.copy(
                product = product
            )
        }
    }

    fun onEvent(productDetailsViewEvent: ProductDetailsViewEvent){
        when(productDetailsViewEvent) {
            is ProductDetailsViewEvent.OnBackButtonClicked -> {
                _viewAction.trySend(ProductDetailsViewAction.NavigateToProductPage())
            }
        }
    }

}