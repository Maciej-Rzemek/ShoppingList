package com.mrzemek.shoppinglist.core.repository

import com.mrzemek.shoppinglist.core.database.ShoppingListsDatabase
import com.mrzemek.shoppinglist.core.models.ShoppingListModel

class ShoppingRepository(private val database: ShoppingListsDatabase) {

    suspend fun insertNewShoppingList(newList: ShoppingListModel) = database.getShoppingListDao().insertShoppingList(newList)

    fun getAllShoppingLists() = database.getShoppingListDao().getAllShoppingLists()
}