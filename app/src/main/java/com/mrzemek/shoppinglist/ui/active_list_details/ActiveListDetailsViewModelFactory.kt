package com.mrzemek.shoppinglist.ui.active_list_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository

class ActiveListDetailsViewModelFactory(private val shoppingRepository: ShoppingRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ActiveListDetailsViewModel(shoppingRepository) as T
    }
}