package ir.sohreco.androidfilechooser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

class ItemsAdapter extends RecyclerView.Adapter<ItemHolder> {
    private List<Item> items;
    private ItemHolder.OnItemClickListener itemClickListener;

    public ItemsAdapter(ItemHolder.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        items = new ArrayList<>();
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ItemHolder(inflater.inflate(R.layout.list_item, parent, false), itemClickListener);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
