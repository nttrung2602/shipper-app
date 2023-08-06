package com.trungdz.appshipper.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trungdz.appshipper.R;
import com.trungdz.appshipper.databinding.FragmentHistoryBinding;
import com.trungdz.appshipper.service.model.Order;
import com.trungdz.appshipper.viewmodel.HistoryFragmentViewmodel;
import com.trungdz.appshipper.viewmodel.SharedMainActivityViewmodel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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

    FragmentHistoryBinding binding;
    HistoryFragmentViewmodel historyFragmentViewmodel;
    SharedMainActivityViewmodel sharedMainActivityViewmodel;
    ItemOrderAdapter itemOrderAdapter;
    List<Order> orderList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        historyFragmentViewmodel = ViewModelProviders.of(this).get(HistoryFragmentViewmodel.class);
        sharedMainActivityViewmodel = ViewModelProviders.of(requireActivity()).get(SharedMainActivityViewmodel.class);
        initUI();
        initObserver();
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    void initObserver() {
        historyFragmentViewmodel.getOrderedList().observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                itemOrderAdapter.setDataList(orders);
                itemOrderAdapter.notifyDataSetChanged();
            }
        });
    }

    void initUI() {
        itemOrderAdapter = new ItemOrderAdapter(orderList, new ItemOrderAdapter.OnClickListener() {
            @Override
            public void onClick(View view, int id_order) {
                sharedMainActivityViewmodel.setIdOrderIsSelected(id_order);
                replaceFragment(new OrderDetailForHistoryFragment());
            }
        });
        binding.listViewRecycle.setAdapter(itemOrderAdapter);
    }

    void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
//                fragmentTransaction.add(R.id.frame_layout, fragment);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}