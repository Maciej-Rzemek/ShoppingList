package com.mrzemek.shoppinglist.ui.archived_list_details

import androidx.lifecycle.ViewModel
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository

class ArchivedListDetailsViewModel(private val repository: ShoppingRepository) : ViewModel() {

    fun getAllProductsList(shoppingListId: Int) = repository.getAllProductsList(shoppingListId)

}