package com.mrzemek.shoppinglist.ui.active_lists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository

class ActiveListsViewModelFactory(private val shoppingRepository: ShoppingRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ActiveListsViewModel(shoppingRepository) as T
    }
}