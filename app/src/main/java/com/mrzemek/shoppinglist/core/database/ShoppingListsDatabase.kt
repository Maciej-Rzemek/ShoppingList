package com.mrzemek.shoppinglist.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.akademiaandroida.utils.DATABASE_NAME
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import com.mrzemek.shoppinglist.core.models.ShoppingListModel

@Database(
    entities = [ShoppingListModel::class, ListDetailsModel::class],
    version = 7
)
abstract class ShoppingListsDatabase: RoomDatabase() {

    abstract fun getShoppingListDao(): ShoppingListDao

    companion object {
        @Volatile
        private var instance: ShoppingListsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ShoppingListsDatabase::class.java, DATABASE_NAME).fallbackToDestructiveMigration().build()
    }
}