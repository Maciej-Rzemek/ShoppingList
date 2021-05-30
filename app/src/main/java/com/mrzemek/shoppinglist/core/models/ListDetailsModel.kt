package com.mrzemek.shoppinglist.core.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_list")
data class ListDetailsModel(

    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,

    @ColumnInfo(name = "shopping_list_id")
    val shoppingListId: Int,

    @ColumnInfo(name = "product_list")
    val productName: String,

    @ColumnInfo(name = "product_amount")
    val productAmount: String)
{
    companion object
}
