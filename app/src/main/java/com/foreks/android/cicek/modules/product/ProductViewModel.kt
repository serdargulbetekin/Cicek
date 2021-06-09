package com.foreks.android.cicek.modules.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foreks.android.cicek.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    private val _productList = MutableLiveData<Result<List<Product>?>>()
    val productList: LiveData<Result<List<Product>?>>
        get() = _productList

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            _productList.postValue(Result.loading(data = null))
            _productList.postValue(repository.getProducts())
        }
    }

}