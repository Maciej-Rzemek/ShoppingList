package com.mrzemek.shoppinglist.core.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mrzemek.shoppinglist.core.models.ShoppingListModel

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_list_name")
    fun getAllShoppingLists(): LiveData<List<ShoppingListModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShoppingList(shoppingList: ShoppingListModel)

}