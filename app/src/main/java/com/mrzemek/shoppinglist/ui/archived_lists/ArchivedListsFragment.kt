package com.mrzemek.shoppinglist.ui.archived_lists

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrzemek.shoppinglist.core.models.ShoppingListModel
import com.mrzemek.shoppinglist.databinding.ArchivedListsFragmentBinding
import com.mrzemek.shoppinglist.ui.adapters.ListsAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ArchivedListsFragment : Fragment(), KodeinAware, ListsAdapter.OnListItemClicked {

    override val kodein by closestKodein()
    private val archivedListsViewModelFactory: ArchivedListsViewModelFactory by instance()
    private var _binding: ArchivedListsFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ArchivedListsViewModel
    private lateinit var shoppingLists: List<ShoppingListModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArchivedListsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, archivedListsViewModelFactory).get(ArchivedListsViewModel::class.java)

        val listAdapter = ListsAdapter(arrayListOf(), this)
        binding.archivedListsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.archivedListsRecyclerview.adapter = listAdapter

        viewModel.getAllActiveShoppingLists().observe(viewLifecycleOwner, Observer {
            shoppingLists = it
            listAdapter.setShoppingLists(it)
            listAdapter.notifyDataSetChanged()
        })
    }

    override fun onItemClicked(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onArchiveClicked(position: Int) {
    }

}