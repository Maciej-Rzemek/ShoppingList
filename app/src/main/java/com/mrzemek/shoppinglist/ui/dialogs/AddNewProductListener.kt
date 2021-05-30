package com.mrzemek.shoppinglist.ui.dialogs

import com.mrzemek.shoppinglist.core.models.ListDetailsModel


interface AddNewProductListener {
    fun onAddButtonClicked(item: ListDetailsModel)
}