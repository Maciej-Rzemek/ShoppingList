package com.mrzemek.shoppinglist.ui.archived_lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository
import com.mrzemek.shoppinglist.ui.active_lists.ActiveListsViewModel

class ArchivedListsViewModelFactory(private val shoppingRepository: ShoppingRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArchivedListsViewModel(shoppingRepository) as T
    }
}