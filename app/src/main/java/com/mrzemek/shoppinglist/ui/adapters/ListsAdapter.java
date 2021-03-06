package com.mrzemek.shoppinglist.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.mrzemek.shoppinglist.R;
import com.mrzemek.shoppinglist.core.models.ShoppingListModel;
import java.util.List;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder> {
    List<ShoppingListModel> shoppingLists;
    private OnListItemClicked onListItemClicked;

    public ListsAdapter(List<ShoppingListModel> shoppingLists, OnListItemClicked onListItemClicked) {
        this.shoppingLists = shoppingLists;
        this.onListItemClicked = onListItemClicked;
    }

    public void setShoppingLists(List<ShoppingListModel> shoppingLists) {
        this.shoppingLists = shoppingLists;
    }

    @NonNull
    @Override
    public ListsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lists_recycler_item, parent, false);
        ViewHolder holder = new ViewHolder(view, onListItemClicked);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListsAdapter.ViewHolder holder, int position) {
        holder.shoppingListName.setText(shoppingLists.get(position).getShoppingName());
        holder.shoppingDate.setText(shoppingLists.get(position).getShoppingDate());

        if (shoppingLists.get(position).isArchived()) {
            holder.archiveButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return shoppingLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView shoppingListName;
        TextView shoppingDate;
        ImageView archiveButton;
        ConstraintLayout listItemContainer;
        OnListItemClicked onListItemClicked;

        public ViewHolder(@NonNull View itemView, OnListItemClicked onListItemClicked) {
            super(itemView);
            shoppingDate = itemView.findViewById(R.id.shopping_date_text_view);
            shoppingListName = itemView.findViewById(R.id.shopping_list_name_text_view);
            archiveButton = itemView.findViewById(R.id.archive_icon_button);
            listItemContainer = itemView.findViewById(R.id.list_item_container);
            this.onListItemClicked = onListItemClicked;
            archiveButton.setOnClickListener(this);
            listItemContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == archiveButton) {
                onListItemClicked.onArchiveClicked(getAdapterPosition());
            } else if (view == listItemContainer) {
                onListItemClicked.onItemClicked(getAdapterPosition());
            }
        }
    }

    public interface OnListItemClicked {
        void onItemClicked(int position);
        void onArchiveClicked(int position);
    }
}
