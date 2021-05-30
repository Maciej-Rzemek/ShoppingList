package com.mrzemek.shoppinglist.ui.active_lists

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akademiaandroida.utils.SHOPPING_LIST_ID
import com.mrzemek.shoppinglist.R
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

class ActiveListsFragment : Fragment(), KodeinAware,
    ListsAdapter.OnListItemClicked {

    override val kodein by closestKodein()
    private val shoppingFactory: ActiveListsViewModelFactory by instance()
    var navController: NavController? = null
    private var _binding: ActiveListsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var shoppingLists: List<ShoppingListModel>
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
        viewModel = ViewModelProvider(this, shoppingFactory).get(ActiveListsViewModel::class.java)

        val listAdapter = ListsAdapter(arrayListOf(), this)
        binding.activeListsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.activeListsRecyclerview.adapter = listAdapter

        viewModel.getAllShoppingLists().observe(viewLifecycleOwner, Observer {
            Log.i("LISTA2", "it: $it")
            shoppingLists = it
            listAdapter.setShoppingLists(it)
            listAdapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        binding.newListExtendedFab.setOnClickListener{
            CustomDialogAddNewShoppingList(
                requireContext(),
                object : AddNewShoppingListListener {
                    override fun onAddButtonClicked(item: ShoppingListModel) {
                        Log.i("LISTA2", "item: $item")
                        viewModel.insertNewList(item)
                    }
                }).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(position: Int) {
        val bundle = bundleOf(SHOPPING_LIST_ID to shoppingLists[position].listId)
        Log.i("LISTA1", "bundle: $bundle")

        navController!!.navigate(R.id.action_activeListsFragment_to_activeListDetailsFragment, bundle)
    }
}