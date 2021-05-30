package com.mrzemek.shoppinglist.core.repository

import com.mrzemek.shoppinglist.core.database.ShoppingListsDatabase
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import com.mrzemek.shoppinglist.core.models.ShoppingListModel

class ShoppingRepository(private val database: ShoppingListsDatabase) {

    suspend fun insertNewShoppingList(newList: ShoppingListModel) = database.getShoppingListDao().insertShoppingList(newList)

    suspend fun insertNewProduct(newProduct: ListDetailsModel) = database.getShoppingListDao().insertNewProduct(newProduct)

    fun getAllShoppingLists() = database.getShoppingListDao().getAllShoppingLists()

    fun getAllProductsList(shoppingListId: Int) = database.getShoppingListDao().getAllProductsList(shoppingListId)
}