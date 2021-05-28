package com.mrzemek.shoppinglist.ui.active_lists

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mrzemek.shoppinglist.R

class ActiveListsFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveListsFragment()
    }

    private lateinit var viewModel: ActiveListsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.active_lists_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActiveListsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}