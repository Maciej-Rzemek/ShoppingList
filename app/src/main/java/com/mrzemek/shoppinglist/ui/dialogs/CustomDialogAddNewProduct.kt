package com.mrzemek.shoppinglist.ui.dialogs

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.mrzemek.shoppinglist.R
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import kotlinx.android.synthetic.main.custom_dialog_add_new_product_layout.*
import kotlinx.android.synthetic.main.custom_dialog_add_new_shopping_list_layout.*

class CustomDialogAddNewProduct(context: Context, private var listId: Int, var addDialogListener: AddNewProductListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog_add_new_product_layout)

        new_product_add.setOnClickListener {
            val name = product_name.text.toString()
            val amount = product_amount.text.toString()

            if(name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Fill the empty blanks!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item = ListDetailsModel(0, listId, name, amount)
            Log.i("PROD", "item: $item")
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        new_product_cancel.setOnClickListener {
            cancel()
        }
    }
}