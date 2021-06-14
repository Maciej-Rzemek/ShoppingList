package com.mrzemek.shoppinglist.ui.active_lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActiveListsViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun insertNewList(newList: ShoppingListModel) = viewModelScope.launch {
        repository.insertNewShoppingList(newList)
    }

    fun getAllActiveShoppingLists() = repository.getAllActiveShoppingLists()

    fun archiveShoppingList(shoppingList: ShoppingListModel) = viewModelScope.launch {
        repository.archiveShoppingList(shoppingList)
    }
}