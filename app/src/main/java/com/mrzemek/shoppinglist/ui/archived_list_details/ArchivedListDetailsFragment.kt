package com.mrzemek.shoppinglist.ui.archived_list_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrzemek.shoppinglist.R

class ArchivedListDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ArchivedListDetailsFragment()
    }

    private lateinit var viewModel: ArchivedListDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.archived_list_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ArchivedListDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}