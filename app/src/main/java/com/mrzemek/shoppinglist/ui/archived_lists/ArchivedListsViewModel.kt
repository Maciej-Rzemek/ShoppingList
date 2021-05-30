package com.mrzemek.shoppinglist.ui.archived_lists

import androidx.lifecycle.ViewModel
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository

class ArchivedListsViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun getAllActiveShoppingLists() = repository.getAllArchivedShoppingLists()
}