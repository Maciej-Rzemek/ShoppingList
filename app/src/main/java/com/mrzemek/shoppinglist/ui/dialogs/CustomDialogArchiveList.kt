package com.mrzemek.shoppinglist.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import com.mrzemek.shoppinglist.R
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import kotlinx.android.synthetic.main.custom_dialog_archive_list.*


class CustomDialogArchiveList(context: Context, var shoppingListItem: ShoppingListModel, var shoppingListListener: AddNewShoppingListListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog_add_new_product_layout)

        dialog_archive_yes.setOnClickListener{
            val item = ShoppingListModel(shoppingListItem.listId, shoppingListItem.shoppingName, shoppingListItem.shoppingDate, true)
            shoppingListListener.onAddButtonClicked(item)
            dismiss()
        }

        dialog_archive_no.setOnClickListener {
            cancel()
        }
    }
}