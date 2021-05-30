package com.mrzemek.shoppinglist.core.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import com.mrzemek.shoppinglist.core.models.ShoppingListModel

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_list_name WHERE archived = 0")
    fun getAllActiveShoppingLists(): LiveData<List<ShoppingListModel>>

    @Query("SELECT * FROM shopping_list_name WHERE archived = 1")
    fun getAllArchivedShoppingLists(): LiveData<List<ShoppingListModel>>

    @Update
    suspend fun archiveShoppingList(shoppingList: ShoppingListModel)

    @Query("SELECT * FROM product_list WHERE shopping_list_id = :listId")
    fun getAllProductsListDetails(listId: Int): LiveData<List<ListDetailsModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShoppingList(shoppingList: ShoppingListModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewProduct(item: ListDetailsModel)

}