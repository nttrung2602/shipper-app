package com.trungdz.appshipper.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trungdz.appshipper.databinding.ItemOrderBinding;
import com.trungdz.appshipper.databinding.ItemOrderDetailBinding;
import com.trungdz.appshipper.model.Item;

import java.util.List;

public class ItemOrderInDetailAdapter extends RecyclerView.Adapter<ItemOrderInDetailAdapter.ViewHolder> {

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    List<Item> itemList;

    public ItemOrderInDetailAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderDetailBinding binding = ItemOrderDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        Log.d("DATAA3", "onBindViewHolder: " + item);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemOrderDetailBinding binding;

        public ViewHolder(@NonNull ItemOrderDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindData(Item item) {
            Glide.with(binding.getRoot()).load(item.getImage()).into(binding.imageView); // thay bằng View
            binding.itemName.setText(item.getName());
            binding.priceAndQuantity.setText(String.format("%,d",item.getPrice()) + " đồng x " + Integer.toString(item.getQuantity()));
        }

    }
}
