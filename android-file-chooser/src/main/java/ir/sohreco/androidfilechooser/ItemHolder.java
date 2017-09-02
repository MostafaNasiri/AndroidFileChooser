package ir.sohreco.androidfilechooser;

import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    ItemHolder(View itemView, OnItemClickListener itemClickListener, @ColorRes int textColorResId) {
        super(itemView);
        this.itemClickListener = itemClickListener;

        ivItemIcon = itemView.findViewById(R.id.item_icon_imageview);
        tvItemName = itemView.findViewById(R.id.item_name_textview);
        cbFile = itemView.findViewById(R.id.file_checkbox);

        if (textColorResId != 0)
            tvItemName.setTextColor(ContextCompat.getColor(itemView.getContext(), textColorResId));
    }

    void bind(final Item item) {
        cbFile.setVisibility(View.GONE);
        tvItemName.setText(item.getName());
        ivItemIcon.setImageDrawable(item.getIcon());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onItemClick(item);
            }
        });
    }

    // This method only gets called when chooser type is FILE_CHOOSER with multiple file selection enabled.
    void bind(final Item item, final List<Item> selectedItems) {
        cbFile.setVisibility(View.VISIBLE);
        tvItemName.setText(item.getName());
        ivItemIcon.setImageDrawable(item.getIcon());

        cbFile.setOnCheckedChangeListener(null); // This line prevents unexpected behavior
        cbFile.setChecked(selectedItems.contains(item));
        cbFile.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    selectedItems.add(item);
                } else {
                    selectedItems.remove(item);
                }
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbFile.performClick();
            }
        });
    }
}
