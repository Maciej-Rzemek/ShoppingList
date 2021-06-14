package com.mrzemek.shoppinglist.ui.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatDialog
import com.mrzemek.shoppinglist.R
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import com.mrzemek.shoppinglist.utils.Helpers
import kotlinx.android.synthetic.main.custom_dialog_archive_list.*


class CustomDialogArchiveList(context: Context, var shoppingListItem: ShoppingListModel, var shoppingListListener: AddNewShoppingListListener) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_archive_list)

        dialog_archive_yes.setOnClickListener{
            val item = ShoppingListModel(shoppingListItem.listId, shoppingListItem.shoppingName, shoppingListItem.shoppingDate, true)
            shoppingListListener.onAddButtonClicked(item)
            Helpers.isShowingArchiveDialog = false
            dismiss()
        }

        dialog_archive_no.setOnClickListener {
            Helpers.isShowingArchiveDialog = false
            cancel()
        }
    }

    override fun onSaveInstanceState(): Bundle {
        return super.onSaveInstanceState()
    }
}