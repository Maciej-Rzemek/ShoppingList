package com.mrzemek.shoppinglist.ui.dialogs

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.core.os.bundleOf
import com.mrzemek.shoppinglist.R
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import com.mrzemek.shoppinglist.utils.Helpers
import kotlinx.android.synthetic.main.custom_dialog_add_new_shopping_list_layout.*
import java.util.*

class CustomDialogAddNewShoppingList(context: Context, var addDialogListener: AddNewShoppingListListener) : AppCompatDialog(context) {

    private var selectedDate: Date? = null

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
            val item = ShoppingListModel(0, name, selectedDate)
            addDialogListener.onAddButtonClicked(item)
            Helpers.isShowingAddNewShoppingList = false
            dismiss()
        }

        new_list_date.setOnClickListener{
            showDatePickerDialog(new_list_date)
        }

        new_list_cancel.setOnClickListener {
            Helpers.isShowingAddNewShoppingList = false
            cancel()
        }
    }

    private fun showDatePickerDialog(dateTextView: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)


        val datePickerDialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, thisMonth, thisDay ->
            dateTextView.text = "$thisDay/${thisMonth + 1}/$year"

            val newDate:Calendar =Calendar.getInstance()
            newDate.set(year, thisMonth, thisDay)
            selectedDate = Date(newDate.timeInMillis) // setting new date

            Log.i("LOG", "selectedDate: $selectedDate")
        }, year, month, day)

        datePickerDialog.show()
    }
}