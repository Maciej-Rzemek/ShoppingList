package com.mrzemek.shoppinglist.ui.active_list_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository
import kotlinx.coroutines.launch

class ActiveListDetailsViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun insertNewProduct(newProduct: ListDetailsModel) = viewModelScope.launch {
        repository.insertNewProduct(newProduct)
    }

    fun editProduct(product: ListDetailsModel) = viewModelScope.launch {
        repository.editProduct(product)
    }

    fun getAllProductsList(shoppingListId: Int) = repository.getAllProductsList(shoppingListId)
}