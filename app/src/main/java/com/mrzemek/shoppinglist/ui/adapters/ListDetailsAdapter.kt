package com.mrzemek.shoppinglist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrzemek.shoppinglist.core.models.ListDetailsModel
import com.mrzemek.shoppinglist.databinding.DetailsListRecyclerItemBinding

class ListDetailsAdapter(private var productList: List<ListDetailsModel>): RecyclerView.Adapter<ListDetailsAdapter.ItemViewHolder>() {

    var onProductEditClicked: ((ListDetailsModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailsAdapter.ItemViewHolder {
        val itemBinding = DetailsListRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListDetailsAdapter.ItemViewHolder, position: Int) {
        val product: ListDetailsModel = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = productList.size

    fun submitList(list: List<ListDetailsModel>){
        productList = list
    }

    inner class ItemViewHolder(private val itemBinding: DetailsListRecyclerItemBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bind(product: ListDetailsModel) {
            itemBinding.priceTextView.text = product.productAmount
            itemBinding.productNameTextView.text = product.productName
        }

        init {
            itemBinding.editItemImageview.setOnClickListener {
                onProductEditClicked?.invoke(productList[adapterPosition])
            }
        }
    }
}