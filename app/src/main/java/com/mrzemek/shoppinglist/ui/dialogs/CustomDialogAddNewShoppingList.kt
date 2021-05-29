package com.mrzemek.shoppinglist.ui.dialogs

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.mrzemek.shoppinglist.R
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import kotlinx.android.synthetic.main.custom_dialog_add_new_shopping_list_layout.*
import java.util.*

class CustomDialogAddNewShoppingList(context: Context, var addDialogListener: AddNewShoppingListListener) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog_add_new_shopping_list_layout)

        new_list_add.setOnClickListener {
            val name = new_list_name.text.toString()
            val date = new_list_date.text.toString()

            if(name.isEmpty() || date.isEmpty()) {
                Toast.makeText(context, "Fill the empty blanks!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item = ShoppingListModel(0, name, date)
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        new_list_date.setOnClickListener{
            showDatePickerDialog(new_list_date)
        }

        new_list_cancel.setOnClickListener {
            cancel()
        }
    }

    private fun showDatePickerDialog(dateTextView: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            dateTextView.text = "$dayOfMonth/${monthOfYear + 1}/$year"
        }, year, month, day)

        datePickerDialog.show()
    }
}