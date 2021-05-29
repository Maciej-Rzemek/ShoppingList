package com.mrzemek.shoppinglist

import android.app.Application
import com.mrzemek.shoppinglist.core.database.ShoppingListsDatabase
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository
import com.mrzemek.shoppinglist.ui.active_lists.ActiveListsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ShoppingListsApplication() : Application(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@ShoppingListsApplication))
        bind() from singleton { ShoppingListsDatabase(instance()) }
        bind() from singleton { ShoppingRepository(instance()) }
        bind() from provider {
            ActiveListsViewModelFactory(instance())
        }

    }
}