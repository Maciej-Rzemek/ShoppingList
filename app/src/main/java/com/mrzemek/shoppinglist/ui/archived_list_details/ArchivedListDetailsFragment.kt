package com.mrzemek.shoppinglist.ui.archived_list_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.akademiaandroida.utils.SHOPPING_LIST_ID
import com.mrzemek.shoppinglist.R
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import com.mrzemek.shoppinglist.databinding.ActiveListDetailsFragmentBinding
import com.mrzemek.shoppinglist.databinding.ArchivedListDetailsFragmentBinding
import com.mrzemek.shoppinglist.ui.active_list_details.ActiveListDetailsViewModelFactory
import com.mrzemek.shoppinglist.ui.adapters.ListDetailsAdapter
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ArchivedListDetailsFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val archivedListDetailsViewModelFactory: ArchivedListDetailsViewModelFactory by instance()
    private lateinit var viewModel: ArchivedListDetailsViewModel
    private var _binding: ArchivedListDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ArchivedListDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val listDetailsAdapter = ListDetailsAdapter(listOf())
        viewModel = ViewModelProvider(this, archivedListDetailsViewModelFactory).get(ArchivedListDetailsViewModel::class.java)
        binding.archivedListsDetailsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.archivedListsDetailsRecyclerview.adapter = listDetailsAdapter

        val listId = requireArguments().getInt(SHOPPING_LIST_ID)
        viewModel.getAllProductsList(listId).observe(viewLifecycleOwner, Observer {
            listDetailsAdapter.submitList(it)
            listDetailsAdapter.notifyDataSetChanged()
            handleDisplayEmptyListLabel(it)
        })
    }

    private fun handleDisplayEmptyListLabel(list: List<ListDetailsModel>) {
        if (list.isEmpty()) {
            binding.emptyListLabelArchivedListDetails.visibility = View.VISIBLE
        } else {
            binding.emptyListLabelArchivedListDetails.visibility = View.INVISIBLE
        }
    }
}