package com.mrzemek.shoppinglist.ui.active_list_details

import androidx.lifecycle.ViewModel
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActiveListDetailsViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun insertNewProduct(newProduct: ListDetailsModel) = CoroutineScope(Dispatchers.Main).launch {
        repository.insertNewProduct(newProduct)
    }

    fun getAllProductsList(shoppingListId: Int) = repository.getAllProductsList(shoppingListId)
}