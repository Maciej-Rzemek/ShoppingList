package com.mrzemek.shoppinglist.ui.active_lists

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrzemek.shoppinglist.R
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import com.mrzemek.shoppinglist.databinding.ActiveListDetailsFragmentBinding
import com.mrzemek.shoppinglist.databinding.ActiveListsFragmentBinding
import com.mrzemek.shoppinglist.databinding.ActivityMainBinding
import com.mrzemek.shoppinglist.ui.adapters.ListsAdapter

class ActiveListsFragment : Fragment() {

    var navController: NavController? = null
    private lateinit var binding: ActiveListsFragmentBinding
    private lateinit var listsAdapter: ListsAdapter

    val dummyList: List<ShoppingListModel> = listOf(
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021"),
        ShoppingListModel("ShoppingName", "20-04-2021")
    )

    companion object {
        fun newInstance() = ActiveListsFragment()
    }

    private lateinit var viewModel: ActiveListsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActiveListsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActiveListsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActiveListsFragmentBinding.bind(view)
        navController = Navigation.findNavController(view)
        binding.newListExtendedFab.setOnClickListener{
            navController!!.navigate(R.id.action_activeListsFragment_to_activeListDetailsFragment)
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {

        binding.activeListsRecyclerview.apply {
            binding.activeListsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
            listsAdapter = ListsAdapter(ArrayList(dummyList))
            binding.activeListsRecyclerview.adapter = listsAdapter
        }
    }
}