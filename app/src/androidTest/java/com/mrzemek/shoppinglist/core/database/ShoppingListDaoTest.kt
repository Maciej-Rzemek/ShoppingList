package com.mrzemek.shoppinglist.core.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import com.mrzemek.shoppinglist.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

@ExperimentalCoroutinesApi
@Suppress("DEPRECATION")
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingListDaoTest {

    @get:Rule
    var instantTaskRuleExecutor = InstantTaskExecutorRule()

    private lateinit var shoppingListsDatabase: ShoppingListsDatabase
    private lateinit var shoppingListDao: ShoppingListDao

    @Before
    fun setup() {
        shoppingListsDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingListsDatabase::class.java
        ).allowMainThreadQueries().build()
        shoppingListDao = shoppingListsDatabase.getShoppingListDao()
    }

    @After
    fun teardown() {
        shoppingListsDatabase.close()
    }

    @Test
    fun insertShoppingList() = runBlockingTest {
        val shoppingListItem = ShoppingListModel(1, "name", Date(20, 2, 2020), false)
        shoppingListDao.insertShoppingList(shoppingListItem)

        val allShoppingLists = shoppingListDao.getAllActiveShoppingLists().getOrAwaitValue()
        assertThat(allShoppingLists).contains(shoppingListItem)
    }

    @Test
    fun insertNewProduct() = runBlockingTest {
        val productItem = ListDetailsModel(1, 5, "name", "5kg")
        shoppingListDao.insertNewProduct(productItem)

        val allProductItems = shoppingListDao.getAllProductsListDetails(5).getOrAwaitValue()
        assertThat(allProductItems).contains(productItem)
    }
}