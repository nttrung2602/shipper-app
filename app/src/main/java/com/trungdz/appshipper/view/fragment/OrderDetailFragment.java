package com.trungdz.appshipper.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungdz.appshipper.databinding.FragmentOrderDetailBinding;
import com.trungdz.appshipper.service.model.Item;
import com.trungdz.appshipper.service.model.Order;
import com.trungdz.appshipper.viewmodel.MainActivityViewmodel;
import com.trungdz.appshipper.viewmodel.OrderDetailFragmentViewmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderDetailFragment newInstance(String param1, String param2) {
        OrderDetailFragment fragment = new OrderDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentOrderDetailBinding binding;
    MainActivityViewmodel mainActivityViewmodel;
    OrderDetailFragmentViewmodel orderDetailFragmentViewmodel;
    ItemOrderInDetailAdapter itemOrderInDetailAdapter;
    List<Item> itemList = new ArrayList<>();
    int idOrderIsBeingDisplay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOrderDetailBinding.inflate(inflater, container, false);
        mainActivityViewmodel = ViewModelProviders.of(requireActivity()).get(MainActivityViewmodel.class);
        orderDetailFragmentViewmodel = ViewModelProviders.of(this).get(OrderDetailFragmentViewmodel.class);

        initObserver();
        initUI();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    void initObserver() {
        // Display data for Order Detail Screen
        orderDetailFragmentViewmodel.getItemList().observe(getViewLifecycleOwner(), new Observer<List<Item>>() {

            @Override
            public void onChanged(List<Item> data) {
                // Set itemList for Adapter recyclerView
                itemOrderInDetailAdapter.setItemList(data);
                itemOrderInDetailAdapter.notifyDataSetChanged();

                Order order = orderDetailFragmentViewmodel.getOrderIsBeingShipped();
                idOrderIsBeingDisplay = order.getId_order();

                binding.idOrder.setText("MÃ HÓA ĐƠN: " + Integer.toString(order.getId_order()));
                binding.customerName.setText(order.getName_customer());
                binding.txtAddress.setText(order.getAddress());
                binding.totalItemPrice.setText(String.format("%,d", order.getItem_fee()) + " đồng");
                binding.deliveryFee.setText(String.format("%,d", order.getDelivery_fee()) + " đồng");
                binding.totalOrderFee.setText(String.format("%,d", order.getTotal()) + " đồng");
            }
        });
    }


    void initUI() {
        itemOrderInDetailAdapter = new ItemOrderInDetailAdapter(itemList);

        // set UI for Button
        binding.listViewRecycler.setAdapter(itemOrderInDetailAdapter);
//        if (mainActivityViewmodel.getShippingStatus().getValue()==true){
        binding.btnConfirm.setVisibility(View.VISIBLE);
        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityViewmodel.confirmOrder(idOrderIsBeingDisplay);
            }
        });
//        }
    }


}