package com.mrzemek.shoppinglist.ui.active_lists

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrzemek.shoppinglist.core.database.ShoppingListsDatabase
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import com.mrzemek.shoppinglist.core.repository.ShoppingRepository
import com.mrzemek.shoppinglist.databinding.ActiveListsFragmentBinding
import com.mrzemek.shoppinglist.ui.adapters.ListsAdapter
import com.mrzemek.shoppinglist.ui.dialogs.AddNewShoppingListListener
import com.mrzemek.shoppinglist.ui.dialogs.CustomDialogAddNewShoppingList
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ActiveListsFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val shoppingFactory: ActiveListsViewModelFactory by instance()
    var navController: NavController? = null
    private var _binding: ActiveListsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ActiveListsViewModel

    companion object {
        fun newInstance() = ActiveListsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActiveListsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val database = ShoppingListsDatabase(requireContext())
        val repository = ShoppingRepository(database)
        val factory = ActiveListsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(ActiveListsViewModel::class.java)

        val listAdapter = ListsAdapter(arrayListOf())
        binding.activeListsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.activeListsRecyclerview.adapter = listAdapter

        viewModel.getAllShoppingLists().observe(viewLifecycleOwner, Observer {
            listAdapter.setShoppingLists(it)
            listAdapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = ActiveListsFragmentBinding.bind(view)
        navController = Navigation.findNavController(view)

        binding.newListExtendedFab.setOnClickListener{
            CustomDialogAddNewShoppingList(
                requireContext(),
                object : AddNewShoppingListListener {
                    override fun onAddButtonClicked(item: ShoppingListModel) {
                        viewModel.insertNewList(item)
                    }
                }).show()
        }
    }

    private fun showNewShoppingListDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Add new shopping list")
        val input = EditText(requireContext())
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            var shoppingListName = input.text.toString()
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}