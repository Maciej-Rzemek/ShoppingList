package com.mrzemek.shoppinglist.ui.active_lists

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akademiaandroida.utils.SHOPPING_LIST_ID
import com.mrzemek.shoppinglist.R
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import com.mrzemek.shoppinglist.databinding.ActiveListsFragmentBinding
import com.mrzemek.shoppinglist.ui.adapters.ListsAdapter
import com.mrzemek.shoppinglist.ui.dialogs.AddNewShoppingListListener
import com.mrzemek.shoppinglist.ui.dialogs.CustomDialogAddNewShoppingList
import com.mrzemek.shoppinglist.ui.dialogs.CustomDialogArchiveList
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ActiveListsFragment : Fragment(), KodeinAware,
    ListsAdapter.OnListItemClicked {

    override val kodein by closestKodein()
    private val activeListsViewModelFactory: ActiveListsViewModelFactory by instance()
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
        viewModel = ViewModelProvider(this, activeListsViewModelFactory).get(ActiveListsViewModel::class.java)

        val listAdapter = ListsAdapter(arrayListOf(), this)
        binding.activeListsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.activeListsRecyclerview.adapter = listAdapter

        // Observes and displays all active Shopping Lists
        viewModel.getAllActiveShoppingLists().observe(viewLifecycleOwner, Observer {
            shoppingLists = it
            listAdapter.setShoppingLists(it)
            listAdapter.notifyDataSetChanged()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        // Opens Dialog to add new Shopping List
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // Shopping List click handler - navigate to Shopping List Details
    override fun onItemClicked(position: Int) {
        val bundle = bundleOf(SHOPPING_LIST_ID to shoppingLists[position].listId)
        navController!!.navigate(R.id.action_activeListsFragment_to_activeListDetailsFragment, bundle)
    }

    // Archive click handler
    override fun onArchiveClicked(position: Int) {
        CustomDialogArchiveList(
            requireContext(),
            shoppingLists[position],
            object : AddNewShoppingListListener {
                override fun onAddButtonClicked(item: ShoppingListModel) {
                    viewModel.archiveShoppingList(item)
                }
            }).show()
    }
}