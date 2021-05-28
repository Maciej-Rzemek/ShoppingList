package com.mrzemek.shoppinglist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mrzemek.shoppinglist.R
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import com.mrzemek.shoppinglist.databinding.DetailsListRecyclerItemBinding

class ListDetailsAdapter(private var productList: MutableList<ListDetailsModel>): RecyclerView.Adapter<ListDetailsAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailsAdapter.ItemViewHolder {
        val itemBinding = DetailsListRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListDetailsAdapter.ItemViewHolder, position: Int) {
        val product: ListDetailsModel = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size

    inner class ItemViewHolder(private val itemBinding: DetailsListRecyclerItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(product: ListDetailsModel) {
            itemBinding.priceTextView.text = product.productPrice.toString()
            itemBinding.productNameTextView.text = product.productName
        }
    }
}