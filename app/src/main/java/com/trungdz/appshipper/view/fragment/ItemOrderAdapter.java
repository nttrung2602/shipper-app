package com.trungdz.appshipper.view.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trungdz.appshipper.databinding.ItemOrderBinding;
import com.trungdz.appshipper.service.model.Order;

import java.util.List;

public class ItemOrderAdapter extends RecyclerView.Adapter<ItemOrderAdapter.ViewHolder> {

    private List<Order> dataList;
    private OnClickListener onClickListener;

    void setDataList(List<Order> dataList){
        this.dataList = dataList;
    }

    public ItemOrderAdapter(List<Order> dataList, OnClickListener onClickListener) {
        this.dataList = dataList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderBinding binding= ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order data= dataList.get(position);
        holder.bindData(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemOrderBinding binding;
        public ViewHolder(@NonNull ItemOrderBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        void bindData(Order data){
            String textForButton="";
            if(data.getStatus()==1) {
                textForButton="Nhận";
            }else if (data.getStatus()==4){
                textForButton="Xem";
            }

            binding.btnOrderStatus.setText(textForButton);
            binding.idOrder.setText(Integer.toString(data.getId_order()));
            binding.totalPrice.setText("Tổng tiền: "+ String.format("%,d", data.getTotal()) +" đồng");
            binding.btnOrderStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // trả về id đơn hàng
                    onClickListener.onClick(view,dataList.get(getAdapterPosition()).getId_order());
                }
            });
        }
    }

    public interface OnClickListener{
        void onClick(View view, int id_order);
    }
}
