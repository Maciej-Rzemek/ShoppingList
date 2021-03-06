package com.mrzemek.shoppinglist.core.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_list_name")
data class ShoppingListModel(

    @PrimaryKey(autoGenerate = true)
    val listId: Int = 0,

    @ColumnInfo(name = "shopping_name")
    val shoppingName: String,

    @ColumnInfo(name = "shopping_date")
    val shoppingDate: String,

    @ColumnInfo(name = "archived")
    var isArchived: Boolean = false
) {
    companion object
}
