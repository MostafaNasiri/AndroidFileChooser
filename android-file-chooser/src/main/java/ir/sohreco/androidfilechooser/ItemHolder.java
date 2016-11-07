package ir.sohreco.androidfilechooser;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class ItemHolder extends RecyclerView.ViewHolder {
    private ImageView ivItemIcon;
    private TextView tvItemName;
    private OnItemClickListener itemClickListener;

    interface OnItemClickListener {
        void onItemClick(Item item);
    }

    ItemHolder(View itemView, OnItemClickListener itemClickListener) {
        super(itemView);

        this.itemClickListener = itemClickListener;

        ivItemIcon = (ImageView) itemView.findViewById(R.id.item_icon_imageview);
        tvItemName = (TextView) itemView.findViewById(R.id.item_name_textview);
    }

    void bind(final Item item) {
        tvItemName.setText(item.getName());
        ivItemIcon.setImageDrawable(item.getIcon());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(item);
            }
        });
    }
}
