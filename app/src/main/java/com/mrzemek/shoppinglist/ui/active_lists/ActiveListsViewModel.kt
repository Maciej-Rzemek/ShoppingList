package com.mrzemek.shoppinglist.ui.active_lists

import androidx.lifecycle.ViewModel
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActiveListsViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun insertNewList(newList: ShoppingListModel) = CoroutineScope(Dispatchers.Main).launch {
        repository.insertNewShoppingList(newList)
    }

    fun getAllActiveShoppingLists() = repository.getAllActiveShoppingLists()

    fun archiveShoppingList(shoppingList: ShoppingListModel) = CoroutineScope(Dispatchers.Main).launch {
        repository.archiveShoppingList(shoppingList)
    }
}