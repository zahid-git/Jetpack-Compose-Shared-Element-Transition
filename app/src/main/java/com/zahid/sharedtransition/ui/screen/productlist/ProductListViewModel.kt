package com.zahid.sharedtransition.ui.screen.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zahid.sharedtransition.domain.usecase.GetAllProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getAllProductListUseCase: GetAllProductListUseCase
) : ViewModel() {

    private val _viewState = MutableStateFlow(ProductListViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewAction = Channel<ProductListViewAction>(Channel.BUFFERED)
    val viewAction = _viewAction.receiveAsFlow()

    init {
        viewModelScope.launch {
            val productList = getAllProductListUseCase()
            _viewState.value = _viewState.value.copy(
                productList = productList
            )
        }
    }

    fun onEvent(event: ProductListViewEvent){
        when(event) {
            is ProductListViewEvent.ProductListItemClick -> {
                _viewAction.trySend(ProductListViewAction.GoToDetailsPage(event.productId, event.productImage))
            }
        }
    }

}