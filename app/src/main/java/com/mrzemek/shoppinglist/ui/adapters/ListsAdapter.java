package com.mrzemek.shoppinglist.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mrzemek.shoppinglist.R;
import com.mrzemek.shoppinglist.core.models.ShoppingListModel;

import java.util.ArrayList;
import java.util.List;


public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder> {
    private ArrayList<ShoppingListModel> shoppingLists = new ArrayList<>();

    public ListsAdapter(ArrayList<ShoppingListModel> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    @NonNull
    @Override
    public ListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lists_recycler_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListsAdapter.ViewHolder holder, int position) {
        holder.shoppingListName.setText(shoppingLists.get(position).getShoppingName());
        holder.shoppingDate.setText(shoppingLists.get(position).getShoppingDate());
    }

    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView shoppingListName;
        TextView shoppingDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shoppingDate = itemView.findViewById(R.id.shopping_date_text_view);
            shoppingListName = itemView.findViewById(R.id.shopping_list_name_text_view);
        }
    }
}
