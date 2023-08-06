package com.trungdz.appshipper.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trungdz.appshipper.databinding.FragmentOrderBinding;
import com.trungdz.appshipper.service.model.Order;
import com.trungdz.appshipper.viewmodel.MainActivityViewmodel;
import com.trungdz.appshipper.viewmodel.OrderFragmentViewmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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

    List<Order> listData = new ArrayList<>();
    private FragmentOrderBinding binding;
    ItemOrderAdapter itemOrderAdapter;
    MainActivityViewmodel mainActivityViewmodel;
    OrderFragmentViewmodel orderFragmentViewmodel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Init ViewBinding and ViewModel for UI
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        mainActivityViewmodel = ViewModelProviders.of(requireActivity()).get(MainActivityViewmodel.class);
        orderFragmentViewmodel = ViewModelProviders.of(this).get(OrderFragmentViewmodel.class);
        initUI();
        initObserver();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    void initUI() {
        setListView();
    }

    void setListView() {
        // Set up ListView
        itemOrderAdapter = new ItemOrderAdapter(listData, new ItemOrderAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int id_order) {
                // handle order and update UI
                mainActivityViewmodel.receiveOrder(id_order);
            }
        });

        binding.listViewRecycle.setAdapter(itemOrderAdapter);
    }

    void initObserver() {
        // quan sát listData của ViewModel để update lại UI order
        orderFragmentViewmodel.getUnconfirmedOrderList().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                itemOrderAdapter.setDataList(orders);
                itemOrderAdapter.notifyDataSetChanged();
            }
        });

        orderFragmentViewmodel.getMessageResponse().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
        mainActivityViewmodel.getMessageResponse().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String msg) {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}