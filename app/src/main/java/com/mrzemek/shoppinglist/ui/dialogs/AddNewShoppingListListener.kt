package com.mrzemek.shoppinglist.ui.dialogs

import com.mrzemek.shoppinglist.core.models.ShoppingListModel

interface AddNewShoppingListListener {
    fun onAddButtonClicked(item: ShoppingListModel)
}
