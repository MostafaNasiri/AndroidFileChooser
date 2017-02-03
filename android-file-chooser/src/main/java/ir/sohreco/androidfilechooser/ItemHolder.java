package ir.sohreco.androidfilechooser;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class ItemHolder extends RecyclerView.ViewHolder {
    private ImageView ivItemIcon;
    private TextView tvItemName;
    private CheckBox cbFile;
    private OnItemClickListener itemClickListener;

    interface OnItemClickListener {
        void onItemClick(Item item);
    }

    ItemHolder(View itemView, OnItemClickListener itemClickListener) {
        super(itemView);

        this.itemClickListener = itemClickListener;

        ivItemIcon = (ImageView) itemView.findViewById(R.id.item_icon_imageview);
        tvItemName = (TextView) itemView.findViewById(R.id.item_name_textview);
        cbFile = (CheckBox) itemView.findViewById(R.id.file_checkbox);
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

    void bind(final Item item, final List<Item> selectedItems) {
        tvItemName.setText(item.getName());
        ivItemIcon.setImageDrawable(item.getIcon());
        cbFile.setVisibility(View.VISIBLE);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbFile.isChecked()) {
                    selectedItems.remove(item);
                    cbFile.setChecked(false);
                } else {
                    selectedItems.add(item);
                    cbFile.setChecked(true);
                }
            }
        };
        cbFile.setOnClickListener(onClickListener);
        itemView.setOnClickListener(onClickListener);
    }
}
