package com.mrzemek.shoppinglist.ui.active_list_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akademiaandroida.utils.SHOPPING_LIST_ID
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import com.mrzemek.shoppinglist.databinding.ActiveListDetailsFragmentBinding
import com.mrzemek.shoppinglist.ui.adapters.ListDetailsAdapter
import com.mrzemek.shoppinglist.ui.dialogs.AddNewProductListener
import com.mrzemek.shoppinglist.ui.dialogs.CustomDialogAddNewProduct
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ActiveListDetailsFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val activeListDetailsViewModelFactory: ActiveListDetailsViewModelFactory by instance()
    private var _binding: ActiveListDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private var currentListId: Int = 0

    private lateinit var viewModel: ActiveListDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ActiveListDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, activeListDetailsViewModelFactory).get(ActiveListDetailsViewModel::class.java)
        val listDetailsAdapter = ListDetailsAdapter(listOf())
        binding.itemsListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.itemsListRecyclerview.adapter = listDetailsAdapter
        viewModel.getAllProductsList(currentListId).observe(viewLifecycleOwner, Observer {
            listDetailsAdapter.submitList(it)
            listDetailsAdapter.notifyDataSetChanged()
            handleDisplayEmptyListLabel(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listId = requireArguments().getInt(SHOPPING_LIST_ID)
        currentListId = listId
        binding.newItemExtendedFab.setOnClickListener{
            CustomDialogAddNewProduct(
                    requireContext(),
                    listId,
                    object : AddNewProductListener {
                        override fun onAddButtonClicked(item: ListDetailsModel) {
                            Log.i("LISTA", "UTWORZONY ITEM: $item")
                            viewModel.insertNewProduct(item)
                        }
                    }
            ).show()
        }
    }

    private fun handleDisplayEmptyListLabel(list: List<ListDetailsModel>) {
        if (list.isEmpty()) {
            binding.emptyListLabelListDetails.visibility = View.VISIBLE
        } else {
            binding.emptyListLabelListDetails.visibility = View.INVISIBLE
        }
    }
}