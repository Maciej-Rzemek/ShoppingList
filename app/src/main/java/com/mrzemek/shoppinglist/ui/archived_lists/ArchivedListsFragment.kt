package com.mrzemek.shoppinglist.ui.archived_lists

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrzemek.shoppinglist.R

class ArchivedListsFragment : Fragment() {

    companion object {
        fun newInstance() = ArchivedListsFragment()
    }

    private lateinit var viewModel: ArchivedListsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.archived_lists_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArchivedListsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}